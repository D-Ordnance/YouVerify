package com.deeosoft.youverifytest.feature.onboarding.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deeosoft.youverifytest.R
import com.deeosoft.youverifytest.core.composable.OnboardActions
import com.deeosoft.youverifytest.core.composable.YouVerifyButton
import com.deeosoft.youverifytest.core.composable.YouVerifyText
import com.deeosoft.youverifytest.core.composable.YouVerifyTextButton
import com.deeosoft.youverifytest.feature.onboarding.presentation.page.OnboardingItem
import com.deeosoft.youverifytest.feature.onboarding.presentation.page.OnboardingItemModel
import com.deeosoft.youverifytest.feature.registration.presentation.page.Registration
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class Pager : ComponentActivity() {

    private val items = arrayOf(
        OnboardingItemModel(
            icon = R.drawable.trading_app,
            title = "Track Your \n" + "Expenses",
            description = "Get insights into where your money goes and make informed financial decisions."
        ),
        OnboardingItemModel(
            icon = R.drawable.growing_money,
            title = "Set Savings\n" + "Goals",
            description = "Whether it’s for a vacation, a new car, or an emergency fund, we help you stay on track."
        ),
        OnboardingItemModel(
            icon = R.drawable.financial_insight,
            title = "Get Financial \n" + "Insights",
            description = "Access detailed reports and analytics to make better financial choices."
        )
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                var isBottomSheetOpen by rememberSaveable {
                    mutableStateOf(false)
                }
                val bottomSheetState = rememberModalBottomSheetState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(255, 255, 255))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    ) {
                        MainPager(Modifier.weight(1f))
                        OnboardActions({ isBottomSheetOpen = true }, { signIn() })
                    }
                }




                if (isBottomSheetOpen) {
                    ModalBottomSheet(
                        sheetState = bottomSheetState,
                        onDismissRequest = { isBottomSheetOpen = false }) {
                        Column(
                            modifier = Modifier.padding(
                                top = 24.dp,
                                bottom = 64.dp,
                                start = 24.dp,
                                end = 24.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                YouVerifyText(
                                    modifier = Modifier.weight(1f),
                                    content = "Create an account",
                                    fontSize = 22.sp,
                                    lineHeight = 18.sp,
                                    color = R.color.titleColor
                                )
                                Image(
                                    modifier = Modifier.clickable {
                                        isBottomSheetOpen = false
                                    },
                                    painter = painterResource(id = R.drawable.close),
                                    contentDescription = "Close"
                                )
                            }

                            YouVerifyText(
                                modifier = Modifier.padding(bottom = 24.dp),
                                content = "By pressing accept below you agree to our Terms and Conditions. You can find out more about how we use your data in our Privacy Policy",
                                color = R.color.titleColor,
                                fontSize = 18.sp,
                                lineHeight = 26.sp
                            )

                            YouVerifyButton(
                                modifier = Modifier.fillMaxWidth(),
                                content = {
                                    YouVerifyText(
                                        lineHeight = 22.sp,
                                        fontSize = 18.sp,
                                        content = "Accept and Continue",
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
                                singUp()
                            }

                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MainPager(modifier: Modifier) {
        val state = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            3
        }

        LaunchedEffect(Unit) {
            while (true) {
                animateToNextPage(state)
            }
        }



        Column(modifier = modifier) {
            PagerIndicator(state = state)
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = false,
                state = state

            ) {
                OnboardingItem(model = items[state.currentPage])
            }
        }


    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
    @Composable
    fun PagerIndicator(state: PagerState) {
        FlowRow(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            Arrangement.Center
        ) {
            repeat(state.pageCount) {
                if (state.currentPage == it) {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(R.drawable.active_indicator),
                        contentDescription = "Active"
                    )
                } else {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(R.drawable.in_active_indicator),
                        contentDescription = "Active"
                    )
                }
            }
        }
    }


    private fun singUp() {
        startActivity(Intent(this, Registration::class.java))
    }

    private fun signIn() {
        Toast.makeText(this, "Show something...", Toast.LENGTH_LONG).show()
    }

    @OptIn(ExperimentalFoundationApi::class)
    private suspend fun animateToNextPage(state: PagerState) {
        delay(1500)
        val next = (state.currentPage + 1) % 3
        state.animateScrollToPage(next)
    }
}