package sg.edu.smu.cs461.cs461assignment3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar

class UserC : AppCompatActivity() {
    var ratingNumber = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingNumber = getSharedPreferences("numStarsC", 0).getFloat("numStarsC", 0f)

        setContentView(R.layout.activity_user_c)
        val ratingBar = findViewById<RatingBar>(R.id.ratingC)

        ratingBar.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                ratingNumber = p1
                loadData()
            }
        })
        showRatings()
    }

    fun showRatings(){
        val ratingBar = findViewById<RatingBar>(R.id.ratingC)
        ratingBar.rating = ratingNumber
    }

    private fun loadData(){
        val sharedPreference = getSharedPreferences("numStarsC", 0)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putFloat("numStarsC", ratingNumber)
        editor.commit()
        val ratingBar = findViewById<RatingBar>(R.id.ratingC)
        ratingBar.rating = sharedPreference.getFloat("numStarsC", 0f)
    }

    fun backToProfileC(view: View) {
        val it = Intent()
        it.putExtra("rating", ratingNumber.toString())
        setResult(RESULT_OK, it)
        finish()
    }
}