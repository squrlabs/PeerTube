package com.squrlabs.peertube.mobile.ui.instance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository

class InstanceViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    val getInstance: LiveData<Resource<List<InstanceModel>>>
        get() = _getInstances

    private val _getInstances = instanceRepository.getInstances().asLiveData(viewModelScope.coroutineContext)

}
