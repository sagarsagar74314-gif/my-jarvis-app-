import android.content.Context
import android.content.Intent

class AppLauncher(private val context: Context) {

    fun openAppByName(appName: String): String {
        val name = appName.trim().lowercase()

        val knownPackages = mapOf(
            "youtube" to "com.google.android.youtube",
            "whatsapp" to "com.whatsapp",
            "instagram" to "com.instagram.android",
            "free fire" to "com.dts.freefireth",
            "chrome" to "com.android.chrome"
        )

        for ((key, pkg) in knownPackages) {
            if (name.contains(key)) {
                return launchPackage(pkg, key)
            }
        }

        val pm = context.packageManager
        val installedApps = pm.getInstalledApplications(0)
        for (app in installedApps) {
            val label = pm.getApplicationLabel(app).toString().lowercase()
            if (label.contains(name)) {
                return launchPackage(app.packageName, label)
            }
        }

        return "$appName ऐप नहीं मिला।"
    }

    private fun launchPackage(packageName: String, displayName: String): String {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        return if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            "$displayName खोल रहा हूँ।"
        } else {
            "$displayName ओपन नहीं हो सका।"
        }
    }
}
