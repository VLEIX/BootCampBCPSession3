package com.frantun.bootcampsession3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcampsession3.R
import com.frantun.bootcampsession3.addCurrency
import com.frantun.bootcampsession3.interfaces.IOnEventClick
import com.frantun.bootcampsession3.model.Bill
import com.frantun.bootcampsession3.model.BillType
import java.text.SimpleDateFormat
import java.util.*

class BillAdapter(private val items: List<Bill>, private val onEventListener: IOnEventClick) :
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.bill_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val typeImageView = itemView.findViewById<ImageView>(R.id.type_image_view)
        private val descriptionTextView =
            itemView.findViewById<TextView>(R.id.description_text_view)
        private val amountTextView = itemView.findViewById<TextView>(R.id.amount_text_view)
        private val dateTextView = itemView.findViewById<TextView>(R.id.date_text_view)

        fun bind(item: Bill) {
            descriptionTextView.text = item.description
            amountTextView.text = item.amount.addCurrency()
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateTextView.text = simpleDateFormat.format(item.date)

            typeImageView.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                when(item.type) {
                    BillType.STUDIES -> R.drawable.ic_bill_1
                    BillType.FOOD -> R.drawable.ic_bill_2
                    BillType.WORK -> R.drawable.ic_bill_3
                    BillType.OTHER -> R.drawable.ic_bill_4
                }
            ))

            itemView.setOnClickListener {
                onEventListener.onClick(adapterPosition)
            }
        }
    }
}
