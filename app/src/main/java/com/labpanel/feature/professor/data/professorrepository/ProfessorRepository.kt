package com.labpanel.feature.professor.data.professorrepository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.labpanel.feature.app.domain.model.OpeningModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessorRepository(val auth: FirebaseAuth, val databaseReference: DatabaseReference) {

    companion object {
        private const val PATH = "RegisteredOpenings"
        private const val TAG = "FailToRetrieveOpenings"
    }

    suspend fun fetchOpenings(): List<OpeningModel> {
        val professorCurrentOpenings = mutableListOf<OpeningModel>()
        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        child.getValue(OpeningModel::class.java)?.let { opening ->
                            professorCurrentOpenings.add(
                                opening
                            )
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "loadPost:onCancelled", error.toException())
                }
            })
        }

        return professorCurrentOpenings
    }
}

