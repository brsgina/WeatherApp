package hu.gina.tkweatherapp.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.gina.tkweatherapp.ui.utils.UiEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

open class ViewModelBase: ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    open var coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            val messageResId = when (throwable) {
                is java.net.UnknownHostException -> "Check network connection!"
                else -> "Error!"
            }
            sendUiEvent(UiEvent.ShowError(messageResId))
            throwable.printStackTrace()
            Log.d("ERROR","coroutine exception caught in $coroutineContext")
        }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun startIoCoroutine(myFun: suspend (CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.IO +coroutineExceptionHandler) {
            myFun(this)
        }
    }
}