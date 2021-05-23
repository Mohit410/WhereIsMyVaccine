package com.mohitsharda.whereismyvaccine

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mohitsharda.whereismyvaccine.databinding.ActivityMainBinding
import org.json.JSONException
import java.util.*

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var centerList: MutableList<CenterRvModel>
    private lateinit var centerRVAdapter: CenterRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        centerList = mutableListOf()

        binding.centerListRecyclerView.layoutManager = LinearLayoutManager(this)


        binding.btnSearch.setOnClickListener {
            closeKeyboard()
            val pinCode = binding.etPinCode.text.toString()
            if (pinCode.length != 6) {
                binding.etPinCode.error = "Please enter a valid pin-code"
            } else {
                centerList.clear()

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    this, { _, year, monthOfYear, dayOfMonth ->
                        val dateStr = """$dayOfMonth-${monthOfYear + 1}-${year}"""

                        getAppointment(pinCode, dateStr)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }
    }

    private fun getAppointment(pinCode: String, date: String) {
        val url =
            "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pinCode&date=$date"

        val queue = Volley.newRequestQueue(this@MainActivity)

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            Log.e("request", "Success response is $response")
            try {
                val centerArray = response.getJSONArray("centers")
                if (centerArray.length() == 0) {
                    Toast.makeText(this, "No centers available", Toast.LENGTH_SHORT).show()
                }
                for (i in 0 until centerArray.length()) {
                    val centerObj = centerArray.getJSONObject(i)
                    val centerName = centerObj.getString("name")
                    val centerAddress = centerObj.getString("address")
                    val centerFromTime = centerObj.getString("from")
                    val centerToTime = centerObj.getString("to")
                    val feeType = centerObj.getString("fee_type")

                    val sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0)
                    val ageLimit = sessionObj.getInt("min_age_limit")
                    val vaccineName = sessionObj.getString("vaccine")
                    val availableCapacity = sessionObj.getInt("available_capacity")

                    val center = CenterRvModel(
                        centerName,
                        centerAddress,
                        centerFromTime,
                        centerToTime,
                        feeType,
                        ageLimit,
                        vaccineName,
                        availableCapacity
                    )
                    centerList.add(center)
                }
                centerRVAdapter = CenterRVAdapter(centerList)
                binding.centerListRecyclerView.adapter = centerRVAdapter
                binding.centerListRecyclerView.setHasFixedSize(true)
                centerRVAdapter.notifyDataSetChanged()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            Log.e("Error", "Response is $error")
            Toast.makeText(this@MainActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
        })
        queue.add(request)
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}