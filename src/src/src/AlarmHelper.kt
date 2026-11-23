import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class AlarmHelper(private val context: Context) {

    fun setAlarm(hour: Int, minute: Int, message: String = "Jarvis Alarm"): String {
        return try {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minute)
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
                putExtra(AlarmClock.EXTRA_SKIP_UI, false)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            "$hour:$minute का अलार्म सेट कर रहा हूँ।"
        } catch (e: Exception) {
            "अलार्म सेट करने में समस्या आई।"
        }
    }
}
