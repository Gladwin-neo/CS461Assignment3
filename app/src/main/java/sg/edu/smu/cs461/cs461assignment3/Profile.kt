package sg.edu.smu.cs461.cs461assignment3

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity(){
    private val REQ_CODE = 1234
    private val REQ_CODE_PROFILE = 1235

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
        val pickImageFileIntent = Intent()
        pickImageFileIntent.type = "image/*"
        pickImageFileIntent.action = Intent.ACTION_GET_CONTENT

        val pickGalleryImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val captureCameraImageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val pickTitle = "Capture from camera or Select from gallery the Profile photo"
        val chooserIntent = Intent.createChooser(pickImageFileIntent, pickTitle)
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS, arrayOf(
                captureCameraImageIntent,
                pickGalleryImageIntent
            )
        )
        startActivityForResult(chooserIntent, 1235)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        if (requestCode === 1235) {
            var pic = imageReturnedIntent?.getParcelableExtra<Bitmap>("data")
            if (pic === null) {
                val selectedImage: Uri? = imageReturnedIntent?.data
                profileImage.setImageURI(selectedImage)
            } else {
                profileImage.setImageBitmap(pic)
            }
        }
    }
}