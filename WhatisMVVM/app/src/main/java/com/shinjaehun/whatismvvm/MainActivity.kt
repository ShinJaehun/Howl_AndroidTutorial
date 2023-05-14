package com.shinjaehun.whatismvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.shinjaehun.whatismvvm.databinding.ActivityMainBinding

// interface
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

//    var presenter = Presenter(this)
    var viewModel = ViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        viewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, "$it 번을 클릭함", Toast.LENGTH_SHORT).show()
        })
        viewModel.checkPasswordMessage.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.checkPasswordMessage.get() == true) {
                    binding.messageSuccess.visibility = View.VISIBLE
                } else {
                    binding.messageSuccess.visibility = View.GONE
                }
            }
        })
    }
}