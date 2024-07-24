package com.example.simplebmi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val imageBoy = findViewById<ImageView>(R.id.image_boy)
        val imageGirl = findViewById<ImageView>(R.id.image_girl_blur)
        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.height_value)
        val age = findViewById<EditText>(R.id.age_value)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val bmi = findViewById<TextView>(R.id.bmi)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val bmiView = findViewById<LinearLayout>(R.id.bmi_view)
        //val bmiViewELastic = findViewById<io.armcha.elasticview.ElasticView>(R.id.bmi_view_elastic)
        val calculateAgainButton = findViewById<TextView>(R.id.calculate_again)

        imageBoy.setOnClickListener {
            imageBoy.setImageResource(R.drawable.boy)
            imageGirl.setImageResource(R.drawable.girl_blur)
        }
        imageGirl.setOnClickListener {
            imageBoy.setImageResource(R.drawable.boy_blur)
            imageGirl.setImageResource(R.drawable.girl)
        }
        calculate_button.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0
            var ageValue = 0.0
            closeKeyboard(bmi)

            if(weight.text.toString().isNotEmpty()){
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()){
                heightValue = (height.text.toString().toDouble()/100)
            }
            if (age.text.toString().isNotEmpty()){
                ageValue = (age.text.toString().toDouble())
            }
            if (weightValue > 0.0 && heightValue > 0.0 && ageValue > 0.0){
                val bmiValue = String.format("%.2f", weightValue/heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue/heightValue.pow(2))
                bmiView.visibility = VISIBLE
                //bmiViewELastic.visibility = VISIBLE
                calculate_button.visibility = VISIBLE
            }
            else
                Toast.makeText(this , "Input Weight And Height And Age", Toast.LENGTH_SHORT).show()
        }
        calculateAgainButton.setOnClickListener {
            bmiView.visibility = VISIBLE
            //bmiViewELastic.visibility = GONE
            calculateButton.visibility = VISIBLE
            //weight.text.clear()
            //height.text.clear()
            //age.text.clear()
            weight.requestFocus()
        }
    }
    private fun bmiStatusValue(bmi: Double): String{
        lateinit var bmiStatus: String
        if (bmi<18.5)
            bmiStatus = "UnderWeight"
        else if(bmi>= 18.5 && bmi < 25)
            bmiStatus ="Normal"
        else if (bmi>= 25 && bmi < 30 )
            bmiStatus ="OverWeight"
        else if (bmi > 30)
            bmiStatus ="Obese"
        return bmiStatus
    }
    private fun closeKeyboard (view:View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}