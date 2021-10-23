package com.example.randomassignerkmm.datasource.cache

import platform.Foundation.NSUUID
import java.util.*

actual class RandomUUID {
    actual fun create():String{
        return NSUUID().UUIDString()
    }
}