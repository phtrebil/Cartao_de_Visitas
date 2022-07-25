package me.dio.cartodevisistas.ui

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import me.dio.cartodevisistas.App
import me.dio.cartodevisistas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repositorio)
    }

    private val adapter by lazy{Adapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCartao.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }
    private fun insertListeners(){
        binding.fabButton.setOnClickListener{
            val intent = Intent(this@MainActivity,AddCartaoDeVisitas::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            me.dio.cartodevisistas.util.Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this){ cartaoDeVisitas ->
            adapter.submitList(cartaoDeVisitas)
        }
    }
}