package com.rhbekti.yobo.di

import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): YoboRepository {
        val apiService = ApiConfig.getApiService()
        return YoboRepository.getInstance(apiService)
    }
}