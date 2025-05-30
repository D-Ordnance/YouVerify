package com.deeosoft.youverifytest.feature.login.presentation.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.YouVerifyButton
import com.deeosoft.youverifytest.core.composable.YouVerifyPinEntry
import com.deeosoft.youverifytest.core.composable.YouVerifyText
import com.deeosoft.youverifytest.core.composable.YouVerifyTopBar
import com.deeosoft.youverifytest.core.helper.PreferenceHelper
import com.deeosoft.youverifytest.feature.dashboard.Dashboard

class EnterPasscode : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var pin: String;
        val name = PreferenceHelper.getName(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Scaffold(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    topBar = {
                        YouVerifyTopBar(
                            onBackPressed = { super.onBackPressedDispatcher },
                            title = "Create your passcode"
                        )
                    }) { value ->
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 24.dp,
                            vertical = value.calculateTopPadding()
                        )
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            YouVerifyText(
                                modifier = Modifier
                                    .padding(bottom = 32.dp),
                                content = if (name.isEmpty()) """For a more secure and convenient way to view your account, create a 4-digit passcode now.""".trimMargin()
                                else "Welcome back\n ",
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                lineHeight = 26.sp,
                                color = R.color.titleColor
                            )
                            Spacer(modifier = Modifier.weight(0.3f))
                            YouVerifyPinEntry(
                                modifier = Modifier,
                                canSignOut = name.isNotEmpty(),
                                onSignOut = {},
                                onPinEntered = {
                                    pin = it
                                })
                            Spacer(modifier = Modifier.heightIn(min = 40.dp))
                            YouVerifyButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                content = {
                                    YouVerifyText(
                                        content = if (name.isNotEmpty()) "Continue" else "Create a Pin",
                                        fontSize = 16.sp,
                                        color = R.color.white
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0,
                                        128,
                                        128
                                    )
                                )
                            ) {
                                gotoDashboard()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun gotoDashboard() {
        startActivity(Intent(this, Dashboard::class.java))
    }
}