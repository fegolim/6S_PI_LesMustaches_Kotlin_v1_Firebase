package com.example.lesmustaches

//import androidx.compose.material3.Snackbar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lesmustaches.databinding.ActivityMainBinding
import com.example.lesmustaches.view.Home
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener{
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                nome.isEmpty() -> {
                    mensagem(it,"Coloque o seu nome!")
                }senha.isEmpty() -> {
                mensagem(it, "Preencha a senha!")
                }senha.length <=5 -> {
                mensagem(it, "A senha precisa ter pelo menos 6 caracteres")
                }else -> {
                // Oculta o teclado
                navegarPraHome(nome)
                }
            }
        }
    }
    private fun mensagem(view: View, mensagem: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
    private fun navegarPraHome(nome: String){
        val intent = Intent(this, Home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

}

/*class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LesMustachesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LesMustachesTheme {
        Greeting("Android")
    }
}*/