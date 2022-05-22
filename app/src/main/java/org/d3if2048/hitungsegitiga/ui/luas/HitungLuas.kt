package org.d3if2048.hitungsegitiga.ui.luas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.FragmentLuasBinding
import org.d3if2048.hitungsegitiga.db.LuasDb
import org.d3if2048.hitungsegitiga.model.HasilLuas
import org.d3if2048.hitungsegitiga.model.KategoriLuas

class HitungLuas: Fragment() {

    private lateinit var binding: FragmentLuasBinding

    private val viewModel: HitungViewModel by lazy {
        val db = LuasDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLuasBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return  binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.layout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.layout_menu -> {
                findNavController().navigate(R.id.action_hitungLuas_to_aboutFragment)
                true
            }
            R.id.histori_menu -> {
                findNavController().navigate(R.id.action_HitungLuas_to_historiFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungLuas() }
        binding.resetButton.setOnClickListener { resetHitungan() }
        binding.linkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.celebrities.id/read/rumus-luas-segitiga-FM8P73"))
            requireContext().startActivities(arrayOf(intent))
        }
        binding.lihatGambar.setOnClickListener { viewModel.mulaiNavigasi() }

        setupObserveres()

        viewModel.getHasilLuas().observe(requireActivity(), { showResult(it) })

        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(HitungLuasDirections.actionHitungLuasToAboutFragment())
            viewModel.selesaiNavigasi()
        })
    }

    @SuppressLint("StringFormatMatches")
    private fun showResult(result: HasilLuas?) {
        if (result == null) return

        binding.luasTextView.text = getString(R.string.hasil_luas, result.luas)
        binding.kategoriTextView.text = getString(R.string.kategori_segitiga, getKategoriLabel(result.kategoriLuas))
        binding.lihatGambar.visibility = View.VISIBLE
        binding.linkButton.visibility = View.VISIBLE
    }

    private fun resetHitungan() {
        binding.alasSegitigaInp.text?.clear()
        binding.tinggiSegitigaInp.text?.clear()
        binding.luasTextView.text = ""
        binding.kategoriTextView.text = ""
        binding.lihatGambar.visibility = View.INVISIBLE
        binding.linkButton.visibility = View.INVISIBLE
    }

    private fun hitungLuas() {
        val alas = binding.alasSegitigaInp.text.toString()
        if (TextUtils.isEmpty(alas)) {
            Toast.makeText(context, R.string.alas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiSegitigaInp.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLuas(
            alas.toDouble(),
            tinggi.toDouble()
        )
    }

    private fun getKategoriLabel(kategori: KategoriLuas): String {
        val stringRes = when (kategori) {
            KategoriLuas.KECIL -> R.string.kecil
            KategoriLuas.SEDANG -> R.string.sedang
            KategoriLuas.BESAR -> R.string.besar
        }
        return getString(stringRes)
    }

    private fun setupObserveres() {
        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if(it == null) return@observe
            findNavController().navigate(HitungLuasDirections.actionHitungFragmentToSegitigaFragment(it))
            viewModel.selesaiNavigasi()
        })
        viewModel.getHasilLuas().observe(requireActivity(), { showResult(it) })
    }
}