package com.jitusolution.adv160420074week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jitusolution.adv160420074week4.R
import com.jitusolution.adv160420074week4.model.Student
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


        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {

            txtId.setText(it.id.toString())
            txtName.setText(it.name.toString())
            txtBod.setText(it.bod.toString())
            txtPhone.setText(it.phone.toString())


        })


    }
}
