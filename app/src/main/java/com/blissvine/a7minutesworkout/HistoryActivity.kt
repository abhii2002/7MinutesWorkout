package com.blissvine.a7minutesworkout

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blissvine.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"

        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        val dao = (application as WorkoutApp).db.historyDao()
        getAllCompletedDates(dao)



    }

    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch{
             historyDao.fetchAllDates().collect(){ allCompletedDatesList ->
                 if(allCompletedDatesList.isNotEmpty()){
                      binding?.tvHistory?.visibility = View.VISIBLE
                      binding?.rvHistory?.visibility = View.VISIBLE
                      binding?.tvNoDataAvailable?.visibility = View.INVISIBLE

                     binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                     val dates = ArrayList<String>()
                     for(date in allCompletedDatesList){
                         dates.add(date.date)
                     }

                     val historyAdapter = HistoryAdapter(dates
                     ) { deleteDates ->
                         lifecycleScope.launch {
                             historyDao.fetchAllDates().collect {
                                 if (it != null) {
                                   deleteRecord(deleteDates, historyDao)
                                 }
                             }
                         }
                     }

                     binding?.rvHistory?.adapter = historyAdapter

                 }else{
                     binding?.tvHistory?.visibility = View.GONE
                     binding?.rvHistory?.visibility = View.GONE
                     binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                 }
             }
        }

    }

    private fun deleteRecord(date: String, historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.delete(HistoryEntity(date))
            Toast.makeText(
                applicationContext,
                "Record deleted successfully.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /* private fun deleteRecordAlertDialog(date:String, historyDao: HistoryDao){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Record")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete")

        builder.setPositiveButton("Yes"){ dialogInterface, _ ->
            lifecycleScope.launch{
                historyDao.delete(HistoryEntity(date))
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()

            }
            dialogInterface.dismiss()
        }

        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }




    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    */


}