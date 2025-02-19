package burak.android.countryquiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CapitalCounterViewModel: ViewModel() {
    private val _capitalCounter = mutableIntStateOf(0)
    val capitalCounter : State<Int> = _capitalCounter

    fun capitalCorrectCounterIncrement(){
        _capitalCounter.intValue++
    }
    fun capitalCorrectCounterReset(){
        _capitalCounter.intValue = 0
    }
}