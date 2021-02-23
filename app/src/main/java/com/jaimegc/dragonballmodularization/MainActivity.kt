package com.jaimegc.dragonballmodularization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaimegc.dragonballmodularization.libraries.navigator.NavigationActions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(NavigationActions.navigateToHomeScreen(this))
        finish()
    }
}