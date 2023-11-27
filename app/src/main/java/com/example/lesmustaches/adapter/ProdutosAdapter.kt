package com.example.lesmustaches.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesmustaches.databinding.ProdutosItemBinding
import com.example.lesmustaches.model.Produtos

class ProdutosAdapter(private val context: Context, private val listaProdutos: MutableList<Produtos>):
    RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
        val itemLista = ProdutosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProdutosViewHolder(itemLista)
    }
    override fun getItemCount() = listaProdutos.size

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        holder.imgProduto.setImageResource(listaProdutos[position].img!!)
        holder.txtProduto.text = listaProdutos[position].nome
    }
    inner class ProdutosViewHolder(binding: ProdutosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgProduto = binding.imgProduto
        val txtProduto = binding.txtProduto
    }
}

