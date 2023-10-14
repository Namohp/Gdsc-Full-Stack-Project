package com.example.gdsc_adypu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var gdsc : ImageButton
    private lateinit var rsvp : ImageButton
    private lateinit var team : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gdsc = findViewById(R.id.GDSC)
        rsvp = findViewById(R.id.RSVP)
        team = findViewById(R.id.TEAM)

        gdsc.setOnClickListener{
            startActivity((Intent(this@MainActivity , AboutGdscActivity::class.java)))
            finish()
        }
        rsvp.setOnClickListener{
            startActivity((Intent(this@MainActivity , RsvpActivity::class.java)))
            finish()
        }
        team.setOnClickListener{
            startActivity((Intent(this@MainActivity , TeamActivity::class.java)))
            finish()
        }
    }
}