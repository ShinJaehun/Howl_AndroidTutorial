package com.shinjaehun.mvvminstagram.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.shinjaehun.mvvminstagram.R

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var auth = FirebaseAuth.getInstance()

    var id: MutableLiveData<String> = MutableLiveData("ShinJaehun")
    var password: MutableLiveData<String> = MutableLiveData("")

    var showInputNumberActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    var showFindIdActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    var showMainActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    val context = getApplication<Application>().applicationContext // parameter로 application을 받아야 getApplication() 사용 가능!
    var googleSignInClient: GoogleSignInClient

    init {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun loginWithSignupEmail(){
        println("Email")
        auth.createUserWithEmailAndPassword(id.value.toString(), password.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                showInputNumberActivity.value = true
            } else {
                // 아이디가 있을 경우
                loginEmail()
            }
        }
    }

    fun loginEmail(){
        auth.signInWithEmailAndPassword(id.value.toString(), password.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result.user?.isEmailVerified == true) {
                    showMainActivity.value = true
                } else {
                    // 이메일 인증이 안되었으면 전화번호 입력으로...
                    showInputNumberActivity.value = true
                }
            }
        }
    }
    fun loginGoogle(view: View){ // xml에서 실행되면
        var i = googleSignInClient.signInIntent
        (view.context as? LoginActivity)?.googleLoginResult?.launch(i) // view를 캐스팅해서 view에 있는 googleLoginResult 실행
    }
    fun firebaseAuthWithGoogle(idToken: String?) { // 근디 이건 또 LoginActivity.kt에서 실행되네???
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result.user?.isEmailVerified == true) {
                    showMainActivity.value = true
                } else {
                    // 이메일 인증이 안되었으면 전화번호 입력으로...
                    showInputNumberActivity.value = true
                }
            }
        }
    }
}