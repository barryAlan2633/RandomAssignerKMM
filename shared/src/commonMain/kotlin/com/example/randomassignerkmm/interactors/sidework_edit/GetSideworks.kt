package com.example.randomassignerkmm.interactors.sidework_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSideworks (
    private val appCache: AppCache
) {
    fun execute(
    ): Flow<DataState<List<Sidework>>> = flow {

        try{
            val sideworks = appCache.getAllSideworks().sortedBy { it.name }
            print(sideworks)
            emit(DataState.data(message = null,data = sideworks))

        }catch (e:Exception){
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}