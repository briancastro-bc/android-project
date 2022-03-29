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
    private var title: TextView? = null
    private var identification: TextView? = null
    private var name: TextView? = null
    private var age: TextView? = null
    private var phoneNumber: TextView? = null
    private var address: TextView? = null
    private var subjects: TextView? = null
    private var grades: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        studentId = intent.extras?.get("student_id") as String?
    }

    override fun onStart() {
        super.onStart()
        try {
            student = SchoolService.getOne(studentId)
            //var subjects2: String = student?.subjects!!.forEach {
                //return "${it} "
            //}
            var subjects: String = ""
            for (subject in student!!.subjects) {
                subjects += "${subject} "
            }
            title?.text = "Detalles del estudiante ${student?.name!!.split(' ')[0]}"
            identification?.text = student?.identification
            name?.text = student?.name
            age?.text = student?.age.toString()
            phoneNumber?.text = student?.phoneNumber
            address?.text = student?.address
            this.subjects?.text = subjects
        } catch (e: Exception) {
            //Do something
            Toast.makeText(this, "No pudimos mostrar los datos", Toast.LENGTH_LONG).show()
        }
    }

    private fun init() {
        title = findViewById(R.id.title)
        identification = findViewById(R.id.identification)
        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        phoneNumber = findViewById(R.id.phone_number)
        address = findViewById(R.id.address)
        subjects = findViewById(R.id.subjects)
        grades = findViewById(R.id.grades)
    }
}