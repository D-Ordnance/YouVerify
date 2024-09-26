package com.deeosoft.youverifytest.feature.onboarding.presentation.page

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.YouVerifyText

@Composable
fun OnboardingItem(model: OnboardingItemModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        YouVerifyText(
            modifier = Modifier.padding(bottom = 8.dp),
            content = model.title,
            color = R.color.accentColor,
            fontSize = 32.sp,
            lineHeight = 38.sp
        )
        YouVerifyText(
            content = model.description,
            color = R.color.accentColor,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = model.icon),
            contentDescription = model.title
        )
    }

}


data class OnboardingItemModel(
    @DrawableRes var icon: Int,
    var title: String,
    var description: String
)