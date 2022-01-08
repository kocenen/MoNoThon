package com.example.monothon.intro.picture

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
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.api.NaverAPI
import com.example.monothon.api.NaverAPIRes
import com.example.monothon.databinding.ActivityFacePictureBinding
import com.example.monothon.util.NaverUtil
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
        mBinding.cameraCaptureButton.setOnClickListener { takePhoto() }
        mBinding.cameraCaptureButton2.setOnClickListener { takePhoto() }
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

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
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
                    val filePart = MultipartBody.Part.createFormData("file", photoFile.name + ".jpg", fileBody)
                    checkUserFace(filePart)
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

    private fun checkUserFace(imageFile: MultipartBody.Part) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/vision/face")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server: NaverAPI = retrofit.create(NaverAPI::class.java)

        server.naverCheckFace(NaverUtil.naverClientId, NaverUtil.naverClientSecret, imageFile).enqueue(object : Callback<NaverAPIRes> {
                override fun onFailure(call: Call<NaverAPIRes>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "얼굴_체크_실패", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<NaverAPIRes>?, response: Response<NaverAPIRes>?) {
                    Log.e("네이버_얼굴_체크_데이터: ", "$response")
                }
            }
        )
    }
}