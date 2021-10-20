package com.data.api

import android.util.Log
import com.data.mappers.OpeningsMapper
import com.data.model.OpeningsResponse
import com.domain.FirabaseCallback
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

    suspend fun fetchAllOpenings(callback: FirabaseCallback, openingsMapper: OpeningsMapper) {
       withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val allOpenings = mutableListOf<OpeningsResponse>()
                    for (child in snapshot.children) {
                        child.children.forEach { childSnapshot ->
                            childSnapshot.getValue(OpeningsResponse::class.java)?.let { allOpenings.add(it) }
                        }
                    }
                    callback.onCallback(openings = openingsMapper.map(allOpenings))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "Data could not be retrieved from Database." + error.message, error.toException())
                }
            })
        }
    }
}