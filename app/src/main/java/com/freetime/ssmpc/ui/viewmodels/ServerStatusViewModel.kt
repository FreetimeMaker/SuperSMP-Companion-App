package com.freetime.ssmpc.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freetime.ssmpc.data.status.McSrvStatResponse
import com.freetime.ssmpc.data.status.ServerStatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ServerStatusViewModel(private val repository: ServerStatusRepository = ServerStatusRepository()) : ViewModel() {
    private val _status = MutableStateFlow<McSrvStatResponse?>(null)
    val status: StateFlow<McSrvStatResponse?> = _status

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun refreshStatus(ip: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _status.value = repository.getServerStatus(ip)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
