package org.d3if2048.hitungsegitiga.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.FragmentAboutBinding
import org.d3if2048.hitungsegitiga.network.ApiStatus
import org.d3if2048.hitungsegitiga.network.SegitigaApi

class AboutFragment : Fragment(R.layout.fragment_about){
    private lateinit var binding: FragmentAboutBinding

    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = SegitigaApi
        val factory = AboutViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory)[AboutViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        observeStatus()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeData() {
        viewModel.getData.observe(this@AboutFragment){
            binding.about.text = it.toString()
        }
    }

    private fun observeStatus() {
        viewModel.getStatus().observe(viewLifecycleOwner) {
            when (it) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ApiStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.about.visibility = View.VISIBLE
                }
                ApiStatus.FAILED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.networkError.visibility = View.VISIBLE
                }
            }
        }
    }
}