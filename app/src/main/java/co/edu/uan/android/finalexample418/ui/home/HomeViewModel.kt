package co.edu.uan.android.finalexample418.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.finalexample418.dto.Cat
import co.edu.uan.android.finalexample418.services.CatApi
import com.koushikdutta.ion.Ion
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var cats = MutableLiveData<List<Cat>>()

    fun loadImage() {
        viewModelScope.launch {
            val api = CatApi.getInstance()
            cats.value = api.getCats()
        }
    }


}