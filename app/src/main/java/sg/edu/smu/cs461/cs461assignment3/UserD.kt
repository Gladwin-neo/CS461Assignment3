package sg.edu.smu.cs461.cs461assignment3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar

class UserD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_d)
//        loadData()
    }

    private fun loadData(){
        val sharedPreference = getPreferences(Context.MODE_PRIVATE)
        val rating = sharedPreference.getFloat("numStars", 4f)
        val ratingBar = findViewById<RatingBar>(R.id.rating)
        ratingBar.rating = rating
    }
}