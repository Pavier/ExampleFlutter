package com.example.exampleflutter

import android.app.Application
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.exampleflutter.channel.FlutterChannel
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.kongzue.dialogx.DialogX
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine


/**
 * @author pw
 * @date 2023/10/26 16:49
 *
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DialogX.init(this)
        FlutterBoost.instance().setup(this, object : FlutterBoostDelegate {
            override fun pushNativeRoute(options: FlutterBoostRouteOptions) {
                //这里根据options.pageName来判断你想跳转哪个页面，这里简单给一个
//                val intent = Intent(
//                    FlutterBoost.instance().currentActivity(),
//                    YourTargetAcitvity::class.java
//                )
//                FlutterBoost.instance().currentActivity()
//                    .startActivityForResult(intent, options.requestCode())
            }

            override fun pushFlutterRoute(options: FlutterBoostRouteOptions) {
                val intent = FlutterBoostActivity.CachedEngineIntentBuilder(
                    FlutterBoostActivity::class.java
                )
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                    .destroyEngineWithActivity(false)
                    .uniqueId(options.uniqueId())
                    .url(options.pageName())
                    .urlParams(options.arguments().apply { "local" to "zh" } ?: mapOf("local" to "zh"))
                    .build(FlutterBoost.instance().currentActivity())
                if(options.requestCode() == 0){
                    FlutterBoost.instance().currentActivity().startActivity(intent)
                }else {
                    startActivityForResult(FlutterBoost.instance().currentActivity(),intent,
                        options.requestCode(),null)
                }
            }

        }) { engine: FlutterEngine? ->
            engine?.dartExecutor?.binaryMessenger?.let {
                FlutterChannel(it)
            }
        }
    }
}