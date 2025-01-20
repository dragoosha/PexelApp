# PexelApp

PexelApp is an Android application that allows users to explore and search for stunning, high-quality stock photos and videos, sourced from the Pexels API. With a seamless browsing experience, users can discover free, royalty-free media shared by creators and use them in their projects.

## Features

- **Browse High-Quality Media:** Search and explore a wide variety of images and videos curated from the Pexels API.
- **Paging Support:** Efficiently loads large sets of images with Paging3 for smooth scrolling.
- **Favorites:** Save your favorite images and videos for quick access.
- **Search Functionality:** Easily find images and videos using keywords.
- **Offline Storage:** Use Room for caching and accessing media while offline.

## Tech Stack

- **Android SDK**: Core Android development tools.
- **Jetpack Compose**: Modern UI toolkit for building native Android UIs.
- **Jetpack Navigation**: Simplifies navigation between screens.
- **Room**: Local database for caching media data.
- **Retrofit**: HTTP client to interact with the Pexels API.
- **Dagger/Hilt**: Dependency injection for clean and modular architecture.
- **Coroutines + Flow**: Asynchronous programming for smooth and responsive UI.
- **Paging3**: Efficiently loads and paginates large lists of media data.

## Before Usage

To run the application, you should first add your **API key** from Pexels to the `local.properties` file. You can get your API key by signing up on [Pexels](https://www.pexels.com/api/).

Add the following to your `local.properties` file:

API_KEY=your_pexels_api_key_here


## Getting Started

### Prerequisites

To run this project locally, make sure you have:

- Android Studio installed
- A physical Android device or emulator
- An active internet connection to fetch media from Pexels API

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/PexelApp.git
    ```

2. **Open the project in Android Studio:**
   - Open the cloned repository in Android Studio and let it sync the dependencies.

3. **Add your Pexels API key:**
   - As mentioned earlier, add your Pexels API key to the `local.properties` file.

4. **Run the app:**
   - Connect an Android device or start an emulator and run the app from Android Studio.

## Usage

1. **Explore Media:** Browse through trending photos, videos, and categories.
2. **Search for Media:** Use the search bar to find images and videos based on keywords.
3. **Save Favorites:** Save your favorite images and videos for quick access.
4. **Offline Mode:** View previously saved media while offline.

## How It Works

The app integrates with the Pexels API to fetch high-quality images and videos based on the user's requests. It uses **Paging3** to load data in chunks and display results in an efficient manner. 

The following key components are used in the app:

- **Retrofit**: Makes network requests to fetch media from Pexels.
- **Room**: Caches images and videos locally for offline usage.
- **Coroutines + Flow**: Makes network calls and database interactions asynchronous for smooth UI performance.
- **Dagger/Hilt**: Handles dependency injection, keeping the code clean and modular.


## Contribution

Contributions are welcome! Feel free to fork the repository, create a branch, and submit pull requests with bug fixes, improvements, or new features.

### How to Contribute

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement the change and add tests (if necessary).
4. Commit your changes with descriptive commit messages.
5. Push your changes to your fork and create a pull request.


## Acknowledgements

- [Pexels API](https://www.pexels.com/api/) for providing free stock photos and videos.
- [Android Jetpack](https://developer.android.com/jetpack) for modern Android development tools.
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging) for efficient data pagination.
- [Retrofit](https://square.github.io/retrofit/) for handling HTTP requests.


