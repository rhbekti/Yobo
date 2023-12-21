package com.rhbekti.yobo.data

import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.data.remote.retrofit.ApiService
import com.rhbekti.yobo.model.BookRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class YoboRepository constructor(
    private val apiService: ApiService,
) {
    suspend fun getBookByAuthor(): Flow<Result<List<BookItems>>> = flow {
        emit(Result.Loading)
        try {
            val author = apiService.getBookByAuthor().data
            emit(Result.Success(author))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getAllBooks(bookTitles: BookRequest): Flow<Result<List<BookItems>>> = flow {
        emit(Result.Loading)
        try {
            val books = apiService.getBooks(bookTitles).data
            emit(Result.Success(books))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getBookByYear(): Flow<Result<List<BookItems>>> = flow {
        emit(Result.Loading)
        try {
            val books = apiService.getBookByYear().data
            emit(Result.Success(books))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun getBookById(id: String): Flow<Result<List<BookItems>>> = flow {
        emit(Result.Loading)
        try {
            val books = apiService.getDetailBook(id).data
            emit(Result.Success(books))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: YoboRepository? = null

        fun getInstance(
            apiService: ApiService
        ): YoboRepository = instance ?: synchronized(this) {
            YoboRepository(
                apiService
            ).apply { instance = this }
        }
    }
}