package com.example.randomassignerkmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomassignerkmm.domain.model.NegativeAction
import com.example.randomassignerkmm.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss?.invoke()
        },
        text = {
            if (description != null) {
                Text(
                    text = description,
                )
            }
        },
        title = {
            Text(
                text = title,
            )
        },
        buttons = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {

                if (negativeAction != null) {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            negativeAction.onNegativeAction()
                        }
                    ) {
                        Text(
                            text = negativeAction.negativeBtnTxt,
                        )
                    }
                }
                if (positiveAction != null) {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            onRemoveHeadFromQueue()
                        }
                    ) {
                        Text(
                            text = positiveAction.positiveBtnTxt,
                        )
                    }
                }
            }
        }
    )
}


