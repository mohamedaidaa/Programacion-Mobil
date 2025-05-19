package com.example.universdecuriositats.activities

import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.universdecuriositats.R

class DetallsPlanetaActivity : AppCompatActivity() {
    private lateinit var btnFavorit: ImageButton
    private lateinit var clauFavorit: String
    private lateinit var sharedPrefs: SharedPreferences
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalls_planeta)

        val nom = intent.getStringExtra("EXTRA_NOM") ?: "Sistema Solar"
        val descripcio = intent.getStringExtra("EXTRA_DESC_LLARGA") ?: "Único sistema conocido con vida"
        val nomImatge = intent.getStringExtra("EXTRA_IMATGE") ?: ""
        clauFavorit = "FAVORIT_$nom"

        findViewById<TextView>(R.id.tvNomDetall).text = nom
        findViewById<TextView>(R.id.tvDescripcioDetall).text = descripcio
        val ivImatge = findViewById<ImageView>(R.id.ivImatgeDetall)
        btnFavorit = findViewById(R.id.btnFavorit)
        val btnReproAudio = findViewById<Button>(R.id.btnReproAudio)

        val resId = when (nomImatge.lowercase()) {
            "mercurio" -> R.drawable.mercurio
            "venus" -> R.drawable.venus
            "tierra" -> R.drawable.tierra
            "marte" -> R.drawable.marte
            "jupiter" -> R.drawable.jupiter
            else -> R.drawable.sistemasolar
        }
        ivImatge.setImageResource(resId)

        sharedPrefs = getSharedPreferences("PREFS_PLANETES", MODE_PRIVATE)
        actualitzarIconaFavorit()

        btnFavorit.setOnClickListener {
            val esFavorit = sharedPrefs.getBoolean(clauFavorit, false)
            sharedPrefs.edit().putBoolean(clauFavorit, !esFavorit).apply()
            actualitzarIconaFavorit()
        }

        btnReproAudio.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio_planeta_)
            mediaPlayer?.start()
        }
    }

    private fun actualitzarIconaFavorit() {
        val esFavorit = sharedPrefs.getBoolean(clauFavorit, false)
        btnFavorit.setImageResource(if (esFavorit) R.drawable.ic_star_filled else R.drawable.ic_star_border)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
