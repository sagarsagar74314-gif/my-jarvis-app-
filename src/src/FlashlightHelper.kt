import android.content.Context
import android.hardware.camera2.CameraManager

class FlashlightHelper(private val context: Context) {

    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private val cameraId: String? = cameraManager.cameraIdList.firstOrNull()

    fun setFlashlight(enabled: Boolean): String {
        val id = cameraId ?: return "कैमरा फ़्लैश उपलब्ध नहीं है।"
        return try {
            cameraManager.setTorchMode(id, enabled)
            if (enabled) "फ़्लैशलाइट चालू कर दी है।" else "फ़्लैशलाइट बंद कर दी है।"
        } catch (e: Exception) {
            "फ़्लैशलाइट बदलने में समस्या आई।"
        }
    }
}
