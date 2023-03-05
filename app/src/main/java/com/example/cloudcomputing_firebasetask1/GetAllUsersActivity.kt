package com.example.cloudcomputing_firebasetask1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloudcomputing_firebasetask1.adapter.UserAdapter
import com.example.cloudcomputing_firebasetask1.databinding.ActivityGetAllUsersBinding
import com.example.cloudcomputing_firebasetask1.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class GetAllUsersActivity : AppCompatActivity() {
    lateinit var binding: ActivityGetAllUsersBinding
    lateinit var db: FirebaseFirestore
    lateinit var studentAdapter: UserAdapter
    val data = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetAllUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        studentAdapter = UserAdapter(this, data)
        binding.rvUsers.adapter = studentAdapter


        getAllStudents()
    }

    private fun getAllStudents() {
        db.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.e("bha", document.id)
                    val student = User(
                        document.id,
                        document.getString("name")!!,
                        document.getString("phone")!!,
                        document.get("city").toString()
                    )

                    data.add(student)
                }
                Log.e("bha", data.size.toString())
                studentAdapter = UserAdapter(this, data)
                binding.rvUsers.adapter = studentAdapter
            }
            .addOnFailureListener { error ->
                Log.e("bha", error.message.toString())
            }
    }

    private fun deleteStudentById(id: String) {
        db.collection("users").document(id)
            .delete()
            .addOnSuccessListener {
                Log.e("bha", "Deleted Successfully")
            }
            .addOnFailureListener {
                Log.e("bha", it.message.toString())
            }
    }
}