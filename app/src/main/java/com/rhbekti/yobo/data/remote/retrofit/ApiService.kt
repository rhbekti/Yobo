package com.rhbekti.yobo.data.remote.retrofit

import com.rhbekti.yobo.data.remote.response.BooksResponse
import com.rhbekti.yobo.data.remote.response.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    suspend fun getCategories() : CategoryResponse

    @GET("books")
    suspend fun getBooks() : BooksResponse
}