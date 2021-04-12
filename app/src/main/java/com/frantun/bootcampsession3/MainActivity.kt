package com.frantun.bootcampsession3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcampsession3.BillDetailActivity.Companion.TAG_BILL
import com.frantun.bootcampsession3.adapter.BillAdapter
import com.frantun.bootcampsession3.interfaces.IOnEventClick
import com.frantun.bootcampsession3.model.Bill
import com.frantun.bootcampsession3.model.BillType
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var billRecycler: RecyclerView
    private lateinit var adapter: BillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billRecycler = findViewById(R.id.bills_recycler_view)

        val billList: List<Bill> = buildData()

        adapter = BillAdapter(buildData(), object : IOnEventClick {
            override fun onClick(position: Int) {
                navigateToBillDetail(billList[position])
            }
        })

        billRecycler.layoutManager = LinearLayoutManager(this)
        billRecycler.adapter = adapter
    }

    private fun buildData(): List<Bill> {
        return listOf(
            Bill("Pago de colegio", 500.00, Date(), BillType.STUDIES),
            Bill("Pago de comida", 30.00, Date(), BillType.FOOD),
            Bill("Pago de trabajo", 40.00, Date(), BillType.WORK),
            Bill("Videojuegos", 5400.00, Date(), BillType.OTHER),
            Bill("Pago de colegio", 500.00, Date(), BillType.STUDIES),
            Bill("Pago de comida", 30.00, Date(), BillType.FOOD),
            Bill("Pago de trabajo", 40.00, Date(), BillType.WORK),
            Bill("Videojuegos", 5400.00, Date(), BillType.OTHER),
            Bill("Pago de colegio", 500.00, Date(), BillType.STUDIES),
            Bill("Pago de comida", 30.00, Date(), BillType.FOOD),
            Bill("Pago de trabajo", 40.00, Date(), BillType.WORK),
            Bill("Videojuegos", 5400.00, Date(), BillType.OTHER)
        )
    }

    private fun navigateToBillDetail(bill: Bill) {
        val intent = Intent(this, BillDetailActivity::class.java)
        intent.putExtra(TAG_BILL, bill)
        startActivity(intent)
    }
}