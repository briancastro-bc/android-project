package com.bc.notes_application.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bc.notes_application.R
import com.bc.notes_application.activities.DetailActivity
import com.bc.notes_application.interfaces.Student
import com.bc.notes_application.services.SchoolService
import java.lang.Exception

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegisterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var identification: EditText? = null
    var name: EditText? = null
    var age: EditText? = null
    var phoneNumber: EditText? = null
    var address: EditText? = null
    var subjects: EditText? = null
    var grades: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
        val submitButton: Button? = view?.findViewById(R.id.input_button)
        submitButton?.setOnClickListener {
            this.onSubmit()
        }
    }

    private fun init() {
        identification = view?.findViewById(R.id.input_identification)
        name = view?.findViewById(R.id.input_name)
        age = view?.findViewById(R.id.input_age)
        phoneNumber = view?.findViewById(R.id.input_phone_number)
        address = view?.findViewById(R.id.input_address)
        subjects = view?.findViewById(R.id.input_subjects)
        grades = view?.findViewById(R.id.input_grades)
    }

    private fun onSubmit() {
        var grades = this.grades?.text.toString().split(',')
        var subjects = this.subjects?.text.toString().split(',')
        try {
            if(validate(grades, subjects)) {
                val student = Student(
                    identification = identification?.text.toString(),
                    name = name?.text.toString(),
                    age = this.age?.text.toString().toInt(),
                    phoneNumber = phoneNumber?.text.toString(),
                    address = address?.text.toString(),
                    subjects = subjects,
                    grades = grades
                )
                val intent = Intent(activity, DetailActivity::class.java)
                SchoolService.addOne(student)
                Toast.makeText(context, "Fue registrado el usuario ${student.name}. Edad: ${student.age}. Notas: ${student.grades}", Toast.LENGTH_SHORT).show()
                intent.putExtra("student_id", student.identification)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Deben haber la misma cantidad de materias y notas", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Debes llenar la informacion", Toast.LENGTH_LONG).show()
        }
    }

    private fun validate(grades: List<String>, subjects: List<String>, vararg args: String): Boolean {
        if (grades.count() == subjects.count()) {
            return true
        }
        return  false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}