package com.example.gdsc_adypu

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gdsc_adypu.databinding.ActivitySignupBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignupActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.Regbtn.setOnClickListener{
            val signupUsername = binding.username.text.toString()
            val signupPassword = binding.signuppass.text.toString()

            if (signupUsername.isNotEmpty() && signupPassword.isNotEmpty()){
                signupUser(signupUsername,signupPassword)
            }else{
                Toast.makeText(this@SignupActivity,"Field should not be empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.textView.setOnClickListener{
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
            finish()
        }
    }

    private fun signupUser(username:String, pass:String){
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(!dataSnapshot.exists()){
                    val id = databaseReference.push().key
                    val userData = UserData(id, username,pass)
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(this@SignupActivity,"Signup Successful",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this@SignupActivity,"User already exists",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignupActivity,"Database Error: ${databaseError.message}",Toast.LENGTH_LONG).show()
            }
        })
    }
}