package com.example.githubproblem.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.githubproblem.data.GithubRepoDTO
import com.example.githubproblem.data.GithubService
import com.example.githubproblem.helper.BaseViewModel
import com.example.githubproblem.helper.SafeRequestExecutor.execute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainViewModel(
    private val mGithubService: GithubService,
    private val mViewState: MainViewState
) :
    BaseViewModel() {

    val mainViewState = MutableLiveData<MainViewState>().apply {
        value = mViewState
    }

    fun fetchAllGithubRepos() {
        mUiScope.launch {
            val remoteUserProjects = async(Dispatchers.IO) {
                execute(mGithubService.fetchGithubRepos())
            }.await() as? ArrayList<GithubRepoDTO>?

            mainViewState.value =
                mViewState.copy(
                    allGithubRepos = remoteUserProjects
                )
        }
    }


}