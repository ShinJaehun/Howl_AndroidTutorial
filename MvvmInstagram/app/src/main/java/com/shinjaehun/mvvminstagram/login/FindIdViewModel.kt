package com.shinjaehun.mvvminstagram.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

val TAG = "FindIDViewModel"
class FindIdViewModel: ViewModel() {
    var auth = FirebaseAuth.getInstance()
    var firestore = FirebaseFirestore.getInstance()
    var id = ""
    var phoneNumber = ""
    var toastMessage = MutableLiveData("")

    fun findMyId(){
        firestore.collection("findIds").whereEqualTo("phoneNumber", phoneNumber).get().addOnCompleteListener {
            Log.i(TAG, it.result.documents.toString())
            if (it.isSuccessful && it.result.documents.size > 0) {
                var findIdModel = it.result.documents.first().toObject(FindIdModel::class.java)
                // 아무리 요소가 하나밖에 없더라도 query 결과는 항상 array로 넘어오기 때문에
                toastMessage.value = "당신의 아이디는 " + findIdModel?.id
            } else {
                toastMessage.value = "정보가 정확하지 않습니다"
            }
        }
    }

    fun findMyPassword(){
        auth.sendPasswordResetEmail(id).addOnCompleteListener {
            if (it.isSuccessful) {
                toastMessage.value = "비밀번호를 초기화 했습니다"
            } else {
                toastMessage.value = "정보가 정확하지 않습니다"
            }
        }
    }
}