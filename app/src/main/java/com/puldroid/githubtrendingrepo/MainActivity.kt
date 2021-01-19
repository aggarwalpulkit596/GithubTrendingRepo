package com.puldroid.githubtrendingrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    makeNetworkCall()
    repoRv.layoutManager = LinearLayoutManager(this)

  }

  private fun makeNetworkCall() {
    val okHttpClient = OkHttpClient()
    val request = Request.Builder().url("https://hackertab.pupubird.com/repositories").build()

    okHttpClient.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        //Some error handling
      }

      override fun onResponse(call: Call, response: Response) {
        //Actual response code
        val gson = Gson()
        val listType = object : TypeToken<List<com.puldroid.githubtrendingrepo.Response>>() {}.type
        val list: List<com.puldroid.githubtrendingrepo.Response> =
          gson.fromJson(response.body?.string(), listType)
        runOnUiThread {
          val adapter = RepositoryAdapter(list)
          repoRv.adapter = adapter
        }
      }

    })
  }
}