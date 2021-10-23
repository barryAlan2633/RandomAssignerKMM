package com.example.randomassignerkmm.android.presentation.sidework_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomassignerkmm.android.presentation.components.ButtonView
import com.example.randomassignerkmm.android.presentation.components.SideWorkCard
import com.example.randomassignerkmm.presentation.sidework_list.AppState


@Composable
fun SideWorkListView(state: AppState) {


    LazyColumn(modifier = Modifier
        .padding(30.dp)
        .padding(top = 50.dp)
        .fillMaxWidth()){
        items(state.shuffledSideworks){sideWork->
            SideWorkCard(sideWork)
        }
    }
}