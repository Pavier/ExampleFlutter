package com.example.exampleflutter.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author pw
 * @date 2023/10/26 11:31
 *
 */
@Parcelize
data class ItemBean(
    var title : String,
    val func : () -> Unit
) : Parcelable