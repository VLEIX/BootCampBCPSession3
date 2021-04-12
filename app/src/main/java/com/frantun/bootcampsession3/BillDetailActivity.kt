package com.frantun.bootcampsession3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.frantun.bootcampsession3.model.Bill

class BillDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_detail)

        val descriptionTextView: TextView = findViewById(R.id.description_text_view)
        val amountTextView: TextView = findViewById(R.id.amount_text_view)
        val addBillButton: Button = findViewById(R.id.add_bill_button)

        // option 1 - implementación pobre
//        val billReceived = intent?.extras?.getSerializable(TAG_BILL) as Bill
//
//        descriptionTextView.text = billReceived.description
//        amountTextView.text = billReceived.amount.addCurrency()
        //

        // option 2 - FULL
        intent?.extras?.let {
            if (it.containsKey(TAG_BILL)) {
                val billReceived = it.getSerializable(TAG_BILL) as? Bill

                // CALL API
                // SAVE DBLocal

                billReceived?.let { bill ->
                    descriptionTextView.text = bill.description
                    amountTextView.text = bill.amount.addCurrency()
                }
            }
        }
        //

        addBillButton.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val TAG_BILL = "TAG_BILL"
    }
}