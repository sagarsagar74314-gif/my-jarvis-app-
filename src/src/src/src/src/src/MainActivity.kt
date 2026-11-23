import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var voiceHelper: VoiceHelper
    private lateinit var commandRouter: CommandRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inputBox = EditText(this).apply {
            hint = "कमांड लिखो (जैसे: खोलो youtube, फ्लैशलाइट ऑन, अलार्म)"
        }
        val runButton = Button(this).apply {
            text = "चलाओ"
        }
        val resultText = TextView(this).apply {
            text = "हां जी बॉस, तैयार हूँ।"
            textSize = 18f
            setPadding(24, 24, 24, 24)
        }

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(32, 64, 32, 32)
            addView(inputBox)
            addView(runButton)
            addView(resultText)
        }
        setContentView(layout)

        voiceHelper = VoiceHelper(this) {
            voiceHelper.speak("हां जी बॉस, तैयार हूँ।")
        }
        commandRouter = CommandRouter(this, voiceHelper)

        runButton.setOnClickListener {
            val cmd = inputBox.text.toString()
            if (cmd.isNotBlank()) {
                val reply = commandRouter.processCommand(cmd)
                resultText.text = reply
            }
        }
    }

    override fun onDestroy() {
        voiceHelper.shutdown()
        super.onDestroy()
    }
}
