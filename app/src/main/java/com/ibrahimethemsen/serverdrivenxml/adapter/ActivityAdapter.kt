package com.ibrahimethemsen.serverdrivenxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ibrahimethemsen.serverdrivenxml.DEF_STRING
import com.ibrahimethemsen.serverdrivenxml.R
import com.ibrahimethemsen.serverdrivenxml.databinding.AdapterItemActivtyBinding
import com.ibrahimethemsen.serverdrivenxml.model.AdapterActivity
import com.ibrahimethemsen.serverdrivenxml.model.ItemActivity

class ActivityAdapter : RecyclerView.Adapter<ActivityAdapter.AdapterActivityViewHolder>() {
    private val activityList = mutableListOf<ItemActivity>()
    private var activityUi: AdapterActivity = AdapterActivity()

    fun setActivityList(newActivityList: List<ItemActivity>) {
        activityList.apply {
            clear()
            addAll(newActivityList)
        }
        notifyDataSetChanged()
    }

    fun setActivityUi(activityUi: AdapterActivity) {
        this.activityUi = activityUi
    }

    class AdapterActivityViewHolder(private val binding: AdapterItemActivtyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindUi(activityUi: AdapterActivity) {
            binding.apply {
                aiActivityProductTitleTv.textSize = activityUi.productTitleSize
                aiActivityProductTitleTv.setUiTextColor(activityUi.productTitleColor)
                aiActivityProductPriceTv.textSize=activityUi.productPriceSize
                aiActivityProductPriceTv.setUiTextColor(activityUi.productPriceColor)
                aiActivityProductReduction.textSize = activityUi.productReductionTitleSize
            }
        }
        fun bindUser(activityItem: ItemActivity) {
            binding.apply {
                aiActivityProductTitleTv.text = activityItem.productName
                aiActivityProductPriceTv.text = activityItem.productPrice.toString()
                aiActivityProductReduction.text = activityItem.productReduction
                aiActivityProductReduction.setUiTextColor(activityItem.productReductionColor,activityItem.productReductionBg)
                aiActivityProductFavoriteIv.visibility = if (activityItem.productFavorite) View.VISIBLE else View.GONE
                aiActivityProductIv.networkLoadImage(activityItem.productImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterActivityViewHolder {
        val binding =
            AdapterItemActivtyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterActivityViewHolder(binding)
    }

    override fun getItemCount(): Int = activityList.size

    override fun onBindViewHolder(holder: AdapterActivityViewHolder, position: Int) {
        holder.bindUi(activityUi)
        holder.bindUser(activityList[position])
    }
}
fun TextView.setUiTextColor(color : String,backgroundColor : String = String.DEF_STRING){
    when(color){
        "blue" -> {
            setTextColor(resources.getColor(R.color.blue))
        }
        "red" -> {
            setTextColor(resources.getColor(R.color.red))
        }
        "purple" -> {
            setTextColor(resources.getColor(R.color.purple))
        }
        "black" -> {
            setTextColor(resources.getColor(R.color.black))
        }
    }
    if (backgroundColor != String.DEF_STRING){
        when(backgroundColor){
            "blue" -> {
                setBackgroundColor(resources.getColor(R.color.blue))
            }
            "red" -> {
                setBackgroundColor(resources.getColor(R.color.red))
            }
            "purple" -> {
                setBackgroundColor(resources.getColor(R.color.purple))
            }
        }
    }
}

fun ImageView.networkLoadImage(url : String){
    this.load(url)
}