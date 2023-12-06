package com.rhbekti.yobo.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("data")
	val data: List<CategoryItems> = emptyList(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CategoryItems(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
