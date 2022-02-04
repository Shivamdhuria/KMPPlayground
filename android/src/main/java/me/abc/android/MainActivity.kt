package me.abc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import me.abc.library.Greeting
import android.widget.TextView
import me.abc.library.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
