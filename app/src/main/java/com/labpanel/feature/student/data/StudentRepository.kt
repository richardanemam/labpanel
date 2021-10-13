package com.labpanel.feature.student.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.labpanel.feature.common.domain.model.OpeningsDataModel
import com.labpanel.feature.professor.domain.states.OpeningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(private val databaseReference: DatabaseReference) {

    companion object {
        private const val TAG = "onCancelled"
    }

    suspend fun fetchAllOpenings(openingsState: MutableLiveData<OpeningsState>) {
        withContext(Dispatchers.IO) {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val allOpenings = mutableListOf<OpeningsDataModel>()
                    for (child in snapshot.children) {
                        child.children.forEach { childSnapshot ->
                            childSnapshot.getValue(OpeningsDataModel::class.java)?.let { allOpenings.add(it) }
                        }
                    }
                    openingsState.postValue(OpeningsState.AvailableOpenings(allOpenings))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, error.message, error.toException())
                    openingsState.postValue(OpeningsState.UnavailableOpenings)
                }
            })
        }
    }
}