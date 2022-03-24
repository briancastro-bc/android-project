package com.bc.notes_application.services

import com.bc.notes_application.interfaces.StudentInterface

class SchoolService {
    companion object {
        private var students: ArrayList<StudentInterface> = arrayListOf<StudentInterface>()

        fun getAll(): ArrayList<StudentInterface> = this.students;

        fun addOne(student: StudentInterface): Unit {
            this.students.add(student)
        }

        fun addMany(students: ArrayList<StudentInterface>): Unit {
            for (student: StudentInterface in students) {
                this.students.add(student)
            }
        }

        fun removeOne(student: StudentInterface): Unit {
            this.students.remove(student)
        }
    }
}