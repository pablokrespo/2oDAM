package com.example.ciclovida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado", "onStart")
    }

    override fun onResume() {
        super.onResume()
        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado", "onResume")
    }

    override fun onPause() {
        super.onPause()
        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado", "onPause")
    }

    override fun onStop() {
        super.onStop()
        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        //Imprimimos en la ventana del "Logcat"
        Log.d("Estado","onDestroy")
    }

}