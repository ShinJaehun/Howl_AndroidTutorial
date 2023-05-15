package com.shinjaehun.mvvminstagram.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var id: MutableLiveData<String> = MutableLiveData("ShinJaehun")
    var password: MutableLiveData<String> = MutableLiveData("")

    var showInputNumberActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    var showFindIdActivity: MutableLiveData<Boolean> = MutableLiveData(false)
}