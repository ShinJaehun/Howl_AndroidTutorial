package com.shinjaehun.whatismvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.shinjaehun.whatismvvm.databinding.ActivityMainBinding

// controller
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
    }

    fun clickNumber(i: Int) {
        Toast.makeText(this, "$i 번을 클릭함", Toast.LENGTH_SHORT).show()

        model.inputPassword(i)

        if (model.password.size == 4 && model.checkPassword()) {
            binding.messageSuccess.visibility = View.VISIBLE
        }
    }
}