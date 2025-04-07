package com.example.jel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jel.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            val user = binding.inputUsername.text.toString().trim()
            val email = binding.inputEmail.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()
            val confirmPassword = binding.inputConfirmPassword.text.toString().trim()

            if (validateInput(user, email, password, confirmPassword)) {
                val user = User(user, email, password)
//                pass the user object to the login activity
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("user", user)
                }
                startActivity(intent)
            }
        }
    }

    private fun validateInput(user:String, email: String, password: String, confirmPassword: String): Boolean {
        if (user.isEmpty()) {
            binding.inputUsername.error = "Username is required"
            binding.inputUsername.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            binding.inputEmail.error = "Email is required"
            binding.inputEmail.requestFocus()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inputEmail.error = "Enter a valid email"
            binding.inputEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.inputPassword.error = "Password is required"
            binding.inputPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            binding.inputPassword.error = "Password should be at least 6 characters long"
            binding.inputPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.inputConfirmPassword.error = "Confirm Password is required"
            binding.inputConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.inputConfirmPassword.error = "Passwords do not match"
            binding.inputConfirmPassword.requestFocus()
            return false
        }

        return true
    }
}