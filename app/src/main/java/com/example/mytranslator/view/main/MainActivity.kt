package com.example.mytranslator.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslator.R
import com.example.mytranslator.databinding.ActivityMainBinding
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.view.base.BaseActivity
import com.example.mytranslator.view.main.adapter.MainAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var model: MainViewModel

    // TODO проговорить эту конструкцию
    private val observer = Observer<AppState> { renderData(it) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AndroidInjection.inject(this)
        setContentView(binding.root)

        initButtonSearchFab()

        model = viewModelFactory.create(MainViewModel::class.java)
        model.subscribe().observe(this, Observer<AppState> { renderData(it) })

    }

    private fun initButtonSearchFab() {
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(

                object : SearchDialogFragment.OnSearchClickListener {
                    override fun onClick(searchWord: String) {
                        // TODO проговорить эту конструкцию
                        model.getData(searchWord, true).observe(this@MainActivity, observer)
                    }
                })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData("hi", true).observe(this, observer)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}