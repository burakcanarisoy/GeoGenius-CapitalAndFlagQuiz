package burak.android.countryquiz

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FlagViewModel(application: Application, private val flagCounterViewModel: FlagCounterViewModel): AndroidViewModel(application) {

    var countries = mutableStateOf<List<Country>>(emptyList())
    var currentQuestion = mutableStateOf<Country?>(null)
    var options = mutableStateOf<List<String>>(emptyList())
    @SuppressLint("MutableCollectionMutableState")
    private var askedFlags = mutableStateOf<MutableList<Country>>(mutableListOf())
    var selectedDifficulty = mutableStateOf("all")
    init {
        loadCountries() // Load country list and create first questions when application started
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
        flagCounterViewModel.setFlagCount(filteredCountries.size)
    }
    fun generateNewQuestion(){
        val countryList = getFilteredCountries()
        if (askedFlags.value.size == countryList.size){ // If all countries have been asked, reset
            askedFlags.value.clear()
        }

        val remaniningFlags = countryList.filter { it !in askedFlags.value }

        if (remaniningFlags.isNotEmpty()){
            val randomCountry = remaniningFlags.random()
            askedFlags.value.add(randomCountry) // Add country to asked country list

            val correctOption = randomCountry.name.common

            val inCorrectOptions = countryList.filter { it != randomCountry }.shuffled().take(3).map { it.name.common }
            val allOptions = (inCorrectOptions + correctOption).shuffled()

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
        askedFlags.value.clear()
        currentQuestion.value = null
        options.value = emptyList()
        loadCountries()
    }
    fun resetAtTheEnd(){
        askedFlags.value.clear()
        currentQuestion.value = null
        options.value = emptyList()
    }


}