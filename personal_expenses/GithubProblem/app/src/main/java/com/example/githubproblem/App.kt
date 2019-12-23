package com.example.githubproblem

import android.app.Application
import com.example.githubproblem.data.GithubService
import com.example.githubproblem.helper.RetrofitRestClient
import com.example.githubproblem.viewmodels.MainViewModel
import com.example.githubproblem.viewmodels.MainViewState
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    val appModule = module {

        // single instance of GitHubRepository
        single {
            RetrofitRestClient.createService(GithubService::class.java)
        }

        // MyViewModel ViewModel
        viewModel {
            MainViewModel(get(), MainViewState())
        }
    }


    override fun onCreate() {
        super.onCreate()
        initRemoteClient()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun initRemoteClient() {
        val baseUrl = "https://api.github.com/"

        RetrofitRestClient
            .configureConverterFactory()
            .configureBaseUrl(baseUrl)
            .configureOkHttp(applicationContext)
    }
}