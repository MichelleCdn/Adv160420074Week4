package com.jitusolution.adv160420074week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jitusolution.adv160420074week4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val studentLiveData = MutableLiveData<Student>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(studentID: String?) {
        fun fetch(studentID: String?) {
////        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
////            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
////        studentLD.value = student1
            loadingLD.value = true
            studentLoadErrorLD.value = false

            queue = Volley.newRequestQueue(getApplication())
            val url = "http://adv.jitusolution.com/student.php?id=$studentID"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<List<Student>>() {}.type
                    val result = Gson().fromJson<ArrayList<Student>>(response, sType)
                    studentsLD.value = result

                    loadingLD.value = false
                    Log.d("showvoley", result.toString())

                    loadingLD.value = false
                    Log.d("showvoley", response.toString())
                },
                {
                    Log.d("showvoley", it.toString())
                    studentLoadErrorLD.value = false
                    loadingLD.value = false
                })

            stringRequest.tag = TAG
            queue?.add(stringRequest)
        }

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}

