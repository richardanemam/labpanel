package com.labpanel.feature.common.presentation.view.openinginfo

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labpanel.feature.common.presentation.view.openinginfo.OpeningInfoActivity.Companion.EXTRA_OPENING_INFO
import com.labpanel.feature.common.presentation.view.viewevents.OnBundle

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