package com.stocks.stockpricecompose.ui.theme

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stocks.stockpricecompose.presentation.viewmodel.MainActivityViewModel

@Composable
fun StockList() {
    val TAG = "MainActivity"
    val viewModel: MainActivityViewModel = hiltViewModel()
    val list by viewModel.mStocks.collectAsState()
    LazyColumn {
        list?.let {
            items(list.size) { index ->
                Card(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth()){

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .padding(10.dp)) {
                        Text(modifier = Modifier.align(TopStart),text = list.get(index).name)
                        Text(modifier = Modifier.align(TopEnd),text = "$" +list.get(index).price.toString())
                    }

                }
            }
        }

    }
}