package jp.co.recruit.r.kazuki_kinoshita.viewlayersample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class ViewControlService: Service() {

    val binder = object : IViewControlService.Stub() {
        override fun getPid(): Int {
            return 100
        }

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            TODO("Not yet implemented")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }
}