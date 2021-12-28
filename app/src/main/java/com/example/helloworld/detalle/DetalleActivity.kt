package com.example.helloworld.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.databinding.ActivityDatalleBinding
import com.example.helloworld.model.SuperheroeItem
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {

    private lateinit var  detalleBinding: ActivityDatalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detalleBinding = ActivityDatalleBinding.inflate(layoutInflater)
        setContentView(detalleBinding.root)


        val superheroe:SuperheroeItem = intent.extras?.getSerializable("superheroe") as SuperheroeItem
        with(detalleBinding){
            nameTextView.text = superheroe.name
            aliasTextView.text = superheroe.alias
            alturaTextView.text = superheroe.altura.toString()
            ciudadTextView.text = superheroe.city
            facebookTextView.text = superheroe.facebook
            ocupacionTextView.text = superheroe.occupation
            powersTextView.text = superheroe.powers
            Picasso.get().load(superheroe.urlPicture).into(pictureImageView)
        }
    }
}