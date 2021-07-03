package com.labpanel.feature.professor.data.professorrepository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.labpanel.feature.app.domain.helper.RegexHelper
import com.labpanel.feature.app.domain.model.OpeningsDataModel
import com.labpanel.feature.professor.domain.states.AddValueEventState
import com.labpanel.feature.professor.domain.states.OpeningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessorRepository(val auth: FirebaseAuth, val databaseReference: DatabaseReference) {

    companion object {
        private const val TAG = "onCancelled"
    }

    suspend fun fetchOpenings(openingsState: MutableLiveData<OpeningsState>) {
        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    auth.currentUser?.uid?.let { uid ->
                        val openings = mutableListOf<OpeningsDataModel>()
                        for (child in snapshot.child(uid).children) {
                            child.getValue(OpeningsDataModel::class.java)?.let { openings.add(it) }
                        }
                        openingsState.postValue(OpeningsState.AvailableOpenings(data = openings) )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, error.message, error.toException())
                    openingsState.postValue(OpeningsState.UnavailableOpenings)
                }
            })
        }
    }

    suspend fun addDataToFirebase(
        opening: OpeningsDataModel,
        addValueEventState: MutableLiveData<AddValueEventState>
    ) {

        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    auth.currentUser?.uid?.let { uid ->
                        opening.title?.let { title ->
                            databaseReference
                                .child(uid)
                                .child(RegexHelper.setTitleAsChild(title))
                                .setValue(opening)
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