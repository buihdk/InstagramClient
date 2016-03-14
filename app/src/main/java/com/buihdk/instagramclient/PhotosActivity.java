package com.buihdk.instagramclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        // send out API request to Popular Photos
        photos = new ArrayList<>();

        // 1. Create the adapter linking it to the source
        aPhotos = new InstagramPhotosAdapter(this, photos);

        // 2. Find the ListView from the layout
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);

        // 3. Set the adapter binding it to the ListView
        lvPhotos.setAdapter(aPhotos);

        // Fetch the popular photos
        fetchPopularPhotos();
    }

    // Trigger API request
    public void fetchPopularPhotos() {
        /*
        - CLIENT_ID = e05c462ebd86446ea48a5af73769b602
        - Popular: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
        - Response (API Map)
        */

        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        // Create the network client
        AsyncHttpClient client = new AsyncHttpClient();

        // Trigger the GET request
        client.get(url, null, new JsonHttpResponseHandler() {

            // onSuccess (worked, 200)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Expecting a JSON object


                // Iterate each of the photo items and decode the item into a java object
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data"); // array of posts
                    // iterate array of posts
                    for (int i = 0; i < photosJSON.length(); i++) {
                        // get the JSON object at that position
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        // decode the attributes of the JSON into a data model
                        InstagramPhoto photo = new InstagramPhoto();
                        // Author Name: { "data" => [array of items] => "user" => "username" }
                        photo.username = photoJSON.getJSONObject("user").getString("username");
                        // Caption: { "data" => [array of items] => "caption" => "text" }
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        // Type: { "data" => [array of items] => "type" } ("image" or "video")
                        //photo.type = photosJSON.getJSONObject("type").getString("text");
                        // URL: { "data" => [array of items] => "images" => "standard_resolution" => "url" }
                        photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        // Height
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        // Likes Count
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        // Add decoded object to the photos
                        photos.add(photo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // callback
                aPhotos.notifyDataSetChanged();
            }

            // onFailure (fail)
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // DO SOMETHING

            }
        });
    }
}
