package com.restaurant.delifood.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.delifood.R
import com.restaurant.delifood.databinding.ItemCategoriesBinding
import com.restaurant.delifood.model.Categories

class CategoriaAdapter(var categorias:List<Categories> = listOf(),
                       var onClickCategoria:(Categories)->Unit)
    : RecyclerView.Adapter<CategoriaAdapter.CategoriaAdapterViewHolder>() {

    inner class CategoriaAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val binding : ItemCategoriesBinding = ItemCategoriesBinding.bind(itemView)

        fun bind(categories: Categories) = with(binding) {

            tvNameCategorie.text = categories.name

            root.setOnClickListener {
                onClickCategoria(categories)
            }
        }
    }

    fun actualizarLista(categorias:List<Categories>){
        this.categorias = categorias
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaAdapterViewHolder {

        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_categories,parent,false)
        return CategoriaAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaAdapterViewHolder, position: Int) {

        val categoria = categorias[position]
        holder.bind(categoria)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

}