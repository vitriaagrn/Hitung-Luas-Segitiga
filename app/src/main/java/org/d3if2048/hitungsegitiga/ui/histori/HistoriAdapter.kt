package org.d3if2048.hitungsegitiga.ui.histori

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2048.hitungsegitiga.R
import org.d3if2048.hitungsegitiga.databinding.LayoutItemBinding
import org.d3if2048.hitungsegitiga.db.LuasEntity
import org.d3if2048.hitungsegitiga.model.hitungSegitiga
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<LuasEntity,HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LuasEntity>() {
                override fun areItemsTheSame(
                    oldData: LuasEntity, newData: LuasEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: LuasEntity, newData: LuasEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

//    fun updateData (data: List<LuasEntity>) {
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: LayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: LuasEntity) = with(binding) {
            val hasilSegitiga = item.hitungSegitiga()

            tanggal.text = dateFormatter.format(Date(item.tanggal))
            tinggi.text = root.context.getString(
                R.string.tulisan_tinggi,
                item.tinggi
            )
            alas.text = root.context.getString(
                R.string.tulisan_alas,
                item.alas
            )
            hasilTextView.text = root.context.getString(
                R.string.tulisan_luas,
                hasilSegitiga.luas.toString()
            )
        }
    }
}