package burak.android.countryquiz

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CapitalViewModel(application: Application, private val capitalCounterViewModel: CapitalCounterViewModel) : AndroidViewModel(application) {

    var countries = mutableStateOf<List<Country>>(emptyList())
    var currentQuestion = mutableStateOf<Country?>(null)
    var options = mutableStateOf<List<String>>(emptyList())
    @SuppressLint("MutableCollectionMutableState")
    private var askedCountries = mutableStateOf<MutableList<Country>>(mutableListOf())
    var selectedDifficulty = mutableStateOf("all")

    init {
        loadCountries()
    }

    private fun loadCountries(){
        viewModelScope.launch {
            countries.value = loadCountriesFromJson(getApplication())
            generateNewQuestion()
        }
    }
    fun setDifficulty(difficulty: String){
        selectedDifficulty.value = difficulty
        resetQuestions()

        val filteredCountries = getFilteredCountries()
        capitalCounterViewModel.setFlagCount(filteredCountries.size)
    }
    fun generateNewQuestion(){
        val countryList = getFilteredCountries()
        if (askedCountries.value.size == countryList.size){ // If all countries have been asked, reset
            askedCountries.value.clear()
        }
        val remainingCountries = countryList.filter { it !in askedCountries.value }
        if (remainingCountries.isNotEmpty()){
            val randomCountry = remainingCountries.random()
            askedCountries.value.add(randomCountry) // Add country to asked country list

            val correctCapital = randomCountry.capital?.firstOrNull() ?: "Unknown"

            val inCorrectCapitals = countryList.filter { it != randomCountry }.shuffled().take(3).mapNotNull { it.capital?.firstOrNull() }
            val allOptions = (inCorrectCapitals + correctCapital).shuffled()

            currentQuestion.value = randomCountry
            options.value = allOptions
        }else{
            resetQuestions()
        }
    }
    fun getFilteredCountries(): List<Country>{
        return when(selectedDifficulty.value){
            "easy" -> countries.value.filter { it.difficulty == "easy" }
            "medium" -> countries.value.filter { it.difficulty == "medium" }
            "hard" -> countries.value.filter { it.difficulty == "hard" }
            else -> countries.value
        }
    }
    fun resetQuestions(){
        askedCountries.value.clear()
        currentQuestion.value = null
        options.value = emptyList()
        loadCountries()
    }
    fun resetAtTheEnd(){
        askedCountries.value.clear()
        currentQuestion.value = null
        options.value = emptyList()
    }

}