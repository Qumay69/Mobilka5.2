package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cost = intent.getIntExtra("COST", 0)  // Стоимость аренды
        val mode = intent.getStringExtra("MODE") ?: "Не выбран"  // Тип транспорта

        // Расчёт на основе выбранного режима аренды
        val result = calculateCost(cost, mode)

        // Отображаем результат
        binding.textViewResult.setText("Тип транспорта: $mode\nСтоимость аренды: $cost\n$" +
        "Рассчитанная стоимость: $result")

        binding.buttonBack.setOnClickListener {
            finish() // Закрываем CalcActivity и возвращаемся в InputActivity
        }
    }

    // Функция для расчёта стоимости
    private fun calculateCost(cost: Int, mode: String): Double {
        return when (mode) {
            "Такси" -> cost * 1.0   // Примерный коэффициент для такси
            "Микроавтобус" -> cost * 1.5   // Примерный коэффициент для микроавтобуса
            "Автобус" -> cost * 2.0  // Примерный коэффициент для автобуса
            else -> 0.0
        }
    }
}
