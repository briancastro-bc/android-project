package com.bc.notes_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bc.notes_application.R
import com.bc.notes_application.adapters.StudentAdapter
import com.bc.notes_application.interfaces.Student
import com.bc.notes_application.services.SchoolService

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StatisticsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var statistics: MutableMap<String, Int> = mutableMapOf(
        "students" to SchoolService.getAll().size,
        "winnerStudents" to 0,
        "loserCanRetake" to 0,
        "loserStudents" to 0
    )
    var studentsLength: TextView? = null
    var winnerStudents: TextView? = null
    var loserStudents: TextView? = null
    var studentsCanRetake: TextView? = null

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
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
        showStats()
        studentsLength?.text = statistics["students"].toString()
        winnerStudents?.text = statistics["winnerStudents"].toString()
        studentsCanRetake?.text = statistics["loserCanRetake"].toString()
        loserStudents?.text = statistics["loserStudents"].toString()
        displayStudentsList()
    }

    fun showStats() {
        for (student: Student in SchoolService.getAll()) {
            var average = SchoolService.average(student)
            if (average > 36 && average < 50) {
                statistics["winnerStudents"] = statistics["winnerStudents"]!!.plus(1)
            } else if(average > 25 && average < 36) {
                statistics["loserCanRetake"] = statistics["loserCanRetake"]!!.plus(1)
            } else if(average > 0 && average < 25){
                statistics["loserStudents"] = statistics["loserStudents"]!!.plus(1)
            }
        }
    }

    private fun displayStudentsList() {
        var studentsListRecycler: RecyclerView? = view?.findViewById(R.id.students_list_container)
        studentsListRecycler?.adapter = StudentAdapter(SchoolService.getAll())
        studentsListRecycler?.layoutManager = LinearLayoutManager(context)
    }

    private fun init() {
        studentsLength = view?.findViewById(R.id.students_length)
        winnerStudents = view?.findViewById(R.id.winner_students_length)
        loserStudents = view?.findViewById(R.id.loser_students_length)
        studentsCanRetake = view?.findViewById(R.id.loser_canRetake_students_length)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}