package com.example.lesmustaches.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lesmustaches.MainActivity
import com.example.lesmustaches.R
import com.example.lesmustaches.adapter.ServicosAdapter
import com.example.lesmustaches.databinding.ActivityHomeBinding
import com.example.lesmustaches.model.Servicos


class Home_backup : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()
   // private lateinit var binding: ActivityHomeBinding



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo(a), $nome!"
        val recyclerViewServicos = binding.recyclerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()

        binding.btAgendar.setOnClickListener{
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
        }
        binding.btSair.setOnClickListener {
            // Substitua "Seu Nome" pelo nome que você deseja passar para a função
            navegarPraLogin("SAIR")
        }
    }

    private fun navegarPraLogin(nome: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
        finish() // Adicione esta linha
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
}
