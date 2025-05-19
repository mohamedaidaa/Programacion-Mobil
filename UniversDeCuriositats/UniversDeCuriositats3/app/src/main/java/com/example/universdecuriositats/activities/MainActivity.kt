package com.example.universdecuriositats.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.universdecuriositats.R
import com.example.universdecuriositats.adapters.PlanetaAdapter
import com.example.universdecuriositats.data.Planeta

class MainActivity : AppCompatActivity() {
    private val planetes = listOf(
        Planeta(1, "Mercurio", "Es un enano rojo", "Es el más cercano al sol", "mercurio"),
        Planeta(2, "Venus", "Es el planeta más caliente", "Su atmósfera es tóxica...", "venus"),
        Planeta(3, "Tierra", "Es el planeta donde vivimos", "Único planeta con vida que conozcamos", "tierra"),
        Planeta(4, "Marte", "Es el planeta rojo", "Su superficie está llena de óxido", "marte"),
        Planeta(5, "Jupiter", "Es un gigante gaseoso", "Planeta más grande del sistema solar", "jupiter")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPlanetes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlanetaAdapter(planetes) { planeta ->
            val intent = Intent(this, DetallsPlanetaActivity::class.java).apply {
                putExtra("EXTRA_NOM", planeta.nom)
                putExtra("EXTRA_DESC_BREU", planeta.descripcioBreu)
                putExtra("EXTRA_DESC_LLARGA", planeta.descripcioLlarga)
                putExtra("EXTRA_IMATGE", planeta.nomImatge)
            }
            startActivity(intent)
        }
    }
}
