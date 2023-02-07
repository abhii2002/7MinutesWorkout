package com.blissvine.a7minutesworkout

import android.media.MediaDrm.MetricsConstants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.blissvine.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
         private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW" //Metric Unit View
         private const val US_UNIT_VIEW = "US_UNIT_VIEW"   // US Unit View

    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW //A variable to hold a value to make a selected
    // view visible

    private var binding: ActivityBmiBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar != null){
             supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"

        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener{
              onBackPressed()
        }

        // Default view
        makeVisibleMetricUnitView()

        binding?.rgUnits?.setOnCheckedChangeListener{ _, checkedId: Int ->
            if(checkedId == R.id.rbMetricUnits){
                 makeVisibleMetricUnitView()
            }else{
                 makeVisibleUsUnitsView()
            }

        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateBMI()
        }

    }

    private fun validateMetricUnits(): Boolean{
         var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
             isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
             isValid = false
        }
        return isValid
    }

    private fun validateUsUnits(): Boolean{
         var isValid = true

        when{
             binding?.etUsMetricUnitWeight?.text.toString().isEmpty() ->{
                  isValid = false
             }

            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() ->{
                isValid  = false

            }
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() ->{
                 isValid = false
            }

        }

        return isValid
    }

  private fun calculateBMI(){
      if (currentVisibleView == METRIC_UNITS_VIEW){

          if(validateMetricUnits()){
              val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

              val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

              val bmi  = weightValue /  (heightValue*heightValue)
              displayBMIResult(bmi)
          }else{
              Toast.makeText(this, "Please enter valid values.", Toast.LENGTH_SHORT).show()
          }
      }else{
          if(validateUsUnits()){
               val usUnitHeightValueFeet: String = binding?.etUsMetricUnitHeightFeet?.text.toString()
              val usUnitHeightValueInch: String =
                  binding?.etUsMetricUnitHeightInch?.text.toString()
              val usUnitWeightValue: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()

              val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() *  12

              val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
               displayBMIResult(bmi)
          }else{
              Toast.makeText(this, "Please enter valid values.", Toast.LENGTH_SHORT).show()
          }

      }

    }



 private fun makeVisibleMetricUnitView(){
       currentVisibleView = METRIC_UNITS_VIEW
       binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
       binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
       binding?.tilUsMetricUnitWeight?.visibility = View.GONE
     binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE
     binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE

     /*
     When we are changing to the US metric system view we are making sure that there is no entry left
      */

     binding?.etMetricUnitHeight?.text!!.clear() // height value is cleared if it was added.
     binding?.etMetricUnitWeight?.text!!.clear() // weight value is cleared if it was added.

     binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

 }

    private fun makeVisibleUsUnitsView(){

        currentVisibleView = US_UNIT_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        /*
        When we are changing to the  metric system view we are making sure that there is no entry left
         */

        binding?.etUsMetricUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()


        binding?.etMetricUnitHeight?.text!!.clear() // height value is cleared if it was added.
        binding?.etMetricUnitWeight?.text!!.clear()



        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE


    }

private fun displayBMIResult(bmi: Float){

    val bmiLabel: String
    val bmiDescription : String

    if(bmi.compareTo(15f) <= 0){
         bmiLabel = "Severely underweight"
         bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
    }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
        bmiLabel = "Sharply underweight"
        bmiDescription = "Oops! You need to start a balanced diet! Eat more!"
    }else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
        bmiLabel = "Underweight"
        bmiDescription = "Oops! A little attention towards a balanced diet is required! Eat more!"
    }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <=0 ) {
        bmiLabel = "Normal"
        bmiDescription = "Congratulations! You are in a good shape!"
      }else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
          bmiLabel = "Overweight"
         bmiDescription = "Oops! You need attention to reduce weight! Workout!"
    }else if(bmi.compareTo(30f) > 0  && bmi.compareTo(35f) <=0 ){
         bmiLabel = "Obese Class | (Moderately obese)"
        bmiDescription = "Oops! You really need to take care of yourself! Workout"
    }else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <=0){
        bmiLabel = "Obese Class || (Severely obese)"
        bmiDescription = "Oops! You really need to take care of yourself! Workout"
    }else {
         bmiLabel = "Obese Class ||| (Very Severely obese"
        bmiDescription = "Dangerous! Could be sign of critical health issue! Needs medical attention!"
    }

    val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

    binding?.llDisplayBMIResult?.visibility = View.VISIBLE
    binding?.tvBMIValue?.text = bmiValue
    binding?.tvBMIType?.text = bmiLabel
    binding?.tvBMIDescription?.text = bmiDescription


}





}