package com.example.githubproblem.viewmodels

import com.example.githubproblem.data.GithubRepoDTO

data class MainViewState(
    var allGithubRepos: ArrayList<GithubRepoDTO>? = null
)
