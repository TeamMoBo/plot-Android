# plot-android

##### <**Movie Matching Service**> : Find friends to watch movie 

영화 같이 볼 친구 찾기 어플리케이션

<img width="300" alt="plot" src="https://user-images.githubusercontent.com/57608585/71559878-9d498400-2aa6-11ea-81d1-0f407b7bc3c6.png">



# **Project Period**

Start : Dec 22, 2019  

End : Jan 4, 2020



# **Project Member**

- [김민진](https://github.com/kim003512)
- [강수빈](https://github.com/ksb0511)
- [황지혜](https://github.com/jihye0420)
- [박길현](https://github.com/G-hyeon)



# **Project Environment**

Android Studio (Sdk Version : 29)



# Using Library

- recyclerview

  ```kotlin
  implementation 'androidx.recyclerview:recyclerview:1.1.0'
  ```

- glide

  ```kotlin
  implementation 'com.github.bumptech.glide:glide:4.9.0'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
  ```

- retrofit

  ```kotlin
  implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
  implementation 'com.squareup.retrofit2:retrofit:2.6.2'
  ```

- Gson

  ```kotlin
  implementation 'com.google.code.gson:gson:2.8.6'
  implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
  ```

- circleimageview

  ```kotlin
  implementation 'de.hdodenhof:circleimageview:3.0.1'
  ```

- tablayout

  ```kotlin
  implementation 'com.google.android.material:material:1.0.0'
  ```

- tagText

  ```kotlin
  implementation 'com.github.anonymous-ME:TagsEditText:0.5.0'
  implementation 'me.gujun.android.taggroup:library:1.4@aar'
  implementation 'com.veinhorn.tagview:library:1.0.4'
  ```

- Date

  ```kotlin
  implementation 'com.github.hotchemi:khronos:0.9.0'
  ```

- firebase 

  ```kotlin
  implementation 'com.google.firebase:firebase-analytics:17.2.0'
  implementation 'com.google.firebase:firebase-database:19.2.0'
  ```

  

- firebase plugin

  ```kotlin
  apply plugin: 'com.google.gms.google-services'
  ```

  



## Program Structure



|             Activity             |                         Description                          |
| :------------------------------: | :----------------------------------------------------------: |
|         `SplashActivity`         |        First run of the app using the Lottie Library         |
| `SignInActivity, SignUpActivity` | 1. If ID exists, Login successful & Move to MainActivity<br />2.Move to membership activity if ID does not exist |
|          `MainActivity`          | 1. Show Recommendation Movie, Selected Movie, Selected Time<br />2.Move to MyPageActivity, ModifyMovieActivity, ModifyTimeActivity, MovieSelectionActivity |
|     `MovieSelectionActivity`     |                                                              |
|       `TimeChoiceActivity`       |                                                              |
|      `ModifyMovieActivity`       |                                                              |
|         `MyPageActivity`         |                                                              |
|    `MatchingHistoryActivity`     |                                                              |
| `MatchingHistoryDetailsActivity` |                                                              |
|        `MatchingActivity`        |                                                              |
|     `MatchingSelectionPopup`     |                                                              |
|        `ChattingActivity`        |                                                              |
|       `PayChoiceActivity`        |                                                              |
|                                  |                                                              |



# Implement key features

- viewpager
- recyclerview
- fragment
- chatting

- hashtag
- connect Hyperlink
- Pop-up
- load Photo from Gallery








