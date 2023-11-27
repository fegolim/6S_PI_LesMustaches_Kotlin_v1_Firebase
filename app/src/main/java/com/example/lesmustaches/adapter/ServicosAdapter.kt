package com.example.lesmustaches.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesmustaches.databinding.ServicosItemBinding
import com.example.lesmustaches.model.Servicos

class ServicosAdapter(private val context: Context, private val listaServicos: MutableList<Servicos>):
    RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ServicosViewHolder(itemLista)
    }
    override fun getItemCount() = listaServicos.size

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        holder.imgServico.setImageResource(listaServicos[position].img!!)
        holder.txtServico.text = listaServicos[position].nome
    }
    inner class ServicosViewHolder(binding: ServicosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServico = binding.imgServico
        val txtServico = binding.txtServico
    }
}

/*class ServicosAdapter (private val context: Context, private val listaServicos: MutableList<Servicos>):
    RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ServicosViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        val servico = listaServicos[position]
        holder.imgServico.setImageResource(servico.img!!)
        //holder.txtServico.text = servico.nome
    }

    override fun getItemCount() = listaServicos.size

    /*override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        holder.imgServico.setImageResource(listaServicos[position].img!!)
        holder.txtServico.text = listaServicos[position].nome
    }*/

    inner class ServicosViewHolder(binding: ServicosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServicos = binding.imgServico
        val txtServicos = binding.txtServico
        //val txtServico = binding.imgServico

    }
}*/


