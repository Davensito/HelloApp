package com.example.holakotin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            speak()
        }

    }

    fun speak(){

        var msgTxt = findViewById<EditText>(R.id.edMsg).text.toString()

        if(msgTxt.isEmpty()){
            msgTxt = "Are you trolling? "
        }
        findViewById<TextView>(R.id.textView).text = msgTxt

        tts!!.speak(msgTxt, TextToSpeech.QUEUE_FLUSH, null,"")

        //var msg : String = findViewById<TextView>(R.id.textView).text.toString()
        //tts!!.speak(msg, TextToSpeech.QUEUE_FLUSH, null,"") //Almacena frases para decir (el queueflush)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.textView).text = "Ready"
            tts!!.setLanguage(Locale.US)
        }else{
            findViewById<TextView>(R.id.textView).text = "No disponible"
        }
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }
}