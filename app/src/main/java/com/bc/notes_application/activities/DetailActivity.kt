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
    private var status: TextView? = null
    private var average: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        studentId = intent.extras?.get("student_id") as String?
    }

    override fun onStart() {
        super.onStart()
        showStudentInformation()
    }

    private fun showStudentInformation() {
        try {
            student = SchoolService.getOne(studentId)
            var subjects: String = "Materias: "
            student?.subjects!!.forEach {
                subjects += "${it} "
            }
            var grades: String = "Notas: "
            student?.grades!!.forEach{
                grades += "${it} "
            }
            var average = SchoolService.average(student!!)
            title?.text = "Detalles del estudiante ${student?.name!!.split(' ')[0]}"
            identification?.text = student?.identification
            name?.text = student?.name
            age?.text = student?.age.toString()
            phoneNumber?.text = student?.phoneNumber
            address?.text = student?.address
            this.subjects?.text = subjects
            this.grades?.text = grades
            this.average?.text = average.toString()
            if (average > 35) {
                status?.text = "Gana el periodo"
            } else {
                if(SchoolService.canRetakeSubject(average)) {
                    status?.text = "Pierde con posibilidad de recuperacion"
                } else {
                    status?.text = "Perdio el periodo"
                }
            }
        } catch (e: Exception) {
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
        status = findViewById(R.id.status)
        average = findViewById(R.id.average)
    }
}