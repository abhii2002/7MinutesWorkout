package com.blissvine.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blissvine.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items: ArrayList<String>,
                     private val deleteListener:(id:String)->Unit):
                                RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        //Accessing all the particular elements in the bound layout using binding object
         val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
        val deleteItem = binding.deleteItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date: String = items.get(position)

       val item = HistoryEntity(items[position])

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if(position % 2 == 0){
            holder.llHistoryItemMain.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }else{
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }

        holder.deleteItem.setOnClickListener{
            deleteListener.invoke(item.date)
        }

        holder.adapterPosition

    }

    override fun getItemCount(): Int {
       return items.size
    }
}