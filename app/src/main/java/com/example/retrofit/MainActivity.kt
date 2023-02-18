package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)
        val numberBtn = findViewById<Button>(R.id.numberBtn)
        val numberET = findViewById<EditText>(R.id.numberET)
        val numberTV = findViewById<TextView>(R.id.numberTV)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myResponse.observe(this) {
            if (it.isSuccessful) {
                Log.d(TAG, it.body()?.id.toString())
                Log.d(TAG, it.body()?.userId.toString())
                Log.d(TAG, it.body()?.title!!)
                Log.d(TAG, it.body()?.body!!)
                textView.text = it.body()?.title
            } else {
                textView.text = it.errorBody().toString()
                Log.e(TAG, it.code().toString())
            }
        }

        /*     */
        numberBtn.setOnClickListener {
            val editTextNumber = numberET.text.toString()
            viewModel.getPostNumber(editTextNumber.toInt())
            viewModel.myResponseNumber.observe(this) {
                if (it.isSuccessful) {
                    numberTV.text = it.body()?.id.toString()
                }else{
                    numberTV.text = it.code().toString()
                }
            }
        }

        /* List Of Post */
        numberBtn.setOnClickListener {
            val editTextNumber = numberET.text.toString()
            viewModel.getNumberPosts(editTextNumber.toInt(), "id", "desc")
            viewModel.myResponsePosts.observe(this) {
                if (it.isSuccessful) {
                    numberTV.text = it.body()?.toString()
                    it.body()?.forEach {
                        Log.d(TAG, it.userId.toString())
                        Log.d(TAG, it.id.toString())
                        Log.d(TAG, it.title)
                        Log.d(TAG, it.body)
                        Log.d(TAG, "-----------------")
                    }
                }else{
                    numberTV.text = it.code().toString()
                }
            }
        }

        /* List Of Post */
        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"
        numberBtn.setOnClickListener {
            val editTextNumber = numberET.text.toString()
            viewModel.getNumberAllPost(editTextNumber.toInt(), options)
            viewModel.myResponseAllPost.observe(this) {
                if (it.isSuccessful) {
                    numberTV.text = it.body()?.toString()
                    it.body()?.forEach {
                        Log.d(TAG, it.userId.toString())
                        Log.d(TAG, it.id.toString())
                        Log.d(TAG, it.title)
                        Log.d(TAG, it.body)
                        Log.d(TAG, "-----------------")
                    }
                }else{
                    numberTV.text = it.code().toString()
                }
            }
        }
    }
}