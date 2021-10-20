package com.example.randomassignerkmm.android.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomassignerkmm.sideW
import com.jetbrains.handson.kmm.shared.entity.Sidework


@Composable
fun SideWorkListView(sideWorks:List<Sidework>) {
    LazyColumn(modifier = Modifier.padding(30.dp).padding(top = 50.dp).fillMaxWidth()){
        items(sideWorks){sideWork->
            SideWorkCard(sideWork)
        }
    }
}