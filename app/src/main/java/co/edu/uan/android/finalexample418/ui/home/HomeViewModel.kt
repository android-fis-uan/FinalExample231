package co.edu.uan.android.finalexample418.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.finalexample418.databases.CatsDatabase
import co.edu.uan.android.finalexample418.dto.Cat
import co.edu.uan.android.finalexample418.entities.CatEntity
import co.edu.uan.android.finalexample418.services.CatApi
import com.koushikdutta.ion.Ion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var cats = MutableLiveData<List<Cat>>()

    fun loadImage() {
        viewModelScope.launch() {
            val api = CatApi.getInstance()
            cats.value = api.getCats(5)
            Log.v("CATAPI",cats.value.toString())
            val db = CatsDatabase.getInstance(getApplication<Application>().applicationContext)
            db.dao().save(CatEntity("1","http://hshshhshshs"))
        }
    }


}