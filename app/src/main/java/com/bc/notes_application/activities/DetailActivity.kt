package com.bc.notes_application.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bc.notes_application.R
import com.bc.notes_application.interfaces.Student
import com.bc.notes_application.services.SchoolService
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    var studentId: String? = null
    var student: Student? = null
    var name: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        studentId = intent.extras?.get("student_id") as String?
        try {
            Toast.makeText(this, "Student: ${studentId}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "No student created", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        student = SchoolService.getOne(studentId)
        //student = students.filter { it.identification == studentId }
        Toast.makeText(this, "Student found: ${student}", Toast.LENGTH_LONG).show()
        name?.text = student?.name
    }

    private fun init() {
        name = findViewById(R.id.name)
    }
}