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

class SignUpActivity : AppCompatActivity() {

    private lateinit var editName:EditText
    private lateinit var editId:EditText
    private lateinit var editPw:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        editName = findViewById(R.id.editName)
        editId = findViewById(R.id.editId)
        editPw = findViewById(R.id.editPw)


        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            if(allFieldsEntered()){
                navigateToSignUp()
            }else{
                Toast.makeText(this,"입력되지 않은 정보가 있습니다",Toast.LENGTH_SHORT).show()
            }
        }

    }


    //모두 입력받아야 SignInActivity 로 이동함
    private fun allFieldsEntered(): Boolean {
        val name = editName.text.toString()
        val id = editId.text.toString()
        val pw = editPw.text.toString()
        return name.isNotEmpty() && id.isNotEmpty() && pw.isNotEmpty()
    }
    private fun navigateToSignUp() {
        val intent = Intent(this,SignInActivity::class.java)
        intent.putExtra("id",editId.text.toString())
        intent.putExtra("pw",editPw.text.toString())
        setResult(RESULT_OK,intent)
        finish()

    }
}