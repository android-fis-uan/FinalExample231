package co.edu.uan.android.finalexample418.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.uan.android.finalexample418.databinding.FragmentHomeBinding
import co.edu.uan.android.finalexample418.dto.Cat
import co.edu.uan.android.finalexample418.services.CatApi
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import java.lang.Exception

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        homeViewModel.cats.observe(viewLifecycleOwner) {
            showImage(homeViewModel.cats.value!!.get(0).url)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadImage() {
        homeViewModel.loadImage()
    }

    fun loadImageIonGson() {
        Ion.with(this)
            .load("https://api.thecatapi.com/api/images/get?format=json&size=med&results_per_page=3")
            .asString()
            .setCallback(object: FutureCallback<String>{
                override fun onCompleted(e: Exception?, result: String?) {
                    val gson = Gson()
                    val cats = gson.fromJson(result, Array<Cat>::class.java)
                    showImage(cats[0].url)
                    Log.e("CATAPI",cats.toString())
                }
            })
    }

    fun loadImageIon() {
        Ion.with(this)
            .load("https://api.thecatapi.com/api/images/get?format=json&size=med&results_per_page=3")
            .asJsonArray()
            .setCallback(object: FutureCallback<JsonArray>{
                override fun onCompleted(e: Exception?, result: JsonArray?) {
                    val url = result!!.get(0).asJsonObject.get("url").asString
                    showImage(url)
                    Log.e("CATAPI",e?.message, e)
                    Log.e("CATAPI",result!!.toString())
                }
            })
    }

    fun showImage(url: String) {
        Ion.with(this).load(url)
            .withBitmap()
            .intoImageView(binding.catImage)
    }
}