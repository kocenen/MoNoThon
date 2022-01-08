package com.example.monothon.view.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.widget.Toast
import com.example.monothon.databinding.ActivityMakeWoohanSmsactivityBinding

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
        val telNumber = tm.line1Number
        if (telNumber != null)
            phoneNumber = telNumber

        setOnClick()
    }

    private fun setOnClick() {
        binding.send.setOnClickListener {
            sendSMS(phoneNumber, binding.tvMessage.text.toString())
        }
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val sms: SmsManager = SmsManager.getDefault()
        sms.sendTextMessage("*23#$phoneNumber", null, message, null, null)
    }
}