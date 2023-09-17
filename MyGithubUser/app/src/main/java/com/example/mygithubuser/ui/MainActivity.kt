package com.example.mygithubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.data.response.GithubResponse
import com.example.mygithubuser.data.response.ItemsItem
import com.example.mygithubuser.data.retrofit.ApiConfig
import com.example.mygithubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
        private var USERNAME = "nanda"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    USERNAME = searchBar.text.toString()
                    false
                }
        }

        //sembunyikan actionbar
        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        findUser()
    }

    private fun findUser() {
        showLoading(true)
        val client = ApiConfig.getApiService().getUsers(USERNAME)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody.items)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.e(TAG, "API request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }



    private fun setUserData(user: List<ItemsItem?>?) {
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding.rvUser.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}