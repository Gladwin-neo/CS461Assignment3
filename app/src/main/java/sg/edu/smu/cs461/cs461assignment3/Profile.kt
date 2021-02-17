package sg.edu.smu.cs461.cs461assignment3

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class Profile : AppCompatActivity(){
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

    fun setProfileImage(view: View) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),111)
        }
        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101){
            var pic: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            val profileImage = findViewById<ImageView>(R.id.profileImage)
            profileImage.setImageBitmap(pic)
        }
    }
}