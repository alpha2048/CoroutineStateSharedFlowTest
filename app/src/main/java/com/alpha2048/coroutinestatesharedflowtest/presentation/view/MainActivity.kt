package com.alpha2048.coroutinestatesharedflowtest.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpha2048.coroutinestatesharedflowtest.R
import com.alpha2048.coroutinestatesharedflowtest.databinding.ActivityMainBinding
import com.alpha2048.coroutinestatesharedflowtest.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()
    private val adapter = MainAdapter(lifecycleScope)

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.progress.visibility = View.VISIBLE
        viewModel.loadData()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenStarted {
            viewModel.repoItems
                .collect {
                    binding.progress.visibility = View.INVISIBLE
                    adapter.setItem(it)
                }
        }

        lifecycleScope.launchWhenStarted {
            adapter.onClick
                .collect {
                    val uri = Uri.parse(it.htmlUrl)
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
        }

    }
}