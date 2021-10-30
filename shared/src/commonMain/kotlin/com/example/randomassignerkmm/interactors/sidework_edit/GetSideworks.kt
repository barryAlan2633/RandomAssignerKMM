package com.example.randomassignerkmm.interactors.sidework_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.GenericMessageInfo
import com.example.randomassignerkmm.domain.model.PositiveAction
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.model.UIComponentType
import com.example.randomassignerkmm.domain.util.CommonFlow
import com.example.randomassignerkmm.domain.util.DataState
import com.example.randomassignerkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSideworks(
    private val appCache: AppCache
) {
    fun execute(
    ): CommonFlow<DataState<List<Sidework>>> = flow {

        try {
            val sideworks = appCache.getAllSideworks()

            emit(
                DataState.data(
                    message = null,
                    data = sideworks.sortedBy { it.name })
            )
        } catch (e: Exception) {
            emit(
                DataState.error<List<Sidework>>(
                    message = GenericMessageInfo.Builder()
                        .id("GetSideworks.Error")
                        .title("Warning")
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