package org.d3if2048.hitungsegitiga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if2048.hitungsegitiga.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

       binding = ActivityMainBinding.inflate(layoutInflater)
       val view = binding.root
       setContentView(view)

       binding.button.setOnClickListener { hitungLuas() }
       binding.resetButton.setOnClickListener { reset() }
     }

    private fun hitungLuas() {
        val alas = binding.alasSegitigaInp.text.toString()
            if (TextUtils.isEmpty(alas)) {
                Toast.makeText(this, "Alas Segitiga Harus Diisi!", Toast.LENGTH_LONG).show()
                return
            }

        val tinggi = binding.tinggiSegitigaInp.text.toString()
            if (TextUtils.isEmpty(tinggi)) {
                Toast.makeText(this, "Tinggi Segitiga Harus Diisi!", Toast.LENGTH_LONG).show()
                return
            }

        var isEmptyFields = false
            if (!isEmptyFields) {
                val hasil = (alas.toDouble() * tinggi.toDouble()) / 2
                binding.luasTextView.text = resources.getString(R.string.hasil_luas, hasil.toInt())
        }
    }

    private fun reset() {
        binding.alasSegitigaInp.text?.clear()
        binding.tinggiSegitigaInp.text?.clear()
        binding.luasTextView.text = ""
    }
}