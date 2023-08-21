package br.pucpr.apiconsumer.api

import br.pucpr.apiconsumer.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductsInterface {
    @GET("/products")
    fun getProducts() : Call<List<Product>>

}