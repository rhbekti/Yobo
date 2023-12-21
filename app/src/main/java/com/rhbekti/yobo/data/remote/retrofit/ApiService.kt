package com.rhbekti.yobo.data.remote.retrofit

import com.rhbekti.yobo.data.remote.response.BooksResponse
import com.rhbekti.yobo.model.BookRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("sort_author")
    suspend fun getBookByAuthor(): BooksResponse

    @Headers("Content-Type: application/json")
    @POST("recommendations")
    suspend fun getBooks(
        @Body request: BookRequest
    ): BooksResponse

    @GET("sort_year")
    suspend fun getBookByYear(): BooksResponse

    @GET("detail/{id}")
    suspend fun getDetailBook(
        @Path("id") id: String
    ): BooksResponse
}