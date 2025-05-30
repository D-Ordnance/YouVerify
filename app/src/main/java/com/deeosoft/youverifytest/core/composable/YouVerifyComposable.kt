package com.deeosoft.youverifytest.core.composable

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import kotlinx.coroutines.delay

@Composable
fun YouVerifyTextButton(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    onClick: () -> Unit,
    @ColorRes textColor: Int
) {
    YouVerifyText(
        content = content,
        fontSize = fontSize,
        textAlign = textAlign,
        lineHeight = lineHeight,
        textDecoration = textDecoration,
        modifier = modifier.clickable(onClick = onClick),
        color = textColor
    )
}

@Composable
fun YouVerifyText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    @ColorRes color: Int
) {
    Text(
        modifier = modifier,
        text = content,
        textDecoration = textDecoration,
        fontSize = fontSize,
        textAlign = textAlign,
        lineHeight = lineHeight,
        fontFamily = YouVerifyFontFamily,
        fontWeight = FontWeight.Light,
        color = colorResource(id = color)
    )
}

@Composable
fun YouVerifyButton(
    modifier: Modifier = Modifier,
    content: @Composable() (RowScope.() -> Unit),
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    colors: ButtonColors,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        content = content,
        contentPadding = contentPadding,
        onClick = onClick,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouVerifyTextField(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    title: String,
    placeholder: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {

    Column {
        YouVerifyText(
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp,
            lineHeight = 23.sp,
            content = title,
            color = R.color.titleColor
        )
        TextField(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.textFieldOutlineColor),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            textStyle = TextStyle(
                color = Color(R.color.titleColor),
                background = Color.Transparent,
                fontSize = fontSize,
                lineHeight = lineHeight
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

}

@Composable
fun OnboardActions(
    buttonOneAction: () -> Unit,
    buttonTwoAction: () -> Unit,
    buttonTextOne: String,
    buttonTextTwo: String,
    buttonTwoActionText: String,
) {
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
                    content = buttonTextOne,
                    color = R.color.white,
                    lineHeight = 22.sp,
                    fontSize = 18.sp
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
            onClick = buttonOneAction
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            YouVerifyText(
                content = buttonTextTwo,
                color = R.color.accentColor,
                lineHeight = 22.sp,
                fontSize = 18.sp
            )
            YouVerifyTextButton(
                onClick = { buttonTwoAction() },
                content = buttonTwoActionText,
                lineHeight = 22.sp,
                textDecoration = TextDecoration.Underline,
                fontSize = 18.sp,
                textColor = R.color.primary
            )
        }
    }

}

@Composable
fun CountdownTimer(
    totalTimeInSeconds: Int,
    onFinished: () -> Unit = {}
) {
    var timeLeft by remember { mutableIntStateOf(totalTimeInSeconds) }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else {
            onFinished()
        }
    }

    YouVerifyText(
        content = "Resent code in ${timeLeft}s",
        color = R.color.accentColor,
        lineHeight = 22.sp,
        fontSize = 18.sp
    )
}

@Composable
fun YouVerifyPinEntry(
    modifier: Modifier,
    canSignOut: Boolean,
    pinLength: Int = 4,
    onSignOut: () -> Unit,
    onPinEntered: (String) -> Unit = {}
) {
    val pinValues = remember { mutableStateListOf(*Array(pinLength) { "" }) }
    var currentFocusIndex by remember { mutableIntStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(pinLength) { index ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.pinInputColor),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    YouVerifyText(
                        content = pinValues[index],
                        fontSize = 40.sp,
                        color = R.color.pinInputColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        CustomKeyboard(
            canSignOut = canSignOut,
            onNumberClick = { number ->
                if (currentFocusIndex < pinLength) {
                    pinValues[currentFocusIndex] = number
                    currentFocusIndex++
                    if (currentFocusIndex == pinLength) {
                        onPinEntered(pinValues.joinToString(""))
                    }
                }
            },
            onBackPressed = {
                if (currentFocusIndex > 0) {
                    currentFocusIndex--
                    pinValues[currentFocusIndex] = ""
                }
            },
            onSignOut = onSignOut
        )
    }
}

@Composable
fun CustomKeyboard(
    canSignOut: Boolean,
    onNumberClick: (String) -> Unit,
    onSignOut: () -> Unit,
    onBackPressed: () -> Unit
) {
    val keys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
    )
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        keys.forEach { row ->
            Box(
                Modifier
                    .background(colorResource(id = R.color.pinInputBackgroundColor))
                    .fillMaxWidth()
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { key ->
                        YouVerifyTextButton(
                            content = key,
                            textColor = R.color.accentColor,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            onClick = {
                                onNumberClick(key)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 14.dp)
                        )
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (canSignOut) {
                YouVerifyTextButton(
                    content = "Sign Out",
                    onClick = { onSignOut() },
                    textColor = R.color.primary,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            } else {
                Box(Modifier.weight(1f))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(colorResource(id = R.color.pinInputBackgroundColor))
            ) {
                YouVerifyTextButton(
                    content = "0",
                    onClick = { onNumberClick("0") },
                    textColor = R.color.accentColor,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable { onBackPressed() }
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                Image(
                    painter = painterResource(id = R.mipmap.delete),
                    contentDescription = "Party Popper"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouVerifyTopBar(onBackPressed: () -> Unit, title: String) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            Box(Modifier.clickable { onBackPressed() }) {
                Image(
                    painter = painterResource(id = R.mipmap.back),
                    contentDescription = "Party Popper"
                )
            }
        },
        title = {
            YouVerifyText(
                modifier = Modifier.padding(end = 8.dp),
                content = title,
                fontSize = 22.sp,
                lineHeight = 32.sp,
                color = R.color.titleColor
            )
        })
}


val YouVerifyFontFamily = FontFamily(
    Font(R.font.capriola_regular, FontWeight.Light),
)