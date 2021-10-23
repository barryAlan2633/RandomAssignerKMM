package com.example.randomassignerkmm.datasource.cache

import java.util.*

actual class RandomUUID {
    actual fun create():String{
        return UUID.randomUUID().toString()
     }
}