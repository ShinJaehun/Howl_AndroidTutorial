package com.shinjaehun.whatismvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

class ViewModel {
    var toastMessage = MutableLiveData<Int>()
    var checkPasswordMessage = ObservableField<Boolean>(false)

    var model = Model()

    // class 정의의 Presenter(var viewInterface: ViewInterface)와 동일한 효과
//    var viewInterface: ViewInterface? = null
//    constructor(viewInterface: ViewInterface) {
//        this.viewInterface = viewInterface
//    }

    fun clickNumber(i: Int) {
        toastMessage.value = i // MutableLieveData이기 때문에 value()
        model.inputPassword(i)

        if (model.password.size == 4 && model.checkPassword()) {
            checkPasswordMessage.set(true) // ObservableField이기 때문에 set()
        }
    }

}