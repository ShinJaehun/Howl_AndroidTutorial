package com.shinjaehun.mvvminstagram.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.shinjaehun.mvvminstagram.R
import com.shinjaehun.mvvminstagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
//    lateinit var loginViewModel: LoginViewModel
    val loginViewModel: LoginViewModel by viewModels() // 이거 implementation 하나 추가하고 이렇게 바꿨음

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        // by viewModels() 이후 삭제
//        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java] // 이건 뭔 형식이다냐...
//        // LoginActivity가 사라지면 얘네도 함께 제거하기 위해서 이렇게 선언한다고 함.

        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        setObserve()
    }

    fun setObserve(){
        loginViewModel.showInputNumberActivity.observe(this){
            if(it){
                finish()
                startActivity(Intent(this, InputNumberActivity::class.java))
            }
        }
        loginViewModel.showFindIdActivity.observe(this){
            // 근데 얘는 왜 finish()가 필요 없는 거야? // ID 찾고 다시 login해야 하기 때문!
            if(it){
                startActivity(Intent(this, FindIdActivity::class.java))
            }
        }
    }

    fun loginEmail(){
        println("Email")
        loginViewModel.showInputNumberActivity.value = true
    }
    fun findId(){
        println("findId")
        loginViewModel.showFindIdActivity.value = true
    }
}