package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {
    private val REQ_CODE = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun userA(view: View) {
        val it = Intent(this, UserA::class.java)
        startActivityForResult(it, REQ_CODE)
    }

    fun userB(view: View) {
        val it = Intent(this, UserB::class.java)
        startActivityForResult(it, REQ_CODE)
    }

    fun userC(view: View) {
        val it = Intent(this, UserC::class.java)
        startActivityForResult(it, REQ_CODE)
    }

    fun userD(view: View) {
        val it = Intent(this, UserD::class.java)
        startActivityForResult(it, REQ_CODE)
    }
}