package pt.uevora.di.atividade5.data

import android.util.Log
import pt.uevora.di.atividade5.data.cb.DataRetriever
import pt.uevora.di.atividade5.data.model.Breed
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "DogsAPIClient"

object DogsAPIClient {

    private val apiDog: DogsAPI by lazy {
        setup()
    }

    private fun setup(): DogsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    fun getListOfBreeds(listener: DataRetriever) {
        apiDog.getBreedsList().enqueue(object : Callback<List<Breed>> {
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    listener.onDataFetchedSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.e(TAG, "onResponse: ${t.message}")
            }
        })
    }
}