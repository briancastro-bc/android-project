package com.bc.notes_application.services

import com.bc.notes_application.interfaces.Student

class SchoolService {
    companion object {
        private var students: MutableList<Student> = mutableListOf<Student>()

        fun getAll(): MutableList<Student> = this.students;

        fun getOne(identification: String?): Student? {
            return students.find { student ->  student.identification == identification }
        }

        fun addOne(student: Student) {
            this.students.add(student)
        }

        fun addMany(students: ArrayList<Student>) {
            for (student: Student in students) {
                this.students.add(student)
            }
        }

        fun removeOne(student: Student): Unit {
            this.students.remove(student)
        }

        fun average(student: Student): Double {
            var sum: Double = 0.0
            for(grade in student?.grades) {
                sum += grade
            }
            return (sum / student.grades.count())
        }

        fun canRetakeSubject(average: Double): Boolean {
            if(average > 25) {
                return true
            }
            return false
        }
    }
}