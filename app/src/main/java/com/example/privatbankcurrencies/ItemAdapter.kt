package com.example.privatbankcurrencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privatbankcurrencies.databinding.ExchangeRateBinding
import com.example.privatbankcurrencies.item.ExchangeRate

class ItemAdapter(private val items: List<ExchangeRate>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ExchangeRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExchangeRate) {
            binding.tvCurrency1.text = item.baseCurrency
            binding.tvCurrency2.text = item.currency

            binding.tvBuy.text = item.purchaseRate.toString()
            binding.tvSale.text = item.saleRate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExchangeRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}