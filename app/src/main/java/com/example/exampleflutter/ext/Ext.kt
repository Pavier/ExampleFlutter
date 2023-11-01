package com.example.exampleflutter.ext

import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.ToastUtils
import com.kongzue.dialogx.dialogs.WaitDialog
import java.util.Calendar

/**
 * @author pw
 * @date 2023/10/27 15:25
 *
 */
fun showLoading(msg : String){
    WaitDialog.show(msg)
}

fun showToast(msg : String){
    ToastUtils.showShort(msg)
}

fun dismissLoading(){
    WaitDialog.dismiss()
}

fun getLocalTimezone() : String{
    return Calendar.getInstance().timeZone.id
}