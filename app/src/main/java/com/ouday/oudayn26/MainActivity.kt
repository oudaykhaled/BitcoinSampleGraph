package com.ouday.oudayn26

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ouday.profile.presentation.ui.activity.ProfileActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            var intent = Intent(MainActivity@this, ProfileActivity::class.java)
            startActivity(intent)
        }, 500)

    }
}
