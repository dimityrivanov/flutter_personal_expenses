package com.example.githubproblem.data

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    companion object {
        private const val GET_USER_PROJECTS = "orgs/square/repos"
    }

    @GET(GET_USER_PROJECTS)
    fun fetchGithubRepos(): Call<ArrayList<GithubRepoDTO>>
}