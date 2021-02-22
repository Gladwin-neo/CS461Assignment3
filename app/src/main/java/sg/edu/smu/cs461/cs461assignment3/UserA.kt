package sg.edu.smu.cs461.cs461assignment3

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class UserA : AppCompatActivity() {
    var ratingNumber = 0f

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
}