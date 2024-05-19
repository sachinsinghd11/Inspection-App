package com.sachin_singh_dighan.inspection_app.utils

import android.view.View

typealias ItemClickListener<T> = (position: Int, data: T) -> Unit

typealias setNestedUi<T> = (recyclerview: T) -> Unit