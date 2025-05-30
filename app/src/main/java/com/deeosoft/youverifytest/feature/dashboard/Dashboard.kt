package com.deeosoft.youverifytest.feature.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.YouVerifyText

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
                topAppBar()
            }
        }
    }
}

@Composable
fun topAppBar() {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Column {
            YouVerifyText(content = "Hello, Jane", color = R.color.accentColor, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(2.dp))
            YouVerifyText(
                content = "Your financial journey starts here.",
                fontSize = 14.sp,
                color = R.color.accentColor
            )
        }
        Row {
            Image(painter = painterResource(id = R.mipmap.profile), contentDescription = "")
            Spacer(modifier = Modifier.width(3.dp))
            Image(
                painter = painterResource(id = R.mipmap.notification),
                contentDescription = ""
            )
        }
    }
}