package com.jitusolution.adv160420074week4.view

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jitusolution.adv160420074week4.R
import com.jitusolution.adv160420074week4.model.Student
import com.jitusolution.adv160420074week4.util.loadImage
import com.jitusolution.adv160420074week4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.txtId
import kotlinx.android.synthetic.main.student_list_item.txtName
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {


    private lateinit var viewModel:DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)
            observeViewModel()
        }


//        arguments?.let {
//            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
//            viewModel=ViewModelProvider(this).get(DetailViewModel::class.java)
//            viewModel.fetch(studentID)
//
//            viewModel.studentLiveData.observe(viewLifecycleOwner) {student ->
//                txtId.setText(student.id)
//                txtName.setText(student.name)
//                txtBod.setText(student.bod)
//                txtPhone.setText(student.phone)
//                imageView.loadImage(student.photoUrl, progressBar2)
//                btnDetail.setOnClickListener{
//                    Observable.timer
//                }
//            }
//        }

    }

    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner){

            //val student = viewModel.studentLiveData.value
            val student = it

            student?.let{
                txtId.setText(it.id)
                txtName.setText(it.name)
                txtBod.setText(it.bod)
                txtPhone.setText(it.phone)

                imageView2.loadImage(student.photoUrl, progressBar2)

                btnNotif.setOnClickListener {
                    io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.d("Messages", "five seconds")
                            MainActivity.showNotification(student.name.toString(),
                                "A new notification created",
                                R.drawable.ic_baseline_person_24)
                        }
                }

            }



        }


    }
}
