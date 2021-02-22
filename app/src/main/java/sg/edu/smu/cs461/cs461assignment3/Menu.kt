package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class Menu : AppCompatActivity() {
    private val REQ_CODE = 1234
    private val REQ_CODE_B = 1236
    private val REQ_CODE_C = 1237
    private val REQ_CODE_D = 1238
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    // bringing users to the main page to see user A's profile
    fun userA(view: View) {
        val it = Intent(this, UserA::class.java)
        startActivityForResult(it, REQ_CODE)
    }

    // bringing users to the main page to see user B's profile
    fun userB(view: View) {
        val it = Intent(this, UserB::class.java)
        startActivityForResult(it, REQ_CODE_B)
    }

    // bringing users to the main page to see user C's profile
    fun userC(view: View) {
        val it = Intent(this, UserC::class.java)
        startActivityForResult(it, REQ_CODE_C)
    }

    // bringing users to the main page to see user D's profile
    fun userD(view: View) {
        val it = Intent(this, UserD::class.java)
        startActivityForResult(it, REQ_CODE_D)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var rating = data?.getStringExtra("rating").toString()
        Log.i("dirPic", rating)


        when (requestCode) {
            1234 -> {
                Toast.makeText(this, "Your rating for Shaun: $rating", Toast.LENGTH_SHORT).show()
            }
            1236 -> {
                Toast.makeText(this, "Your rating for Gladwin: $rating", Toast.LENGTH_SHORT).show()
            }
            1237 -> {
                Toast.makeText(this, "Your rating for Alwyn: $rating", Toast.LENGTH_SHORT).show()
            }
            1238 -> {
                Toast.makeText(this, "Your rating for Giles: $rating", Toast.LENGTH_SHORT).show()
            }
        }

    }
}