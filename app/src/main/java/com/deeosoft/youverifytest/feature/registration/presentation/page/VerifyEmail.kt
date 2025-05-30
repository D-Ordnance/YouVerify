package com.deeosoft.youverifytest.feature.registration.presentation.page

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.CountdownTimer
import com.deeosoft.youverifytest.core.composable.YouVerifyButton
import com.deeosoft.youverifytest.core.composable.YouVerifyText
import com.deeosoft.youverifytest.core.composable.YouVerifyTextButton
import com.deeosoft.youverifytest.core.composable.YouVerifyTextField
import com.deeosoft.youverifytest.feature.login.presentation.page.EnterPasscode

class VerifyEmail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            YouVerifyText(
                                modifier = Modifier.padding(end = 8.dp),
                                content = "Check your email!",
                                fontSize = 22.sp,
                                lineHeight = 32.sp,
                                color = R.color.titleColor
                            )
                            Image(
                                painter = painterResource(id = R.mipmap.party_popper),
                                contentDescription = "Party Popper"
                            )
                        }
                        YouVerifyText(
                            modifier = Modifier.padding(bottom = 32.dp),
                            content = """We have sent an email to 
                                |janedoe@gmail.com. Please remember to check your 
                                |inbox as well as the spam folder.
                                |
                                |Please enter the verification code below 
                                |to continue with your account.""".trimMargin(),
                            fontSize = 18.sp,
                            lineHeight = 26.sp,
                            color = R.color.titleColor
                        )

                        val verificationCodeState = remember { mutableStateOf("") }
                        val emailState = remember { mutableStateOf("") }
                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "Enter verification code",
                            placeholder = {
                                YouVerifyText(
                                    content = "Enter code here",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = verificationCodeState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            verificationCodeState.value = it
                        }
                    }
                    VerifyEmail { signIn() }
                }
            }
        }
    }

    private fun signIn() {
        startActivity(Intent(this, EnterPasscode::class.java))
    }
}

@Composable
fun VerifyEmail(signUpAction: () -> Unit) {
    var isResendVisible by remember {
        mutableStateOf(false)
    }
    var timerCount by remember {
        mutableIntStateOf(50)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YouVerifyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            content = {
                YouVerifyText(
                    content = "Create an account",
                    color = R.color.white,
                    lineHeight = 22.sp,
                    fontSize = 18.sp
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
            onClick = signUpAction
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            YouVerifyText(
                content = "Didn’t recieve the email? ",
                color = R.color.placeholderColor,
                lineHeight = 22.sp,
                fontSize = 15.sp
            )
            Crossfade(
                targetState = isResendVisible,
                animationSpec = tween(1000),
                label = ""
            ) {
                if (it) {
                    YouVerifyTextButton(
                        content = "Resend",
                        onClick = { timerCount = 50; isResendVisible = false },
                        textColor = R.color.primary,
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline
                    )
                } else {
                    CountdownTimer(
                        totalTimeInSeconds = timerCount,
                        onFinished = {
                            isResendVisible = true
                        }
                    )
                }
            }
        }
    }
}