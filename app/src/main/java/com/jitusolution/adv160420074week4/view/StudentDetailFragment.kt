package com.jitusolution.adv160420074week4.view

import android.database.Observable
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.txtId
import kotlinx.android.synthetic.main.student_list_item.txtName


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

            val student = viewModel.studentLiveData.value
            student?.let{
                txtId.setText(student.id)
                txtName.setText(student.name)
                txtBod.setText(student.bod)
                txtPhone.setText(student.phone)

                imageView2.loadImage(student.photoUrl, progressBar2)
            }



        }


    }
}
