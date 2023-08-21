package br.pucpr.apiconsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.pucpr.apiconsumer.api.ProductsInterface
import br.pucpr.apiconsumer.databinding.ActivityMainBinding
import br.pucpr.apiconsumer.model.Product
import br.pucpr.apiconsumer.view.ProductsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapterProdutos: ProductsAdapter

    private val URL_BASE_FAKE_STORE_API = "https://fakestoreapi.com"

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configurarFloatActionButton()
    }

    private fun loadRecyclerView(produtos: MutableList<Product>) {
        LinearLayoutManager(this).apply {
            this.orientation = LinearLayoutManager.VERTICAL
            binding.productsRecycleView.layoutManager = this

            adapterProdutos = ProductsAdapter(produtos).apply {
                binding.productsRecycleView.adapter = this
            }
        }
    }

    private fun configurarFloatActionButton() {
        binding.inputButton.setOnClickListener {
            chamarAPI()
        }
    }

    private fun chamarAPI() {
        getAllData()
    }

    private fun getAllData() {
        var retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE_FAKE_STORE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsInterface::class.java)

        var productsData = retrofit.getProducts()

        productsData.enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                var data = response.body()

                Log.d("data", data.toString())
                var produtos = data!!.toMutableList()
                loadRecyclerView(produtos)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}