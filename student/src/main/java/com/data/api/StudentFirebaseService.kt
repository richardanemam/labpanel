package com.data.api

import android.util.Log
import com.data.model.OpeningsResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentFirebaseService(private val databaseReference: DatabaseReference) {

    companion object {
        private const val TAG = "onCancelled"
    }

    private val allOpenings = mutableListOf<OpeningsResponse>()

    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (child in snapshot.children) {
                child.children.forEach { childSnapshot ->
                    childSnapshot.getValue(OpeningsResponse::class.java)?.let { allOpenings.add(it) }
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w(TAG, error.message, error.toException())
        }
    }

    suspend fun fetchAllOpenings(): List<OpeningsResponse> {
        return withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(valueEventListener)
            allOpenings
        }
    }
}