package com.frantun.bootcampsession3.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.frantun.bootcampsession3.application.BootCampApplication
import com.frantun.bootcampsession3.databinding.ActivityAddBillBinding
import com.frantun.bootcampsession3.model.Bill
import com.frantun.bootcampsession3.model.BillType
import java.text.SimpleDateFormat
import java.util.*

class AddBillActivity : AppCompatActivity() {

    lateinit var viewModelFactory: AddBillViewModelFactory
    lateinit var viewModel: AddBillViewModel

    private lateinit var binding: ActivityAddBillBinding

    private val typeList = listOf("Studies", "Food", "Work", "Other")

    private val calendar = Calendar.getInstance()
    private var indexType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel
        viewModelFactory =
            AddBillViewModelFactory((application as BootCampApplication).billRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddBillViewModel::class.java)

        // UI
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()
        setListener()
    }

    private fun setUI() {
        val typeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            typeList
        )
        binding.typeAutoText.setAdapter(typeAdapter)
    }

    private fun setListener() {
        binding.dateEditText.setOnClickListener {
            showDatePicker()
        }

        binding.typeAutoText.setOnItemClickListener { _, _, position, _ ->
            indexType = position
        }

        binding.addBillButton.setOnClickListener {
            val description: String = binding.descriptionEditView.text.toString()
            val amount: Double = binding.amountEditView.text.toString().toDoubleOrNull() ?: 0.0
            val date: Date = calendar.time
            val type: BillType = BillType.values()[indexType]

            viewModel.insert(Bill(description, amount, date, type))

            finish()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this, { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.dateEditText.setText(simpleDateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}