import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

var FIREBASE_APP: FirebaseApp = initializeFirebaseApp()

fun initializeFirebaseApp(): FirebaseApp {
    FileInputStream("firebase_service_account_key.json").use { fis -> //or implicit `it`
        val options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(fis)).build()
        return FirebaseApp.initializeApp(options)
    }
}
