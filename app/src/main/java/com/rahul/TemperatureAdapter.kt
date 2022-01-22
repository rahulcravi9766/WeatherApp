package com.rahul

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.response.currentTemperature.CurrentTempResponse
import com.rahul.weatherapp.R

class TemperatureAdapter(private val temp: CurrentTempResponse?) : RecyclerView.Adapter<TemperatureAdapter.TempViewHolder>() {
  inner  class TempViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){


      val celsius: TextView = itemView.findViewById(R.id.text_next_celsius)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return TempViewHolder(view)
    }

    override fun onBindViewHolder(holder: TempViewHolder, position: Int) {
        holder.apply {

                celsius.text = temp?.temperature?.map {
                    it.main.temp.toInt().toString().plus(" C")
                }?.get(position).toString()
        }
    }

    override fun getItemCount(): Int {
        return temp?.temperature!!.size
    }
}