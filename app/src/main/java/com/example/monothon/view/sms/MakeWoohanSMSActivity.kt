package com.example.monothon.view.sms

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.monothon.databinding.ActivityMakeWoohanSmsactivityBinding
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.monothon.view.intro.picture.FacePictureActivity
import com.example.monothon.model.SunType
import com.example.monothon.repository.db.RoomDB
import com.example.monothon.repository.db.SunHistoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File


class MakeWoohanSMSActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMakeWoohanSmsactivityBinding.inflate(layoutInflater)
    }
    private lateinit var tm: TelephonyManager
    private lateinit var phoneNumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        var telNumber = tm.line1Number
        if (telNumber != null)
            phoneNumber = telNumber
        if(telNumber.contains("+82")){
            telNumber = telNumber.replace("+82", "0")
        }

        binding.clWhenText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                CoroutineScope(Dispatchers.Main).launch {
                    binding.progressWoohan.isVisible = true
                    delay(5000)
                    binding.progressWoohan.isVisible = false
                    binding.clCorona.isVisible = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        setOnClick()
    }

    private fun setOnClick() {
        binding.send.setOnClickListener {
            val db = RoomDB.getInstance(applicationContext)
            db!!.sunHistoryDao().addHistory(SunHistoryItem(SunType.COVID, true, "22.01.09", (intent.getSerializableExtra("imageFile") as File).path))

            val smsUri = Uri.parse("sms:$phoneNumber");
            val sendIntent = Intent(Intent.ACTION_SENDTO, smsUri);
            sendIntent.putExtra("sms_body", binding.tvMessage.text.toString());


            finishAffinity()
            startActivity(Intent(this, FacePictureActivity::class.java))
            startActivity(sendIntent)

        }
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val sms: SmsManager = SmsManager.getDefault()
        sms.sendTextMessage("*23#$phoneNumber", null, message, null, null)
    }

    companion object{
        const val PERMISSION_REQUEST_CODE = 1
    }
}