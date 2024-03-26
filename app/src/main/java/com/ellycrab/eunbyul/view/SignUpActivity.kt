package com.ellycrab.eunbyul.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.ellycrab.eunbyul.R
import com.ellycrab.eunbyul.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var editName:EditText
    private lateinit var editId:EditText
    private lateinit var editPw:EditText
    private lateinit var editPwConfirm:EditText
    private lateinit var editEmail:EditText


    private val viewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        editName = findViewById(R.id.editName)
        editId = findViewById(R.id.editId)
        editPw = findViewById(R.id.editPw)
        editPwConfirm = findViewById(R.id.editPwConfirm)
        editEmail = findViewById(R.id.editEmail)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            if(validateFields()){//기존에  allFieldsEntered()
                updateViewModel()
                navigateToSignUp()

            }else{
                Toast.makeText(this,"입력되지 않은 정보가 있습니다",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun validateFields(): Boolean { // 변경된 메서드 정의
        val name = editName.text.toString()
        //val id = editId.text.toString()
        val pw = editPw.text.toString()
        val pwConfirm  = editPwConfirm.text.toString()
        val emailConfirm = editEmail.text.toString()

        // 이름 필수 입력 확인
        if (name.isEmpty()) {
            Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
            return false
        }
        //이메일 유효성검사
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if(!emailConfirm.matches(emailPattern.toRegex())) {
            Toast.makeText(this, "유효한 이메일을 입력하세요", Toast.LENGTH_SHORT).show()
            return false
        }

        // 비밀번호 강도 처리 (길이, 대문자, 특수문자)
        //비밀번호길이가 8자리 이하이거나 대문자혹은 특수문자가 없다면 =>비밀번호를 다시 입력
        if (pw.length < 8 || !pw.contains(Regex("[A-Z]")) || !pw.contains(Regex("[^A-Za-z0-9]"))) {
            Toast.makeText(this, "비밀번호가 강도가 낮습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }

        // 비밀번호 확인
        if (pw != pwConfirm) { // 비밀번호 확인 필드와 일치하는지 확인
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            return false
        }



        return true
    }




    private fun updateViewModel() {
        viewModel.setName(editName.text.toString())
        viewModel.setId(editId.text.toString())
        viewModel.setPassword(editPw.text.toString())
    }


    //모두 입력받아야 SignInActivity 로 이동함
//    private fun allFieldsEntered(): Boolean {
//        val name = editName.text.toString()
//        val id = editId.text.toString()
//        val pw = editPw.text.toString()
//        return name.isNotEmpty() && id.isNotEmpty() && pw.isNotEmpty()
//    }
    private fun navigateToSignUp() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra("name",editName.text.toString())
        intent.putExtra("id",editId.text.toString())
        intent.putExtra("pw",editPw.text.toString())
        setResult(RESULT_OK,intent)
        finish()

    }
}