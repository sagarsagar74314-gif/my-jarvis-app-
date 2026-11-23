import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import java.util.Locale

class SpeechInputHelper(private val activity: Activity) {

    fun startListening(requestCode: Int = 1001) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("hi", "IN"))
            putExtra(RecognizerIntent.EXTRA_PROMPT, "बोलिए बॉस...")
        }
        activity.startActivityForResult(intent, requestCode)
    }

    fun extractSpokenText(data: Intent?): String? {
        val matches = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        return matches?.firstOrNull()
    }
}
