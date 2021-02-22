package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val REQ_CODE = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the name the was saved alr
        val name = getSharedPreferences("Name", 0).getString("Name", null)
        Log.i("test",name)
        val title = findViewById<TextView>(R.id.textView)

        val originalTitle = title.text.toString()
        title.text = originalTitle + name
    }

    // bringing users to the create profile page
    fun createProfile(view: View) {
        val it = Intent(this, Profile::class.java)
        startActivityForResult(it, REQ_CODE)
    }
}