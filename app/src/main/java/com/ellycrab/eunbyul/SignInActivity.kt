package com.ellycrab.eunbyul

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.math.log

class SignInActivity : AppCompatActivity() {

    private lateinit var editId: EditText
    private lateinit var editPw: EditText
    // 회원가입 결과 콜백변수 초기화-https://velog.io/@ho-taek/Android-registerForActivityResult%EB%9E%80 블로그 참조함
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // EditText 필드를 초기화
        editId = findViewById(R.id.editId)
        editPw = findViewById(R.id.editPw)

        // 회원가입 결과 처리를 설정
        setResultSignUp()

        // 로그인 버튼을 눌렀을 때의 동작을 설정
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            if (allFieldsEntered()) {
                // 모든 필드가 입력되었으면 로그인을 진행
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
                    val id = it.getStringExtra("id") ?: ""
                    val pw = it.getStringExtra("pw") ?: ""
                    editId.setText(id)
                    editPw.setText(pw)
                }
            }
        }
    }
}