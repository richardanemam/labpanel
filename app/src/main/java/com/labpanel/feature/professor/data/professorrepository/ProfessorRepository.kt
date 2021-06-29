package com.labpanel.feature.professor.data.professorrepository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.labpanel.feature.app.domain.model.OpeningModel

class ProfessorRepository(val auth: FirebaseAuth, val databaseReference: DatabaseReference) {

    companion object {
        private const val PATH = "RegisteredOpenings"
    }

    fun fetchOpenings(): List<OpeningModel?> {
        val professorCurrentOpenings = mutableListOf<OpeningModel>()
        auth.uid?.let { uid ->
            databaseReference.child(PATH).child(uid).get().addOnSuccessListener {
                for(snapshot in it.children) {
                    snapshot.getValue(OpeningModel::class.java)?.let { opening ->
                        professorCurrentOpenings.add(
                            opening
                        )
                    }
                }
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
        return professorCurrentOpenings
    }
}