package com.coding.informer.androidviewmodelexample.views.component.login


import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.informer.androidviewmodelexample.data.DataRepository
import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.error.INVALID_USERNAME_AND_PASSWORD
import com.coding.informer.androidviewmodelexample.data.error.PASSWORD_ERROR
import com.coding.informer.androidviewmodelexample.data.error.USERNAME_ERROR
import com.coding.informer.androidviewmodelexample.data.login.LoginRequest
import com.coding.informer.androidviewmodelexample.data.login.LoginResponse
import com.coding.informer.androidviewmodelexample.utils.RegexUtils.isValidEmail
import com.coding.informer.androidviewmodelexample.utils.SingleEvent
import com.coding.informer.androidviewmodelexample.utils.wrapEspressoIdlingResource
import com.coding.informer.androidviewmodelexample.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = loginLiveDataPrivate

    /** Error handling as UI **/

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun doLogin(userName: String, password: String) {
        val isUsernameValid = isValidEmail(userName)
        val isPassWordValid = password.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(PASSWORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(USERNAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(INVALID_USERNAME_AND_PASSWORD)
        } else {
            viewModelScope.launch {
                loginLiveDataPrivate.value = Resource.Loading()
                wrapEspressoIdlingResource {
                    dataRepository.doLogin(loginRequest = LoginRequest(userName, password)).collect {
                        loginLiveDataPrivate.value = it
                    }
                }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}