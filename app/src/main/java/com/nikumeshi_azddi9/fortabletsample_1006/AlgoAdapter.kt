package com.nikumeshi_azddi9.fortabletsample_1006

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlgoAdapter(private val algoList: List<AlData>,
                  private val onItemClicked: (AlData) -> Unit) : RecyclerView.Adapter<AlgoAdapter.AlgoViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_row, parent, false)
        val vh = AlgoViewHolder(view)
        view.setOnClickListener {
            onItemClicked(algoList[vh.adapterPosition])
        }
        return vh
    }

    override fun getItemCount() = algoList.size

    override fun onBindViewHolder(holder: AlgoViewHolder, position: Int) {
        algoList[position].also{ item ->
            holder.apply {
                title.text = item.name
            }

        }
    }

    inner class AlgoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.AlgoName)
    }

}