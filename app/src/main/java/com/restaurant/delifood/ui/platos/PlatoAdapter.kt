package com.restaurant.delifood.ui.platos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.ItemPlatoBinding
import com.restaurant.delifood.model.Plato
import com.squareup.picasso.Picasso

class PlatoAdapter(var platos:List<Plato> = listOf())
    : RecyclerView.Adapter<PlatoAdapter.PlatoAdapterViewHolder>() {

    lateinit var onClickPlato:(Plato)->Unit

    //XML
    //Data
    inner class PlatoAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding : ItemPlatoBinding = ItemPlatoBinding.bind(itemView)

        fun bind(plato : Plato) = with(binding) {

            tvNombrePlato.text = plato.name
            tvDescripcionPlato.text = plato.description
            tvPrecioPlato.text = plato.price.toString()

            //https://firebasestorage.googleapis.com/v0/b/restaurant-administrador-am.appspot.com/o/1599494869539.jpg?alt=media&token=ffa42398-b21c-49f2-99ea-af4bf15504f9
            Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/restaurant-administrador-am.appspot.com/o/${plato.avatar}?alt=media&token=ffa42398-b21c-49f2-99ea-af4bf15504f9")
                .into(imgPlato)

            root.setOnClickListener {
                onClickPlato(plato)
            }
        }
    }

    fun actualizarLista(platos:List<Plato>){
        this.platos = platos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plato,parent,false)
        return PlatoAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatoAdapterViewHolder, position: Int) {

        val plato = platos[position]
        holder.bind(plato)

    }

    override fun getItemCount(): Int {
        return platos.size
    }


}