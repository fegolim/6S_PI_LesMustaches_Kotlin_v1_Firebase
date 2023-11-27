package com.example.lesmustaches.view

/*TEM QUE ARRUMAR O TRATAMENTO DA DATA, NÃO PERMITIR AGENDAMENTO PRA ONTEM*/

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lesmustaches.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore


class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String=""
    private var hora: String=""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.dataPicker //Mexi aqui de datePicker para dataPicker
        datePicker.setOnDateChangedListener{_, year, monthOfYear,dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String


            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear+1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener{_, hourOfDay, minute ->

            val minuto: String

            if(minute < 10 ){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }
            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener{

            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3
            val barbeiro4 = binding.barbeiro4
            val barbeiro5 = binding.barbeiro5

        //    val currentDate = LocalDate.now()
        //    val tenDaysLater = currentDate.plusDays(10)

            when{
                hora.isEmpty() -> {
                    mensagem(it, "Por favor, preencha o horário!","#4D4D4D", 25.0f)
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it,"Les Mustaches está fechado - horário de atendimento das 08:00 as 19:00!","#4D4D4D", 20.0f)
                }
                data.isEmpty() -> {
                    mensagem(it,"Coloque uma data!","#4D4D4D", 25.0f)
                }

                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Bruh Diva's", data, hora)

                    /*mensagem(it,"Agendamento realizado com sucesso!","#5D5D5D", 25.0f)
                    // Navegue de volta para a Home*/
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("nome", nome)
                        startActivity(intent)
                    }, 1500)
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Franz Ferdnand's", data, hora)
                    /*mensagem(it,"Agendamento realizado com sucesso!","#5D5D5D", 25.0f)
                    // Navegue de volta para a Home*/
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("nome", nome)
                        startActivity(intent)
                    }, 1500)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Louis Louis", data, hora)
                    /*mensagem(it,"Agendamento realizado com sucesso!","#5D5D5D", 25.0f)
                    // Navegue de volta para a Home*/
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("nome", nome)
                        startActivity(intent)
                    }, 1500)
                }
                barbeiro4.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Prih Vega", data, hora)
                    /*mensagem(it,"Agendamento realizado com sucesso!","#5D5D5D", 25.0f)
                    // Navegue de volta para a Home*/
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("nome", nome)
                        startActivity(intent)
                    }, 1500)
                }
                barbeiro5.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Vincent Lamarra", data, hora)
                    /*mensagem(it,"Agendamento realizado com sucesso!","#5D5D5D", 25.0f)
                    // Navegue de volta para a Home*/
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("nome", nome)
                        startActivity(intent)
                    }, 1500)
                }
                else -> {
                    mensagem(it,"Escolha um barbeiro!","#4D4D4D", 25.0f)
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String, tamanho: Float){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textSize = tamanho
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        //snackbar.view.apply {
        //    findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.apply {
          //      textSize = tamanho
            //    typeface = Typeface.DEFAULT_BOLD
         //   }
        //}
        snackbar.show()
    }
    private fun salvarAgendamento(
        view: View,
        cliente: String,
        barbeiro: String,
        data: String,
        hora: String
    ) {

        val db = FirebaseFirestore.getInstance()

        val dataUser = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora,
        )

        db.collection("agendamento").document(cliente).set(dataUser).addOnCompleteListener {
            mensagem(view, "Agendamento realizado com sucesso!", "#80CBC4", 25.0f)
        }.addOnFailureListener {
            mensagem(view, "Erro ao salvar!", "#FF0000", 25.0f)
        }
    }
}
