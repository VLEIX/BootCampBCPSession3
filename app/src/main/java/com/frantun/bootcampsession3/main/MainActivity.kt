package com.frantun.bootcampsession3.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcampsession3.BillDetailActivity
import com.frantun.bootcampsession3.BillDetailActivity.Companion.TAG_BILL
import com.frantun.bootcampsession3.R
import com.frantun.bootcampsession3.adapter.BillAdapter
import com.frantun.bootcampsession3.application.BootCampApplication
import com.frantun.bootcampsession3.interfaces.IOnEventClick
import com.frantun.bootcampsession3.model.Bill

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel

    private lateinit var billRecycler: RecyclerView
    private lateinit var adapter: BillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel
        viewModelFactory = MainViewModelFactory((application as BootCampApplication).billRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // UI
        setContentView(R.layout.activity_main)
        billRecycler = findViewById(R.id.bills_recycler_view)
        adapter = BillAdapter(onEventListener = object : IOnEventClick {
            override fun onClick(bill: Bill) {
                navigateToBillDetail(bill)
            }
        })
        billRecycler.layoutManager = LinearLayoutManager(this)
        billRecycler.adapter = adapter

        // Observer
        viewModel.allBills.observe(this) { billList ->
            billList?.let {
                adapter.updateItems(billList)
            }
        }

        // TEMPORAL
        val bill1 = Bill("Pago de colegio", 500.00)
        val bill2 = Bill("Pago de comida", 30.00)
        viewModel.insert(bill1)
        viewModel.insert(bill2)
    }

//    private fun buildData(): List<Bill> {
//        return listOf(
//            Bill("Pago de colegio", 500.00),
//            Bill("Pago de comida", 30.00),
//            Bill("Pago de trabajo", 40.00)
//        )
//    }

    private fun navigateToBillDetail(bill: Bill) {
        val intent = Intent(this, BillDetailActivity::class.java)
        intent.putExtra(TAG_BILL, bill)
        startActivity(intent)
    }
}