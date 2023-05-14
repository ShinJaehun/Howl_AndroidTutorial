package com.shinjaehun.whatismvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.shinjaehun.whatismvvm.databinding.ActivityMainBinding

// interface
class MainActivity : AppCompatActivity(), ViewInterface {

    lateinit var binding: ActivityMainBinding

    var presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
    }

    fun clickNumber(i: Int) {
        presenter.clickNumber(i) // presenter로 값을 넘김
    }

    override fun toastMessage(i: Int) { // presenter에서 값을 받아오면 화면에 표시
        Toast.makeText(this, "$i 번을 클릭함", Toast.LENGTH_SHORT).show()
    }

    override fun checkPasswordMessage() { // presenter에서 값을 받아오면 화면에 표시
        binding.messageSuccess.visibility = View.VISIBLE
    }
}