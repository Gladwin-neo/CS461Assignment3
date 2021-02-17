package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Profile : AppCompatActivity() {
    private val REQ_CODE = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    // saving user information
    fun save(view: View) {
        val nameEditText = findViewById<EditText>(R.id.nameEditText);
        val ageEditText = findViewById<EditText>(R.id.ageEditText);
        val interestsEditText = findViewById<EditText>(R.id.interestsEditText);
    }

    // bringing users to the main page to see other users' profile
    fun menu(view: View) {
        val it = Intent(this, Menu::class.java)
        startActivityForResult(it, REQ_CODE)
    }
}