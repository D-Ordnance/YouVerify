package com.deeosoft.youverifytest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.deeosoft.youverifytest.core.helper.PreferenceHelper
import com.deeosoft.youverifytest.feature.login.presentation.page.Login
import com.deeosoft.youverifytest.feature.onboarding.presentation.Pager
import com.deeosoft.youverifytest.feature.registration.presentation.page.Registration

class Launcher : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hasSeenPager = PreferenceHelper.hasSeenPager(this)

        if (hasSeenPager) {
            startActivity(Intent(this, Login::class.java))
        } else {
            startActivity(Intent(this, Pager::class.java))
        }
        finish()
    }
}