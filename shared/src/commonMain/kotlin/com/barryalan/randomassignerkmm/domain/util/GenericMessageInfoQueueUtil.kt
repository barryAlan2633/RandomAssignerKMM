package com.barryalan.randomassignerkmm.domain.util

import com.barryalan.randomassignerkmm.domain.model.GenericMessageInfo



//KMM cannot use extension functions therefore we need to build it this way to use it in swift
class GenericMessageInfoQueueUtil() {

    //Takes duplicates out of the queue
    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo:GenericMessageInfo
    ):Boolean{
        for(item in queue.items){
            if(item.id == messageInfo.id){
                return true
            }
        }

        return false
    }
}