package com.jaimegc.dragonballmodularization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaimegc.dragonballmodularization.libraries.navigator.NavigationActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(500)
            startActivity(NavigationActions.navigateToHomeScreen(this@MainActivity))
            finish()
        }
    }
}