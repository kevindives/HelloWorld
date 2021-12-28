package com.example.helloworld.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.helloworld.detalle.DetalleActivity
import com.example.helloworld.R
import com.google.android.material.textfield.TextInputEditText

class RegistroSuperheroeMainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_superheroe_main2)

        val registrartButton: Button = findViewById(R.id.registar_button)
        val nombreEditText: EditText = findViewById(R.id.nombre_edit_text)
        val infoTextView: TextView = findViewById(R.id.info_text_view)
        val estaturaEditText: TextInputEditText = findViewById(R.id.estatura_edit_text)
        val masculinoRadioButton: RadioButton = findViewById(R.id.masculino_radio_button)
        //val femeninoRadioButton: RadioButton = findViewById(R.id.femenino_radio_button)
        val fuerzaCheckBox: CheckBox = findViewById(R.id.super_fuerza_checkBox)
        val velocidadCheckBox: CheckBox = findViewById(R.id.velocidad_checkBox)
        val telequinesisCheckBox: CheckBox = findViewById(R.id.telequinsesis_checkBox)
        val ciudadNacimientoSpinner: Spinner = findViewById(R.id.ciudad_nacimiento_spinner)

        registrartButton.setOnClickListener {

            if (estaturaEditText.text.toString() == "")
                estaturaEditText.error = "Digite estatura"
            if (nombreEditText.text.isEmpty() || estaturaEditText.text.toString() == "")
                Toast.makeText(this,"Debe digitar el nombre o la estatura", Toast.LENGTH_SHORT).show()
            else {
                val nombre : String = nombreEditText.text.toString()
                val estatura: Float  = estaturaEditText.text.toString().toFloat()
                val genero: String
                var poderes = ""
                val ciudadNacimiento = ciudadNacimientoSpinner.selectedItem.toString()

                if (masculinoRadioButton.isChecked)
                    genero = getString(R.string.masculino)
                else
                    genero = getString(R.string.femenino)

                if (fuerzaCheckBox.isChecked) poderes = getString(R.string.super_fuerza)
                if (velocidadCheckBox.isChecked) poderes = poderes + " " + getString(R.string.velocidad)
                if (telequinesisCheckBox.isChecked) poderes = poderes + " " + getString(R.string.telequinsesis)

                infoTextView.text = getString(R.string.info, nombre, genero,estatura, poderes, ciudadNacimiento)


                val intent = Intent (this, DetalleActivity::class.java)
                intent.putExtra("nombre",nombre)
                startActivity(intent)

             }
        }
    }
}