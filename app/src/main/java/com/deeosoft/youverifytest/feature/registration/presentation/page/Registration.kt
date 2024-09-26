package com.deeosoft.youverifytest.feature.registration.presentation.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.OnboardActions
import com.deeosoft.youverifytest.core.composable.YouVerifyText
import com.deeosoft.youverifytest.core.composable.YouVerifyTextField

class Registration : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            YouVerifyText(
                                modifier = Modifier.padding(end = 8.dp),
                                content = "Let\'s get started!",
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
                            content = "Join us and start managing your finances with Fintrack today.",
                            fontSize = 18.sp,
                            lineHeight = 26.sp,
                            color = R.color.titleColor
                        )

                        val fullNameState = remember { mutableStateOf("") }
                        val emailState = remember { mutableStateOf("") }
                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "First & Last Name",
                            placeholder = {
                                YouVerifyText(
                                    content = "e.g John Doe",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = fullNameState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            fullNameState.value = it
                        }
                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "Email Address",
                            placeholder = {
                                YouVerifyText(
                                    content = "e.g email@mail.com",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = emailState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            emailState.value = it
                        }
                        YouVerifyTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            title = "Enter a referral code(optional)",
                            placeholder = {
                                YouVerifyText(
                                    content = "e.g email@mail.com",
                                    color = R.color.placeholderColor
                                )
                            },
                            value = emailState.value,
                            fontSize = 16.sp,
                            lineHeight = 23.sp
                        ) {
                            emailState.value = it
                        }
                    }

                    OnboardActions({ signUp() }, { login() })
                }
            }
        }
    }

    private fun signUp() {

    }

    private fun login() {

    }
}