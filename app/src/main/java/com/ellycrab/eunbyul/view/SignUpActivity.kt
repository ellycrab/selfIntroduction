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

    private lateinit var editName: EditText
    private lateinit var editId: EditText
    private lateinit var editPw: EditText
    private lateinit var editPwConfirm: EditText
    private lateinit var editEmail: EditText


    private val viewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //view 초기화
        initView()


        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            val name = editName.text.toString()
            val id = editId.text.toString()
            val pw = editPw.text.toString()
            val pwConfirm = editPwConfirm.text.toString()
            val email = editEmail.text.toString()

            if (viewModel.validateFields(
                    name,
                    id,
                    pw,
                    pwConfirm,
                    email
                )
            ) {
                viewModel.updateValidation(
                    name,
                    id,
                    pw,
                    pwConfirm,
                    email
                )
                navigateToSignUp(name, id, pw)
            } else {
                if (name.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                } else if (id.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())) {
                    Toast.makeText(this@SignUpActivity, "유효한 이메일을 입력하세요", Toast.LENGTH_SHORT).show()
                } else if (pw.length < 8 || !pw.contains(Regex("[A-Z]")) || !pw.contains(
                        Regex("[^A-Za-z0-9]")
                    )
                ) {
                    Toast.makeText(this, "비밀번호가 강도가 낮습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else if (pw != pwConfirm) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun initView(){
        editName = findViewById(R.id.editName)
        editId = findViewById(R.id.editId)
        editPw = findViewById(R.id.editPw)
        editPwConfirm = findViewById(R.id.editPwConfirm)
        editEmail = findViewById(R.id.editEmail)
    }
    //모두 입력받아야 SignInActivity 로 이동함
//    private fun allFieldsEntered(): Boolean {
//        val name = editName.text.toString()
//        val id = editId.text.toString()
//        val pw = editPw.text.toString()
//        return name.isNotEmpty() && id.isNotEmpty() && pw.isNotEmpty()
//    }

    private fun navigateToSignUp(name: String, id: String, pw: String) {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("id", id)
        intent.putExtra("pw", pw)
        setResult(RESULT_OK, intent)
        finish()
    }

}







