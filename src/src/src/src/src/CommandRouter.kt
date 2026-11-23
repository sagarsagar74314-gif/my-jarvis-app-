import android.content.Context

class CommandRouter(
    private val context: Context,
    private val voice: VoiceHelper
) {
    private val appLauncher = AppLauncher(context)
    private val flashlight = FlashlightHelper(context)
    private val alarm = AlarmHelper(context)

    fun processCommand(rawInput: String): String {
        val text = rawInput.trim().lowercase()

        val reply = when {
            text.contains("फ्लैशलाइट ऑन") || text.contains("flashlight on") || text.contains("टॉर्च चालू") ->
                flashlight.setFlashlight(true)

            text.contains("फ्लैशलाइट ऑफ") || text.contains("flashlight off") || text.contains("टॉर्च बंद") ->
                flashlight.setFlashlight(false)

            text.contains("अलार्म") || text.contains("alarm") ->
                alarm.setAlarm(7, 0, "Jarvis Alarm")

            text.startsWith("खोलो ") || text.startsWith("open ") -> {
                val targetApp = text.removePrefix("खोलो ").removePrefix("open ").trim()
                appLauncher.openAppByName(targetApp)
            }

            else ->
                "हां जी बॉस, कमांड मिला: $rawInput"
        }

        voice.speak(reply)
        return reply
    }
}
