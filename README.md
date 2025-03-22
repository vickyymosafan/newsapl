# News App

A modern Android news application that displays news from NewsAPI.org with a beautiful UI. Created for Bank Mandiri technical assessment.

## Features

- **Top Headlines**: Display current headline news in a horizontal scrollable carousel
- **News List**: Show a comprehensive list of news articles with infinite scrolling
- **Article Details**: View detailed information about each news article
- **Pull to Refresh**: Update the news feed with the latest articles
- **Open in Browser**: Option to open the full article in a web browser

## Technical Details

### Architecture
This app follows the MVVM (Model-View-ViewModel) architecture pattern using:
- Jetpack Compose for UI
- ViewModel for managing UI-related data
- Repository pattern for data handling
- StateFlow for reactive data streams

### Libraries
- Retrofit: Network requests
- Gson: JSON parsing
- Coil: Image loading
- Material 3: UI components

### API
This app uses the NewsAPI.org service to fetch news data:
- Top Headlines Endpoint: `/v2/top-headlines`
- All News Endpoint: `/v2/everything`

## Setup
To build and run this application:
1. Clone the repository
2. Open the project in Android Studio
3. Run the app on an emulator or physical device

## Screenshots
(Screenshots would be added here after implementation)

## Project Structure
```
├── data
│   ├── api
│   │   ├── NewsApiService.kt
│   │   └── RetrofitClient.kt
│   ├── model
│   │   └── NewsResponse.kt
│   └── repository
│       └── NewsRepository.kt
├── ui
│   ├── components
│   │   └── NewsCard.kt
│   ├── screens
│   │   ├── ArticleDetailScreen.kt
│   │   └── NewsScreen.kt
│   ├── theme
│   └── viewmodel
│       └── NewsViewModel.kt
└── MainActivity.kt
```
