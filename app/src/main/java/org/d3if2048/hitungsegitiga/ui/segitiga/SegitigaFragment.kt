package org.d3if2048.hitungsegitiga.ui.segitiga

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.FragmentLihatGambarBinding
import org.d3if2048.hitungsegitiga.model.KategoriLuas
import org.d3if2048.hitungsegitiga.util.Gambar

class SegitigaFragment : Fragment() {

    private lateinit var binding: FragmentLihatGambarBinding
    private val args: SegitigaFragmentArgs by navArgs()

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLihatGambarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI (kategori: KategoriLuas) {
        when (kategori) {
            KategoriLuas.KECIL -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.segitiga3))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_segitiga)
            }
            KategoriLuas.SEDANG -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.segitiga2))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_segitiga_sedang)
            }
            KategoriLuas.BESAR -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.segitiga1))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_segitiga_besar)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}