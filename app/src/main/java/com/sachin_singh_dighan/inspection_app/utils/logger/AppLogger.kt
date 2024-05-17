package com.sachin_singh_dighan.inspection_app.utils.logger

import android.util.Log

class AppLogger : Logger {
    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}