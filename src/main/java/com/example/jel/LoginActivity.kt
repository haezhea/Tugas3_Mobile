package com.example.jel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.jel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>("user")

        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmailLogin.text.toString().trim()
            val password = binding.inputPassLogin.text.toString().trim()

            if (validateInput(email, password)) {
                // check user if it null
                if (user == null) {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("user", user)
                }
                startActivity(intent)
            }
        }

        binding.dontHaveTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.inputEmailLogin.error = "Email is required"
            binding.inputEmailLogin.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.inputPassLogin.error = "Password is required"
            binding.inputPassLogin.requestFocus()
            return false
        }

        return true
    }

    fun onClickRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}