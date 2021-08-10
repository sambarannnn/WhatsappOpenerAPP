package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number :String = "0"
        if(intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(number.isDigitsOnly() || number[number.length/2] == ' ') {
            startWhatsapp(number);
        } else {
            Toast.makeText(this, "Please check the number", Toast.LENGTH_SHORT).show();
        }
    }
    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data :String = when {
            number[0] == '+' -> {
                number.substring(1)
            }
            number.length == 10 -> {
                //adding India's country code
//                Toast.makeText(this, "91$number", Toast.LENGTH_SHORT).show()
                "91$number"
            }
            number.length == 11 -> {
                //adding India's country code
//                Toast.makeText(this, "91$number", Toast.LENGTH_SHORT).show()
                "91$number"
            }
            else -> {
//                Toast.makeText(this, number, Toast.LENGTH_SHORT).show()
                number
            }
        }
        intent.data = Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please install WhatsApp!", Toast.LENGTH_SHORT).show()
        }
    }
}