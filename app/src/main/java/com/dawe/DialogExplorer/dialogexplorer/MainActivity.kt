package com.dawe.DialogExplorer.dialogexplorer

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertDialogButton: Button = findViewById(R.id.alertDialogButton)
        val datePickerButton: Button = findViewById(R.id.datePickerButton)
        val timePickerButton: Button = findViewById(R.id.timePickerButton)
        resultTextView = findViewById(R.id.resultTextView)

        alertDialogButton.setOnClickListener {
            // Show AlertDialog
            showAlertDialog()
        }

        datePickerButton.setOnClickListener {
            // Show DatePickerDialog
            showDatePickerDialog()
        }

        timePickerButton.setOnClickListener {
            // Show TimePickerDialog
            showTimePickerDialog()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input Text Dialog")

        // Set up the input
        val input = EditText(this)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        input.layoutParams = lp
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { _, _ ->
            val userInput = input.text.toString()
            showToast("Entered text: $userInput")
            updateResult("User selected OK in AlertDialog. Entered text: $userInput")
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            showToast("User canceled AlertDialog")
            dialog.cancel()
        }

        builder.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = formatDate(selectedYear, selectedMonth, selectedDay)
                showToast("Selected date: $selectedDate")
                updateResult("User selected date: $selectedDate")
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val selectedTime = formatTime(selectedHour, selectedMinute)
                showToast("Selected time: $selectedTime")
                updateResult("User selected time: $selectedTime")
            },
            hour,
            minute,
            false
        )

        timePickerDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateResult(message: String) {
        resultTextView.text = message
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun formatTime(hour: Int, minute: Int): String {
        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
    }

    fun onClick(view: View) {}
}