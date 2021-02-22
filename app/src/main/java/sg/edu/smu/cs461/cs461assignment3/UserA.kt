package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserA : AppCompatActivity() {
    var ratingNumber = 0f
    private val REQ_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingNumber = getSharedPreferences("numStarsA", 0).getFloat("numStarsA", 0f)

        setContentView(R.layout.activity_user_a)
        val ratingBar = findViewById<RatingBar>(R.id.ratingA)

        ratingBar.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                ratingNumber = p1
                loadData()
            }

        })
        showRatings()
    }

    fun showRatings(){
        val ratingBar = findViewById<RatingBar>(R.id.ratingA)
        ratingBar.rating = ratingNumber

    }

    private fun loadData(){
        val sharedPreference = getSharedPreferences("numStarsA", 0)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putFloat("numStarsA", ratingNumber)
        editor.commit()
        val ratingBar = findViewById<RatingBar>(R.id.ratingA)
        ratingBar.rating = sharedPreference.getFloat("numStarsA", 0f)



    }

    fun backToProfile(view: View) {
        val it = Intent()
        it.putExtra("rating", ratingNumber.toString())
        setResult(RESULT_OK, it)
        finish()
    }
}