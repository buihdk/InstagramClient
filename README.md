# InstagramClient

**InstagramClient ** is an android app that allows a user to check out the most popular photos on Instagram

Submitted by: **Khoa Bui**

Time spent: 10 hours

## User Stories

The following **required** functionality is completed:

* [x] User can scroll through current popular photos from Instagram 
* [x] For each photo displayed, user can see the following details:
Graphic, Caption, Username (2 points)
(Optional) relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/NhmkrfK.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Challenges encountered while building the app:
* Had some issues running the 'app' with other APIs besides API 23 
    
    Update: The issue was generated due to the readItems() statement in onCreate method when the app initially tried to read the list of items in a text file that didn't exist. The issue is now fixed after the implementation of SQLite.
    
* Figuring out how to use Intent and Extras took quite some time
* Picking the right color combinations that can go well with the background image was hard

## License

    Copyright (c) 2016 Khoa Bui

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
