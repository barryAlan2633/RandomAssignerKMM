package com.example.randomassignerkmm.interactors.sidework_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteSidework(
    private val appCache: AppCache
) {

    fun execute(
        sideworkID:String
    ): Flow<DataState<List<Sidework>>> = flow {

        try {
            appCache.deleteSidework(sideworkID)
        } catch (e: Exception) {
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}