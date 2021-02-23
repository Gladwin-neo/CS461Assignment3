package sg.edu.smu.cs461.cs461assignment3

import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.*
import java.util.*


class Profile : AppCompatActivity(){
    private val REQ_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val name = getSharedPreferences("Name", 0).getString("Name", "")
        val age = getSharedPreferences("Age", 0).getInt("Age", 0).toString()
        val interests = getSharedPreferences("Interests", 0).getString("Interests", "")
        if (name!== "" && age !== "0" && interests !== "") {
            val nameET = findViewById<EditText>(R.id.nameEditText)
            nameET.setText(name)

            val ageET = findViewById<EditText>(R.id.ageEditText)
            ageET.setText(age)

            val interestsET = findViewById<EditText>(R.id.interestsEditText)
            interestsET.setText(interests)
        }

        val imgFile = File(getDir("profilePhoto", MODE_APPEND).toString() + "/profile.jpg")
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            val myImage = findViewById<ImageView>(R.id.profileImage) as ImageView
            myImage.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 500, 600, false));

        }


    }

    // saving user information
    fun save(view: View) {
        val nameEditText = findViewById<EditText>(R.id.nameEditText);
        val ageEditText = findViewById<EditText>(R.id.ageEditText);
        val interestsEditText = findViewById<EditText>(R.id.interestsEditText);

        // checking if user attempt to not fill up anything
        if (nameEditText.text.toString() == "" || ageEditText.text.toString() == "" || interestsEditText.text.toString() == ""){
            Toast.makeText(this, "Please ensure all fields have been filled in!", Toast.LENGTH_SHORT).show()
        } else {
            val name = getSharedPreferences("Name", 0)
            val editor1: SharedPreferences.Editor = name.edit()
            editor1.putString("Name", nameEditText.text.toString())
            editor1.commit()

            val age = getSharedPreferences("Age", 0)
            val editor2: SharedPreferences.Editor = age.edit()
            editor2.putInt("Age", ageEditText.text.toString().toInt())
            editor2.commit()

            val interests = getSharedPreferences("Interests", 0)
            val editor3: SharedPreferences.Editor = interests.edit()
            editor3.putString("Interests", interestsEditText.text.toString())
            editor3.commit()

            val it = Intent(this, Menu::class.java)
            startActivityForResult(it, REQ_CODE)
        }
    }

    // bringing users to the main page to see other users' profile
    fun menu(view: View) {
        val it = Intent(this, Menu::class.java)
        startActivityForResult(it, REQ_CODE)
    }

    fun setProfileImage(view: View) {
        val pickImageFileIntent = Intent()
        while (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),111)
        }

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
            profileImage.layoutParams.height = 600;
            profileImage.requestLayout();
            if (pic === null) {
                val selectedImage: Uri? = imageReturnedIntent?.data
                profileImage.setImageURI(selectedImage)
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                val uri = saveImageToInternalStorage(bitmap)
            } else {
                profileImage.setImageBitmap(Bitmap.createScaledBitmap(pic, 500, 600, false));
                storeImage(pic)
            }
        }
    }

    // Method to save an image to internal storage
    private fun saveImageToInternalStorage(bitmap: Bitmap):Uri{
        val wrapper = ContextWrapper(applicationContext)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("profilePhoto", MODE_APPEND)

        // Create a file to save the image
        file = File(file, "profile.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }


    /** Create a File for saving an image or video  */
    private fun getOutputMediaFile(): File? {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
//        Log.i("dirPic", filesDir
//                .toString() + "/Android/data/"
//                + applicationContext.packageName
//                + "/Files")
        val mediaStorageDir = File(getDir("profilePhoto", MODE_APPEND)
                .toString())
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        // Create a media file name
        val mediaFile: File
        val mImageName = "profile.jpg"
        mediaFile = File(mediaStorageDir.path + File.separator + mImageName)
        return mediaFile
    }

    private fun storeImage(image: Bitmap) {
        val pictureFile = getOutputMediaFile()
        if (pictureFile == null) {
            Log.d("TAG",
                    "Error creating media file, check storage permissions: ") // e.getMessage());
            return
        }
        try {
            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.d("TAG", "File not found: ")
        } catch (e: IOException) {
            Log.d("TAG", "Error accessing file: ")
        }
    }
}

