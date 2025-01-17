package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    
        firebaseAuth = FirebaseAuth.getInstance()

        binding.backButton.setOnClickListener {
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.submitButton.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.createpassword.text.toString()
            val confirmPass = binding.confirmpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        print(it)
                        if(it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                    }
                    }

                }else{
                    Toast.makeText(this,"Password is not matching",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Feild",Toast.LENGTH_SHORT).show()
            }
        }

    }
}