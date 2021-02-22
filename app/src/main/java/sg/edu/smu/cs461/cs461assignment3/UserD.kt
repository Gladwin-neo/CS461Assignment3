package sg.edu.smu.cs461.cs461assignment3

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar

class UserD : AppCompatActivity() {
    var ratingNumber = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingNumber = getSharedPreferences("numStarsD", 0).getFloat("numStarsD", 0f)

        setContentView(R.layout.activity_user_d)
        val ratingBar = findViewById<RatingBar>(R.id.ratingD)

        ratingBar.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                ratingNumber = p1
                loadData()
            }
        })
        showRatings()
    }

    fun showRatings(){
        val ratingBar = findViewById<RatingBar>(R.id.ratingD)
        ratingBar.rating = ratingNumber
    }

    private fun loadData(){
        val sharedPreference = getSharedPreferences("numStarsD", 0)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putFloat("numStarsD", ratingNumber)
        editor.commit()
        val ratingBar = findViewById<RatingBar>(R.id.ratingD)
        ratingBar.rating = sharedPreference.getFloat("numStarsD", 0f)
    }
}