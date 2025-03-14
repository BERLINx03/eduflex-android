package com.example.eduflex.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduflex.auth.domain.usecase.LoginUseCase
import com.example.eduflex.auth.domain.usecase.RegisterUseCase
import com.example.eduflex.auth.presentation.intents.AuthIntents
import com.example.eduflex.auth.presentation.state.AuthState
import com.example.eduflex.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    private val _successfulLogin = MutableSharedFlow<Unit>()
    val successfulLogin = _successfulLogin.asSharedFlow()

    private val _successfulRegister = MutableSharedFlow<Unit>()
    val successfulRegister = _successfulRegister.asSharedFlow()

    fun onIntent(intent: AuthIntents){
        when (intent){
            is AuthIntents.EmailChanged -> onEmailChanged(intent.email)
            is AuthIntents.PasswordChanged -> onPasswordChanged(intent.password)
            is AuthIntents.RoleChanged -> onRoleChanged(intent.role)
            is AuthIntents.UsernameChanged -> onUsernameChanged(intent.username)
            is AuthIntents.Login -> intent.apply {
                loginUser(email, password)
            }
            is AuthIntents.Register -> intent.apply {
                registerUser(username, email, password, role)
            }
        }
    }
    private fun onUsernameChanged(username: String) {
        _authState.update {
            it.copy(username = username)
        }
    }
    private fun onEmailChanged(email: String) {
        _authState.update {
            it.copy(email = email)
        }
    }
    private fun onPasswordChanged(password: String) {
        _authState.update {
            it.copy(password = password)
        }
    }
    private fun onRoleChanged(role: String) {
        _authState.update {
            it.copy(role = role)
        }
    }

    private fun registerUser(username: String, email: String, password: String, role: String) {
        executeUseCase(
            action = { registerUseCase(username, email, password, role) },
            onSuccess = {_successfulRegister.emit(Unit)}
        )
    }
    private fun loginUser(email: String, password: String) {
        executeUseCase(
            action = { loginUseCase(email, password) },
            onSuccess = {_successfulLogin.emit(Unit)}
        )
    }

    private fun <T> executeUseCase(
        action: suspend () -> Resource<T>,
        onSuccess: suspend () -> Unit
    ) {
        viewModelScope.launch {
            _authState.update { it.copy(isLoading = true) }

            val result = withContext(Dispatchers.IO) { action() }

            when (result) {
                is Resource.Error -> {
                    _authState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
                is Resource.Success -> {
                    _authState.update {
                        it.copy(isLoading = false, errorMessage = null)
                    }
                    onSuccess()
                }
                is Resource.Loading -> {
                    // already updated above
                }
            }
        }
    }
}