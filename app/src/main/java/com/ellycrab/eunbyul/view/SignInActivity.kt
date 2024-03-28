package com.ellycrab.eunbyul.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.ellycrab.eunbyul.R
import com.ellycrab.eunbyul.viewmodel.SignInViewModel
import com.ellycrab.eunbyul.viewmodel.SignUpViewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editId: EditText
    private lateinit var editPw: EditText

    // jetpack 라이브러리에서 제공하는 viewModels() 확장 함수를 사용하여 viewModel인스턴스를 생성한 것
    val viewModel: SignUpViewModel by viewModels()
    private val viewModelSignIn: SignInViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // View 초기화
        initSignInView()

        //viewModel로부터 데이터 가져오기
        viewModel.name.observe(this) { name ->
            editName.setText(name)
        }

        viewModel.id.observe(this) { id ->
            editId.setText(id)
        }

        viewModel.password.observe(this) { password ->
            editPw.setText(password)
        }


        // 회원가입 결과 처리를 설정
        setResultSignUp()

        // 로그인 버튼을 눌렀을 때의 동작을 설정
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            if (allFieldsEntered()) {
                // 모든 필드가 입력되었으면 로그인을 진행
                val id = editId.text.toString().trim()
                val pw = editPw.text.toString().trim()
                viewModelSignIn.updateLoginData(id,pw)
            }
        }

        // 회원가입 버튼을 눌렀을 때의 동작을 설정
        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            // 회원가입 화면으로 이동
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initSignInView() {
        editName = findViewById(R.id.editName)
        editId = findViewById(R.id.editId)
        editPw = findViewById(R.id.editPw)
    }

    // 모든 필드가 입력되었는지 확인
    private fun allFieldsEntered(): Boolean {
        // trim()으로 공백 제거
        val id = editId.text.toString().trim()
        val pw = editPw.text.toString().trim()

        return if (id.isNotEmpty() && pw.isNotEmpty()) {
            // 모든 필드가 입력되었다면 HomeActivity로 이동하고 ID를 전달
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            true
        } else {
            // 어떤 필드라도 비어 있으면 토스트 메시지를 표시하고 false를 반환
            Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            false
        }
    }


    // 회원가입 화면에서 결과를 처리하는 함수
    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.let {
                    val name = it.getStringExtra("name") ?: ""
                    val id = it.getStringExtra("id") ?: ""
                    val pw = it.getStringExtra("pw") ?: ""
//
                    viewModel.updateDataTryToSend(name, id, pw)
                    Toast.makeText(this@SignInActivity,
                        "이름: $name, 아이디: $id, 비밀번호: $pw", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}