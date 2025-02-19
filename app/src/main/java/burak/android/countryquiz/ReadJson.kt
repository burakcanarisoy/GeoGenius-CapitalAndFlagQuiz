package burak.android.countryquiz

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

//This function reads countries.json file from raw directory and it converts JSON data to List<Country> object with Gson.
fun loadCountriesFromJson(context: Context): List<Country>{
    val inputStream = context.resources.openRawResource(R.raw.countries)
    val reader = InputStreamReader(inputStream)
    val countryType = object : TypeToken<List<Country>>() {}.type
    return Gson().fromJson(reader,countryType)
}