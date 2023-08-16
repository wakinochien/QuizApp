package com.agmoacademy.quizapp.network

import com.agmoacademy.quizapp.model.Quiz
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://opentdb.com/api.php/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getQuizList] method
 */
interface ApiService {
    @GET(".")
    suspend fun getQuizList(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String,
    ): Quiz
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object QuizApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
