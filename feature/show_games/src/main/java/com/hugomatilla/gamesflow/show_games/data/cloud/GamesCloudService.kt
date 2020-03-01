package com.hugomatilla.gamesflow.show_games.data.cloud

import com.hugomatilla.data.GamesCloud
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.rawg.io/api/"


interface IGamesCloudService {
    @GET("games")
    fun getProjects(): Call<GamesCloud>
}

class GamesCloudService {
    fun create(): IGamesCloudService {
        return getRestService().create(IGamesCloudService::class.java)
    }

    private fun getRestService(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }
}

fun IGamesCloudService.getAllGames(
    onSuccess: (games: GamesCloud) -> Unit,
    onError: (error: String) -> Unit
) {
    getProjects().enqueue(object : Callback<GamesCloud> {
        override fun onResponse(
            call: Call<GamesCloud>,
            response: Response<GamesCloud>
        ) {
            if (response.isSuccessful && response.body() != null)
                onSuccess(response.body()!!)
            else
                onError(response.errorBody()?.string() ?: "Unknown error")
        }

        override fun onFailure(call: Call<GamesCloud>, t: Throwable) {
            onError(t.message ?: "Unknown error")
        }
    })
}