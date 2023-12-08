package com.rhbekti.yobo.data.remote.response

import com.google.gson.annotations.SerializedName

data class BooksResponse(

	@field:SerializedName("data")
	val data: List<BookItems> = emptyList(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class BookItems(

	@field:SerializedName("image")
	val photoUrl: Image? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("isbn")
	val isbn: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class Image(

	@field:SerializedName("s")
	val s: String? = null,

	@field:SerializedName("l")
	val l: String? = null,

	@field:SerializedName("m")
	val m: String? = null
)
