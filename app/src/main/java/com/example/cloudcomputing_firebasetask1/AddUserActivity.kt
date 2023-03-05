package com.example.cloudcomputing_firebasetask1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cloudcomputing_firebasetask1.databinding.ActivityAddUserBinding
import com.example.cloudcomputing_firebasetask1.model.User
import com.google.firebase.firestore.FirebaseFirestore

class AddUserActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        binding.btnAdd.setOnClickListener {
            val student = User(
                "", binding.txtName.text.toString(), binding.txtPhone.text.toString(),
                binding.txtCity.text.toString()
            )
            addStudent(student)
        }
    }

    private fun addStudent(student: User) {
        db.collection("users")
            .add(student)
            .addOnSuccessListener { documentReference ->
                Log.e("bha", documentReference.id)
                Log.e("bha", "Added Successfully")
            }
            .addOnFailureListener {
                Log.e("bha", it.message.toString())
            }
    }
}