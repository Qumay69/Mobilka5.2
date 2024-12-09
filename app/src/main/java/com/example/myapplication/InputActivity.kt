package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextCost: EditText = findViewById(R.id.editTextCost)
        val radioGroupMode: RadioGroup = findViewById(R.id.radioGroupMode)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val costText = editTextCost.text.toString()
            val cost = costText.toIntOrNull()
            val selectedModeId = radioGroupMode.checkedRadioButtonId
            val selectedMode: String = when (selectedModeId) {
                R.id.radioTaxi -> "Такси"
                R.id.radioMinibus -> "Микроавтобус"
                R.id.radioBus -> "Автобус"
                else -> "Не выбран"
            }

            if (cost != null && selectedMode != "Не выбран") {
                // Передаем данные в CalcActivity
                val intent = Intent(this, CalcActivity::class.java).apply {
                    putExtra("COST", cost)
                    putExtra("MODE", selectedMode)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Пожалуйста, введите все данные", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
