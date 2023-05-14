package com.shinjaehun.whatismvvm

import android.view.View
import android.widget.Toast

class Presenter(var viewInterface: ViewInterface) {
    var model = Model()

    // class 정의의 Presenter(var viewInterface: ViewInterface)와 동일한 효과
//    var viewInterface: ViewInterface? = null
//    constructor(viewInterface: ViewInterface) {
//        this.viewInterface = viewInterface
//    }

    fun clickNumber(i: Int) {
        viewInterface.toastMessage(i)

        model.inputPassword(i)

        if (model.password.size == 4 && model.checkPassword()) {
            viewInterface.checkPasswordMessage()
        }
    }
}