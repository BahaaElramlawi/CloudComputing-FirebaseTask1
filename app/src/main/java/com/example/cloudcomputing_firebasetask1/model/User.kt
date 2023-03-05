package com.example.cloudcomputing_firebasetask1.model

import com.google.firebase.firestore.DocumentId

data class User(@DocumentId var id: String, var name: String, var phone: String, var city: String)