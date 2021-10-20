package com.data.api

import android.util.Log
import com.core.FirebaseResponse
import com.core.listener.FirebaseCallback
import com.data.mappers.OpeningsMapper
import com.data.model.Opening
import com.data.model.OpeningsResponse
import com.domain.extensions.setAsChild
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessorFirebaseService(val auth: FirebaseAuth, val databaseReference: DatabaseReference) {

    companion object {
        private const val TAG = "onCancelled"
    }

    suspend fun fetchOpenings(callback: FirebaseCallback, openingsMapper: OpeningsMapper) {
        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val allOpenings = mutableListOf<OpeningsResponse>()
                    auth.currentUser?.uid?.let { uid ->
                        for (child in snapshot.child(uid).children) {
                            child.getValue(OpeningsResponse::class.java)?.let { allOpenings.add(it) }
                        }
                    }

                    callback.onCallback(openings = openingsMapper.map(allOpenings))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, error.message, error.toException())
                }
            })
        }
    }

    suspend fun addDataToFirebase(opening: Opening): Int {
        var responseCode = FirebaseResponse.ON_CANCELED
        return withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    auth.currentUser?.uid?.let { uid ->
                        opening.title?.let { title ->
                            databaseReference
                                .child(uid)
                                .child(title.setAsChild())
                                .setValue(opening)
                        }
                    }
                    responseCode = FirebaseResponse.ON_DATA_CHANGED
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, error.message, error.toException())
                    responseCode = FirebaseResponse.ON_CANCELED
                }

            })
            responseCode
        }
    }
}