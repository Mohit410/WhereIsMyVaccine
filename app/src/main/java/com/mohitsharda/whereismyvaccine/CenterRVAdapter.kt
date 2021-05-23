package com.mohitsharda.whereismyvaccine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CenterRVAdapter(private val centerList: List<CenterRvModel>) :
    RecyclerView.Adapter<CenterRVAdapter.CenterRVViewHolder>() {
    class CenterRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val centerNameTV = itemView.findViewById<TextView>(R.id.tv_center_name)
        val centerAddressTV = itemView.findViewById<TextView>(R.id.tv_center_address)
        val centerTimingsTV = itemView.findViewById<TextView>(R.id.tv_center_timing)
        val vaccineNameTV = itemView.findViewById<TextView>(R.id.tv_vaccine_name)
        val ageLimitTV = itemView.findViewById<TextView>(R.id.tv_age_limit)
        val feeTypeTV = itemView.findViewById<TextView>(R.id.tv_fee_type)
        val availabilityTV = itemView.findViewById<TextView>(R.id.tv_availability)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterRVViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.center_rv_item, parent, false)
        return CenterRVViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CenterRVViewHolder, position: Int) {
        val currentItem = centerList[position]

        holder.centerNameTV.text = currentItem.centerName
        holder.centerAddressTV.text = currentItem.centerAddress
        holder.ageLimitTV.text = "Age Limit : ${currentItem.ageLimit}+"
        holder.availabilityTV.text = "Availability : ${currentItem.availableCapacity}"
        holder.centerTimingsTV.text =
            "From : ${currentItem.centerFromTiming} To : ${currentItem.centerToTiming}"
        holder.vaccineNameTV.text = currentItem.vaccineName
        holder.feeTypeTV.text = currentItem.feeType
    }

    override fun getItemCount(): Int {
        return centerList.size
    }

}