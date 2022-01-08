package com.example.monothon.view.intro.picture

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.api.NaverAPI
import com.example.monothon.api.NaverAPIRes
import com.example.monothon.api.model.Face
import com.example.monothon.databinding.ActivityFacePictureBinding
import com.example.monothon.model.SunType
import com.example.monothon.repository.db.RoomDB
import com.example.monothon.repository.db.SunHistoryItem
import com.example.monothon.util.NaverUtil
import com.example.monothon.view.history.list.HistoryListActivity
import com.example.monothon.view.intro.result.SunBreakActivity
import com.example.monothon.view.intro.result.SunSafeActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus


class FacePictureActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFacePictureBinding
    private val mViewModel by viewModels<FacePictureViewModel>()

    private var preview : Preview? = null
    private var imageCapture: ImageCapture? = null
    private lateinit var imageFile: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_picture)

        initBinding()
        initClickListener()

        startCamera()

        imageFile = createImageFile()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_face_picture)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
        mBinding.viewModel = mViewModel
    }

    private fun initClickListener() {
        with(mBinding) {
            historyButton.setOnClickListener {
                startActivity(Intent(this@FacePictureActivity, HistoryListActivity::class.java))
            }
            historyButton2.setOnClickListener {
                startActivity(Intent(this@FacePictureActivity, HistoryListActivity::class.java))
            }

            cameraCaptureButton.setOnClickListener {
                mBinding.progressCamera.isVisible = true
                takePhoto()
            }
            cameraCaptureButton2.setOnClickListener {
                mBinding.progressCamera.isVisible = true
                takePhoto()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            preview = Preview.Builder()
                .build()
                .also { preview ->
                    preview.setSurfaceProvider(mBinding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch(exc: Exception) {
                Log.d("CameraX-Debug", "Use case mBinding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createImageFile()
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.d("CameraX-Debug", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val fileBody = RequestBody.create("image/jpg".toMediaTypeOrNull(), photoFile)
                    val filePart = MultipartBody.Part.createFormData("image", photoFile.name + ".jpg", fileBody)
                    getUserFaceInfo(filePart, photoFile)
                }
            }
        )
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val filename = "JPEG_${timeStamp.format(System.currentTimeMillis())}_"
        val storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val file = File.createTempFile(
            filename,
            ".jpg",
            storageDir
        )
        file.delete()
        return file
    }

    private fun getUserFaceInfo(imageFilePart: MultipartBody.Part, imageFile: File) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/vision/face/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: NaverAPI = retrofit.create(NaverAPI::class.java)

        server.naverCheckFace(NaverUtil.naverClientId, NaverUtil.naverClientSecret, imageFilePart).enqueue(object : Callback<NaverAPIRes> {
                override fun onFailure(call: Call<NaverAPIRes>?, t: Throwable?) {
                    mBinding.progressCamera.isVisible = false

                    Toast.makeText(applicationContext, "얼굴_체크_실패", Toast.LENGTH_SHORT).show()
                    Log.e("네이버_얼굴_체크_실패실패: ", "$t")
                }

                override fun onResponse(call: Call<NaverAPIRes>?, response: Response<NaverAPIRes>?) {
                    mBinding.progressCamera.isVisible = false

                    val resultBody = response?.body()
                    Log.e("네이버_얼굴_체크_성공성공:", "$resultBody")

                    if(!resultBody?.faces.isNullOrEmpty()) {
                        checkUserFace(resultBody?.faces?.get(0)!!, imageFile)
                    } else {
                        startActivity(
                            Intent(this@FacePictureActivity, SunSafeActivity::class.java)
                                .putExtra("imageFile", imageFile)
                        )
                    }
                }
            }
        )
    }

    private fun checkUserFace(faceInfo: Face, imageFile: File) {
        if(faceInfo.emotion.confidence >= 0.5) {
            Log.e("네이버_얼굴_표정_체크", "${faceInfo.emotion.value}")
            when(faceInfo.emotion.value) {
                "angry" -> startActivity(Intent(this, SunBreakActivity::class.java))
                "disgust" -> startActivity(Intent(this, SunBreakActivity::class.java))
                "fear" -> startActivity(Intent(this, SunBreakActivity::class.java))
                "sad" -> startActivity(Intent(this, SunBreakActivity::class.java))
                else -> {
                    startActivity(Intent(this, SunSafeActivity::class.java).putExtra("imageFile", imageFile))
                }
            }
        } else {
            startActivity(Intent(this, SunSafeActivity::class.java).putExtra("imageFile", imageFile))
        }
    }
}