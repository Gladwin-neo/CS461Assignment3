package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Profile : AppCompatActivity() {
    private val REQ_CODE = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun save(view: View) {}

    // bringing users to the main page to see other users' profile
    fun menu(view: View) {
        val it = Intent(this, Menu::class.java)
        startActivityForResult(it, REQ_CODE)
    }
}