package com.ellycrab.eunbyul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/*
ViewModel은 앱의 ui관련 데이터를 저장하고 관리하는 클래스로 ui와 관련된 데이터를 처리하고 유지 관리하는 데 사용이되고,
 액티비티 또는 프래그먼트와 같은 ui컨트롤러와 분리되어 있다.
 */

class SignUpViewModel: ViewModel() {



    //MutableLiveData는 데이터를 변경할 수 있는 LiveData의 하위 클래스이며 이를 통해 데이터의 변경을 감지하고 UI에 알릴 수 있다.
    private val _name = MutableLiveData<String>()
    //이름(_name), 아이디(_id), 비밀번호(_password)에 대한 LiveData 속성을 선언하여 외부에서 데이터에 대한 읽기 전용 접근을 허용한다.
    val name:LiveData<String> get() = _name

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _email = MutableLiveData<String>()
    val email:LiveData<String> get() = _email


    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _passwordConfirm = MutableLiveData<String>()
    val passwordConfirm:LiveData<String> get() = _passwordConfirm

    /*
    그 후 setName(), setId(),setPassword() 함수를 통해 각각의 데이터를 설정하고 이 함수들은 내부적으로 MutableLiveData의 값을 설정한다.
     */
    fun setName(value:String){
        _name.value = value
    }
    fun setId(value:String){
        _id.value = value
    }

    fun setEmail(value:String){
        _email.value = value
    }

    fun setPassword(value:String){
        _password.value = value
    }

    fun setPasswordConfirm(value:String){
        _passwordConfirm.value = value
    }

    //로그인 화면으로 보내질 데이터
    fun updateDataTryToSend(name: String, id: String, password: String) {
        setName(name)
        setId(id)
        setPassword(password)
    }

    //회원가입화면 유효성검사 함수
    fun updateValidation(name:String,id:String,email:String,password:String,passwordConfirm:String){
        setName(name)
        setId(id)
        setEmail(email)
        setPassword(password)
        setPasswordConfirm(passwordConfirm)
    }



    //toast메시지는 뷰단에서 처리
    fun validateFields(
        name: String,
        id:String,
        pw: String,
        pwConfirm: String,
        emailConfirm: String,

    ): Boolean {

        if (name.isEmpty()) {

            return false
        }

        if(id.isEmpty()){
            return false
        }
        // 이메일 유효성 검사
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!emailConfirm.matches(emailPattern.toRegex())) {

            return false
        }

        // 비밀번호 강도 처리 (길이, 대문자, 특수문자)
        // 비밀번호 길이가 8자리 이하이거나 대문자 혹은 특수문자가 없다면 => 비밀번호를 다시 입력
        if (pw.length < 8 || !pw.contains(Regex("[A-Z]")) || !pw.contains(Regex("[^A-Za-z0-9]"))) {

            return false
        }

        // 비밀번호 확인
        if (pw != pwConfirm) { // 비밀번호 확인 필드와 일치하는지 확인

            return false
        }

        return true
    }



}
