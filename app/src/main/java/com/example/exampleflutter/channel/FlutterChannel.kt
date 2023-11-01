package com.example.exampleflutter.channel

import com.example.exampleflutter.ext.dismissLoading
import com.example.exampleflutter.ext.getLocalTimezone
import com.example.exampleflutter.ext.showLoading
import com.example.exampleflutter.ext.showToast
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @author pw
 * @date 2023/10/27 15:19
 *
 */
class FlutterChannel(flutterEngine: BinaryMessenger) :
    MethodChannel.MethodCallHandler {
    private val channelName = "com.example.myChannel"
    init {
        MethodChannel(flutterEngine,channelName).apply {
            setMethodCallHandler(this@FlutterChannel)
        }
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "showLoading" -> {
                val map = call.arguments as Map<*, *>
                showLoading(map["msg"].toString())
            }
            "showToast" -> {
                val map = call.arguments as Map<*, *>
                showToast(map["msg"].toString())
            }
            "dismissLoading" -> {
                dismissLoading()
            }
            "getLocalTimezone" -> {
                val timezoneId = getLocalTimezone()
                result.success(timezoneId)
            }

            else -> {
                result.notImplemented()
            }
        }
    }
}