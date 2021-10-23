package com.example.randomassignerkmm.android.presentation.sidework_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideworkListScreen() {
    LazyColumn{
        items(100){ sideworkid->
            Row(
               modifier = Modifier
                   .fillMaxWidth()
            ){
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "SideworkId = ${sideworkid}"
                )
            }

        }
    }
}