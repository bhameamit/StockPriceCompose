package com.stocks.stockpricecompose.ui.theme

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.stocks.stockpricecompose.R
import com.stocks.stockpricecompose.presentation.viewmodel.MainActivityViewModel

@Composable
fun MainUi() {
    val viewModel: MainActivityViewModel = hiltViewModel()
    LaunchedEffect(true){
        viewModel.fetchAllStocks()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = stringResource(R.string.app_name))},

            )
        },
    ) { innerPadding -> StockList()

    }

}