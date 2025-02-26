package burak.android.countryquiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class FlagCounterViewModel: ViewModel() {
    private val _flagCounter = mutableIntStateOf(0)
    val flagCounter : State<Int> = _flagCounter

    fun flagCorrectCounterIncrement(){
        _flagCounter.intValue++
    }
    fun flagCorrectCounterReset(){
        _flagCounter.intValue = 0
    }
    fun setFlagCount(count: Int){
        _flagCounter.intValue = 0
    }
}