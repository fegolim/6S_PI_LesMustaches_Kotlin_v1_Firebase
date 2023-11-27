package com.example.lesmustaches.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesmustaches.MainActivity
import com.example.lesmustaches.R
import com.example.lesmustaches.adapter.ProdutosAdapter
import com.example.lesmustaches.adapter.ServicosAdapter
import com.example.lesmustaches.databinding.ActivityHomeBinding
import com.example.lesmustaches.model.Produtos
import com.example.lesmustaches.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private lateinit var produtosAdapter: ProdutosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()
    private val listaProdutos: MutableList<Produtos> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo(a), $nome!"
        val recyclerViewServicos = binding.recyclerViewServicos
        val recyclerViewProdutos: RecyclerView = binding.recyclerViewProdutos
        recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        recyclerViewProdutos.layoutManager = GridLayoutManager(this, 3)

        //= GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        produtosAdapter = ProdutosAdapter(this,listaProdutos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewProdutos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        recyclerViewProdutos.adapter = produtosAdapter
        getServicos()
        getProdutos()

        binding.btAgendar.setOnClickListener{
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
        }
        binding.btSair.setOnClickListener {
            navegarPraLogin("SAIR")
        }
    }

    private fun navegarPraLogin(nome: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
        finish()
    }
    private fun getServicos(){
        val servico1 = Servicos(R.drawable.cabelo,"Cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.barba,"Barba")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.tintura,"Tintura")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.tratamento,"Tratamento")
        listaServicos.add(servico4)
    }
    private fun getProdutos(){
        val produtos1 = Produtos(R.drawable.prod01,"Shampoo")
        listaProdutos.add(produtos1)

        val produtos2 = Produtos(R.drawable.prod07,"Condicionador")
        listaProdutos.add(produtos2)

        val produtos3 = Produtos(R.drawable.prod08,"Pomada")
        listaProdutos.add(produtos3)

        val produtos4 = Produtos(R.drawable.prod09,"Tônico")
        listaProdutos.add(produtos4)

        val produtos5 = Produtos(R.drawable.prod02,"Barba")
        listaProdutos.add(produtos5)

        val produtos6 = Produtos(R.drawable.prod03,"Kit")
        listaProdutos.add(produtos6)

        val produtos7 = Produtos(R.drawable.prod05,"Cuidado com a pele")
        listaProdutos.add(produtos7)

        val produtos8 = Produtos(R.drawable.prod14,"Tinturas")
        listaProdutos.add(produtos8)

        val produtos9 = Produtos(R.drawable.prod13,"Acessórios")
        listaProdutos.add(produtos9)

    }
}