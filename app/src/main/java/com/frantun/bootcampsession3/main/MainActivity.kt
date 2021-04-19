package com.frantun.bootcampsession3.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frantun.bootcampsession3.BillDetailActivity
import com.frantun.bootcampsession3.BillDetailActivity.Companion.TAG_BILL
import com.frantun.bootcampsession3.R
import com.frantun.bootcampsession3.adapter.BillAdapter
import com.frantun.bootcampsession3.add.AddBillActivity
import com.frantun.bootcampsession3.application.BootCampApplication
import com.frantun.bootcampsession3.interfaces.IOnEventClick
import com.frantun.bootcampsession3.model.Bill

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel

    private lateinit var billRecycler: RecyclerView
    private lateinit var addButton: Button
    private lateinit var adapter: BillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel
        viewModelFactory = MainViewModelFactory((application as BootCampApplication).billRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // UI
        setContentView(R.layout.activity_main)
        billRecycler = findViewById(R.id.bills_recycler_view)
        addButton = findViewById(R.id.add_bill_button)
        adapter = BillAdapter(onEventListener = object : IOnEventClick {
            override fun onClick(bill: Bill) {
                navigateToBillDetail(bill)
            }

            override fun onLongClick(bill: Bill) {
                viewModel.delete(bill)
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

        // Listeners
        addButton.setOnClickListener {
            navigateToAddBill()
        }
    }

    private fun navigateToBillDetail(bill: Bill) {
        val intent = Intent(this, BillDetailActivity::class.java)
        intent.putExtra(TAG_BILL, bill)
        startActivity(intent)
    }

    private fun navigateToAddBill() {
        val intent = Intent(this, AddBillActivity::class.java)
        startActivity(intent)
    }
}