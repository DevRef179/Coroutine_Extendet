package com.example.coroutine_extendet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.textView)
        textView.text = "Hallo DevRef"

        //CoroutineScope(IO).launch {
        CoroutineScope(Main).launch {
            //Log.i("MyTag","Berechnung hat begonnen..........")
            //val timer1 = getTimer1()
            val timer1 = async (IO){
                getTimer1() }
            //textView.text = getTimer1().toString().trim()
            val timer2 = async (IO){
                getTimer2() }
            //textView.text = timer2.toString()
            val total = timer1.await() + timer2.await()
           // textView.text = total.toString()
            Toast.makeText(applicationContext,"Total : $total",Toast.LENGTH_SHORT).show()
            Log.i("MyTag","Timer total ist: $total")
        }
    }
}
private suspend fun getTimer1():Int{
    delay(timeMillis =  11000)
    Log.i("MyTag","Timer 1")
    return 550
}

private suspend fun getTimer2():Int{
    delay(timeMillis = 8000)
    Log.i("MyTag","Timer 2")
    return 350
}

