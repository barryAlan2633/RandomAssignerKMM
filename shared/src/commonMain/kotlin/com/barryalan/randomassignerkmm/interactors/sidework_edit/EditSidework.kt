package com.barryalan.randomassignerkmm.interactors.sidework_edit

import com.barryalan.randomassignerkmm.datasource.cache.AppCache
import com.barryalan.randomassignerkmm.domain.model.GenericMessageInfo
import com.barryalan.randomassignerkmm.domain.model.PositiveAction
import com.barryalan.randomassignerkmm.domain.model.Sidework
import com.barryalan.randomassignerkmm.domain.model.UIComponentType
import com.barryalan.randomassignerkmm.domain.util.CommonFlow
import com.barryalan.randomassignerkmm.domain.util.DataState
import com.barryalan.randomassignerkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class EditSidework(
    private val appCache: AppCache
) {

    fun execute(
        sidework: Sidework
    ): CommonFlow<DataState<List<Sidework>>> = flow {



        try {
            if (sidework.name== "") {
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
    }.asCommonFlow()
}