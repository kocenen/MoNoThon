package com.example.monothon.view.sms

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import com.example.monothon.databinding.ActivityMakeMarryBinding

class MakeMarryActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMakeMarryBinding.inflate(layoutInflater)
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
        setOnClick()
    }


    private fun setOnClick() {
        binding.send.setOnClickListener {
            val smsUri = Uri.parse("sms:$phoneNumber");
            val sendIntent = Intent(Intent.ACTION_SENDTO, smsUri);
            sendIntent.putExtra("sms_body", binding.tvMessage.text.toString());
            startActivity(sendIntent)
        }

    }


}