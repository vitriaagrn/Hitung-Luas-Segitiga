package org.d3if2048.hitungsegitiga.ui.histori

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.FragmentHistoriBinding
import org.d3if2048.hitungsegitiga.db.LuasDb


class HistoriFragment : Fragment(){

    private val viewModel: HistoriViewModel by lazy {
        val db = LuasDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHistoriBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            binding.dataInvalidTextview.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_layout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_histori) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.dialog_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.deleteData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}