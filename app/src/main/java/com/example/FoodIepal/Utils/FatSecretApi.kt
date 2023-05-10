package com.example.FoodIepal.Utils

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface FatSecretAPI {
    @POST("rest/server.api")
    @Headers("Content-Type: application/json")
    fun searchFoods(
        @Query("method") method: String,
        @Query("search_expression") searchExpression: String,
        @Body body: RequestBody
    ): Call<ResponseBody>
}

data class RequestBody(val auth: AuthData)
data class AuthData(val bearer: String)

fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://platform.fatsecret.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(FatSecretAPI::class.java)

    val method = "foods.search"
    val searchExpression = "toast"
    val bearer = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGQUQ4RTE5MjMwOURFRUJCNzBCMzU5M0E2MDU3OUFEMUM5NjgzNDkiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJYNjJPR1NNSjN1dTNDeldUcGdWNXJSeVdnMGsifQ"

    val authData = AuthData(bearer)
    val requestBody = RequestBody(authData)

    val call = api.searchFoods(method, searchExpression, requestBody)
    val response = call.execute()
    if (response.isSuccessful) {
        val responseBody = response.body()
        Log.e("food", response.toString())
    } else {
        val errorBody = response.errorBody()?.string()
        Log.e("Food Errod", errorBody.toString())
    }
}