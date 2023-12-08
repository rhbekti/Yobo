package com.rhbekti.yobo.data

import android.util.Log
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.data.remote.response.CategoryItems
import com.rhbekti.yobo.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class YoboRepository constructor(
    private val apiService: ApiService
) {

    suspend fun getAllCategories(): Flow<Result<List<CategoryItems>>> = flow {
        emit(Result.Loading)
        try {
            val categories = apiService.getCategories().data
            emit(Result.Success(categories))
        } catch (e: Exception) {
            Log.d("yobo_repo", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getAllBooks(): Flow<Result<List<BookItems>>> = flow {
        emit(Result.Loading)
        try {
            val books = apiService.getBooks().data
            emit(Result.Success(books))
        } catch (e: Exception) {
            Log.d("yobo_repo", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: YoboRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): YoboRepository = instance ?: synchronized(this) {
            YoboRepository(
                apiService
            ).apply { instance = this }
        }
    }
}