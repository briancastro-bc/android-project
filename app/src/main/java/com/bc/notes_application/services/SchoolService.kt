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
    }
}