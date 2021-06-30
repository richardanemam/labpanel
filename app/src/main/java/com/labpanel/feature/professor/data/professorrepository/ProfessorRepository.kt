package com.labpanel.feature.professor.data.professorrepository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.labpanel.feature.app.domain.model.OpeningModel
import com.labpanel.feature.professor.domain.states.AddValueEventState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessorRepository(val auth: FirebaseAuth, val databaseReference: DatabaseReference) {

    companion object {
        private const val PATH = "RegisteredOpenings"
        private const val TAG = "onCancelled"
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
                    Log.w(TAG, error.message, error.toException())
                }
            })
        }

        return professorCurrentOpenings
    }

    suspend fun addDataToFirebase(opening: OpeningModel,
                                  addValueEventState: MutableLiveData<AddValueEventState>) {

        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    auth.currentUser?.uid?.let { uid ->
                        opening.title?.let { title ->
                            databaseReference.child(uid).child(title).setValue(opening)
                            addValueEventState.postValue(AddValueEventState.DataChanged)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, error.message, error.toException())
                    addValueEventState.postValue(AddValueEventState.Cancelled)
                }
            })
        }
    }
}