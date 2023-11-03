package com.example.exampleflutter

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.exampleflutter.adapter.MainAdapter
import com.example.exampleflutter.data.ItemBean
import com.example.exampleflutter.databinding.ActivityMainBinding
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs
import java.io.Serializable
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        MainAdapter(arrayListOf(
            ItemBean("跳转Flutter页面") {
                // 官方跳转方法
                // 1.跳转flutter的主页
//                startActivity(FlutterActivity.createDefaultIntent(this))
                // 2.跳转flutter自定义路由页面
//                startActivity(FlutterActivity.withNewEngine().initialRoute("").build(this))

                // flutter_boost库管理路由跳转
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("homePage")
                        .build()
                )

            },
            ItemBean("跳转Flutter页面并携带参数"){
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("secondPage")
                        .arguments(mapOf<String,Any>("data" to "MainActivity to SecondPage"))
                        .build()
                )
            },
            ItemBean("跳转Flutter页面回传参数"){
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("thirdPage")
                        .arguments(mapOf<String,Any>("data" to "MainActivity to ThirdPage"))
                        .requestCode(111)
                        .build()
                )
            },
            ItemBean("跳转Flutter列表页面"){
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("listPage")
                        .build()
                )
            },
            ItemBean("跳转Flutter网络操作页面"){
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("networkPage")
                        .build()
                )
            },
            ItemBean("跳转Flutter屏幕适配页面"){
                FlutterBoost.instance().open(
                    FlutterBoostRouteOptions.Builder()
                        .pageName("screenAdaptPage")
                        .build()
                )
            },
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        adapter.setOnItemClickListener { _, _, position ->
            adapter.items[position].func.invoke()
        }

        val local1 = Locale("hu")
        val local2 = Locale("hu")
        LogUtils.e("local1 == local2  result ：${local1 == local2}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111){
            val map = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.extras?.getSerializable(
                    FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY,
                    Serializable::class.java) as? HashMap<*, *>
            }else {
                data?.extras?.getSerializable(FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY) as? HashMap<*, *>
            }
            ToastUtils.showLong(map.toString())
        }
    }

}