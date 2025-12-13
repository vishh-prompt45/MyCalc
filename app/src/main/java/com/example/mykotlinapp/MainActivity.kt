package com.example.mykotlinapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTv1: TextView
    private lateinit var resultTv2: TextView

    private val expression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTv1 = findViewById(R.id.result_tv1)
        resultTv2 = findViewById(R.id.result_tv2)

        val buttonIds = intArrayOf(
            R.id.btn_zero, R.id.btn_one, R.id.btn_two, R.id.btn_three,
            R.id.btn_four, R.id.btn_five, R.id.btn_six,
            R.id.btn_seven, R.id.btn_eight, R.id.btn_nine,
            R.id.btn_zz, R.id.btn_comma,
            R.id.btn_add, R.id.btn_subtraction,
            R.id.btn_multiplication, R.id.btn_division, R.id.btn_mod,
            R.id.btn_equal, R.id.btn_bkdel, R.id.btn_wdel
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            // NUMBERS
            R.id.btn_zero -> append("0")
            R.id.btn_one -> append("1")
            R.id.btn_two -> append("2")
            R.id.btn_three -> append("3")
            R.id.btn_four -> append("4")
            R.id.btn_five -> append("5")
            R.id.btn_six -> append("6")
            R.id.btn_seven -> append("7")
            R.id.btn_eight -> append("8")
            R.id.btn_nine -> append("9")
            R.id.btn_zz -> append("00")
            R.id.btn_comma -> append(".")

            // OPERATORS
            R.id.btn_add -> append("+")
            R.id.btn_subtraction -> append("-")
            R.id.btn_multiplication -> append("*")
            R.id.btn_division -> append("/")
            R.id.btn_mod -> append("%")

            // BACKSPACE
            R.id.btn_bkdel -> {
                if (expression.isNotEmpty()) {
                    expression.deleteCharAt(expression.length - 1)
                    resultTv1.text = expression.toString()
                }
            }

            // CLEAR ALL
            R.id.btn_wdel -> {
                expression.clear()
                resultTv1.text = ""
                resultTv2.text = ""
            }

            // EQUAL
            R.id.btn_equal -> calculateResult()
        }
    }

    private fun append(value: String) {
        expression.append(value)
        resultTv1.text = expression.toString()
    }

    private fun calculateResult() {
        try {
            val exp = ExpressionBuilder(expression.toString()).build()
            val result = exp.evaluate()
            resultTv2.text = result.toString()
        } catch (e: Exception) {
            resultTv2.text = "Error"
        }
    }
}