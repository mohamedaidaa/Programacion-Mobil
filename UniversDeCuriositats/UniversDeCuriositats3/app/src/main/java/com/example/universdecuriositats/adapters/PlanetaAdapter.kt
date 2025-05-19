package com.example.universdecuriositats.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.universdecuriositats.R
import com.example.universdecuriositats.data.Planeta

class PlanetaAdapter(
    private val planetes: List<Planeta>,
    private val onPlanetaClick: (Planeta) -> Unit
) : RecyclerView.Adapter<PlanetaAdapter.PlanetaViewHolder>() {

    inner class PlanetaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imatge: ImageView = view.findViewById(R.id.nomImatge)
        private val nom: TextView = view.findViewById(R.id.nom)
        private val desc: TextView = view.findViewById(R.id.descripcioBreu)

        fun bind(planeta: Planeta) {
            val resId = when (planeta.nomImatge.lowercase()) {
                "mercurio" -> R.drawable.mercurio
                "venus" -> R.drawable.venus
                "tierra" -> R.drawable.tierra
                "marte" -> R.drawable.marte
                "jupiter" -> R.drawable.jupiter
                else -> R.drawable.sistemasolar
            }
            imatge.setImageResource(resId)
            nom.text = planeta.nom
            desc.text = planeta.descripcioBreu
            itemView.setOnClickListener { onPlanetaClick(planeta) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planeta, parent, false)
        return PlanetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetaViewHolder, position: Int) {
        holder.bind(planetes[position])
    }

    override fun getItemCount(): Int = planetes.size
}
