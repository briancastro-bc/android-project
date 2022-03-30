package com.bc.notes_application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bc.notes_application.R
import com.bc.notes_application.interfaces.Student

class StudentAdapter(val students: MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolderStudent>() {

    class ViewHolderStudent(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.student_list_name)
        var average: TextView = view.findViewById(R.id.student_list_average)
        var status: TextView = view.findViewById(R.id.student_list_status)

        fun displayData(student: Student) {
            name.text = student.name
            average.text = student.phoneNumber
            status.text = student.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStudent {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderStudent(layoutInflater.inflate(R.layout.item_student, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderStudent, position: Int) {
        holder.displayData(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
}