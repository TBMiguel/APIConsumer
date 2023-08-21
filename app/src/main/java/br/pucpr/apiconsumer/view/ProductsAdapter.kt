package br.pucpr.apiconsumer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucpr.apiconsumer.databinding.ProductItemBinding
import br.pucpr.apiconsumer.model.Product

class ProductsAdapter(private var produtos: MutableList<Product>):
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false).apply {
            return ProductViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        produtos[position].apply {
            holder.binding.titulo.text = this.title
            holder.binding.categoria.text = this.category.toString()
            holder.binding.preco.text = this.price.toString()
        }
    }

    override fun getItemCount() = produtos.size

    inner class ProductViewHolder(var binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        var titulo = binding.titulo
        var categoria = binding.categoria
        var preco = binding.preco
    }
}