package com.openinginfo.presentation.view

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openinginfo.presentation.view.OpeningInfoActivity.Companion.EXTRA_OPENING_INFO
import com.openinginfo.presentation.states.OnBundle

class OpeningInfoViewModel : ViewModel() {

    private val bundleValidation: MutableLiveData<OnBundle> = MutableLiveData()
    val onBundleValidation: LiveData<OnBundle> = bundleValidation

    fun validateExtras(intent: Intent) {
        if (intent.hasExtra(EXTRA_OPENING_INFO)) {
            bundleValidation.value = OnBundle.BundleOk
        } else {
            bundleValidation.value = OnBundle.BundleNok
        }
    }
}