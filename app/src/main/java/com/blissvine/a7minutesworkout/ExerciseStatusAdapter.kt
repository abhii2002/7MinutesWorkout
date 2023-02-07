package com.blissvine.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blissvine.a7minutesworkout.databinding.ItemExerciseStatusBinding

/****
  For our recyclerView to Work,  we need an adapter which acts as a data source
 ****/


/*
This will take care of the status, which basically means the number of exercises we are currently at
 */

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
             val tvItem = binding.tvItem

    }

    /****
     Whenever we create an adapter we need to implement couple of members such as following...
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /*
        RecyclerView doesn't have its own context, it needs to use the context from its parent which
        will then use the context from the activity that it is at. that's why we passed parent.context
         */
        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    /*
    What should happen for every single item when the view is bound (definition of onBindViewHolder)
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // getting model of type ExerciseModel based on the items at the position that we are
        //currently at
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{
             model.getIsSelected() ->{
                  holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                      R.drawable.item_circular_thin_color_accent_border)
                 holder.tvItem.setTextColor(Color.parseColor("#212121"))
             }
            model.getIsCompleted() ->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_gray_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

        }


    }

    /*
    Here we just need to return how many items we will have in total of this recycler view
     */
    override fun getItemCount(): Int {
        return items.size
    }


}