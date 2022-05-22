package org.d3if2048.hitungsegitiga.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.FragmentLihatGambarBinding
import org.d3if2048.hitungsegitiga.model.KategoriLuas

class SegitigaFragment : Fragment() {

    private lateinit var binding: FragmentLihatGambarBinding
    private val args: SegitigaFragmentArgs by navArgs()

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLihatGambarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI (kategori: KategoriLuas) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriLuas.KECIL -> {
                binding.imageView.setImageResource(R.drawable.segitiga_kecil)
                binding.textView.text = getString(R.string.desc_segitiga)
            }
            KategoriLuas.SEDANG -> {
                binding.imageView.setImageResource(R.drawable.segitiga_sedang)
                binding.textView.text = getString(R.string.desc_segitiga_sedang)
            }
            KategoriLuas.BESAR -> {
                binding.imageView.setImageResource(R.drawable.segitiga_besar)
                binding.textView.text = getString(R.string.desc_segitiga_besar)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}