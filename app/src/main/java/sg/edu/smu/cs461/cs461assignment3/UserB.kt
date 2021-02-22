package sg.edu.smu.cs461.cs461assignment3

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar

class UserB : AppCompatActivity() {
    var ratingNumber = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingNumber = getSharedPreferences("numStarsB", 0).getFloat("numStarsB", 0f)

        setContentView(R.layout.activity_user_b)
        val ratingBar = findViewById<RatingBar>(R.id.ratingB)

        ratingBar.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                ratingNumber = p1
                loadData()
            }
        })
        showRatings()
    }

    fun showRatings(){
        val ratingBar = findViewById<RatingBar>(R.id.ratingB)
        ratingBar.rating = ratingNumber
    }

    private fun loadData(){
        val sharedPreference = getSharedPreferences("numStarsB", 0)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putFloat("numStarsB", ratingNumber)
        editor.commit()
        val ratingBar = findViewById<RatingBar>(R.id.ratingB)
        ratingBar.rating = sharedPreference.getFloat("numStarsB", 0f)
    }
}