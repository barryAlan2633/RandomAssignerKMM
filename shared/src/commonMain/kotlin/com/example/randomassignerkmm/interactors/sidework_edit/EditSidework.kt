package com.example.randomassignerkmm.interactors.sidework_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.GenericMessageInfo
import com.example.randomassignerkmm.domain.model.PositiveAction
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.model.UIComponentType
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditSidework(
    private val appCache: AppCache
) {

    fun execute(
        sidework: Sidework
    ): Flow<DataState<List<Sidework>>> = flow {



        try {
            if (sidework.name.trim() == "") {//todo add trim on other parts
                throw Exception("Your new sidework must have a name!")
            }

            appCache.insertSidework(sidework)

            emit(
                DataState.data(
                    message = null,
                    data = appCache.getAllSideworks().sortedBy { it.name })
            )

        } catch (e: Exception) {
            emit(
                DataState.error<List<Sidework>>(
                    message = GenericMessageInfo.Builder()
                        .id("EditSideworks.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                        .positive(
                            PositiveAction(
                                positiveBtnTxt = "OK",
                                onPositiveAction = {}
                            )
                        )
                )
            )
        }
    }
}