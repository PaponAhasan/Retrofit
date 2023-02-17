package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myResponse.observe(this) {
            if (it.isSuccessful){
                Log.d(TAG, it.body()?.id.toString())
                Log.d(TAG, it.body()?.myUserId.toString())
                Log.d(TAG, it.body()?.title!!)
                Log.d(TAG, it.body()?.body!!)

                val textView = findViewById<TextView>(R.id.text_view)
                textView.text = it.body()?.title
            }
            else{
                Log.e(TAG, it.errorBody().toString())
            }
        }
    }
}