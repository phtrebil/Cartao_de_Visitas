package me.dio.cartodevisistas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import me.dio.cartodevisistas.App
import me.dio.cartodevisistas.R
import me.dio.cartodevisistas.data.CartaoDeVisitas
import me.dio.cartodevisistas.databinding.ActivityAddCartaoDeVisitasBinding


class AddCartaoDeVisitas : AppCompatActivity() {

    private val binding by lazy { ActivityAddCartaoDeVisitasBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insetListenets()
    }

    private fun insetListenets(){
        binding.btnClose.setOnClickListener{
            finish()
        }
        binding.btnConfirm.setOnClickListener{
            val cartaoDeVisitas = CartaoDeVisitas(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString()
            )
            mainViewModel.insert(cartaoDeVisitas)
            Toast.makeText(this, R.string.label_show_succes, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}