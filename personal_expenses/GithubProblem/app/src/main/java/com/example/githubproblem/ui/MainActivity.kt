package com.example.githubproblem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubproblem.R
import com.example.githubproblem.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    val mainViewModel: MainViewModel by viewModel()
    lateinit var mAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repo_list.layoutManager = LinearLayoutManager(applicationContext)

        attachEvents()

        mainViewModel.fetchAllGithubRepos()
    }

    private fun attachEvents() {
        mainViewModel.mainViewState.observe(this, androidx.lifecycle.Observer { viewState ->
            viewState.allGithubRepos?.let { repos ->
                mAdapter = RepoAdapter(repos)
                repo_list.adapter = mAdapter
            }
        })
    }
}
