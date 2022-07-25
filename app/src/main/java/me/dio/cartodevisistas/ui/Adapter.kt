package me.dio.cartodevisistas.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.cartodevisistas.data.CartaoDeVisitas
import me.dio.cartodevisistas.databinding.ItemCartaoDeVisitasBinding

class Adapter :ListAdapter<CartaoDeVisitas, Adapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartaoDeVisitasBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCartaoDeVisitasBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartaoDeVisitas) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvNomeEmpresa.text = item.empresa
            binding.cdConteudo.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.cdConteudo.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<CartaoDeVisitas>() {
    override fun areItemsTheSame(oldItem: CartaoDeVisitas, newItem: CartaoDeVisitas) = oldItem == newItem
    override fun areContentsTheSame(oldItem: CartaoDeVisitas, newItem: CartaoDeVisitas) =
        oldItem.id == newItem.id
}