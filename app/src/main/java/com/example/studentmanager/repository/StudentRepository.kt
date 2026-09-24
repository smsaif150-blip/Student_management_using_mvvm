package com.example.studentmanager.repository

import com.example.studentmanager.data.Student

class StudentRepository {
   private val students = mutableListOf<Student>(
        Student("1","Saif","21"),
        Student("2","Rahim","22"),
        Student("3","Karim","23")
    )
     fun getStudents(): List<Student>
    {
        return students.toList()
    }

    fun addStudent(student: Student)
    {
        students.add(student)
        println("Students: $students")
    }

    fun deleteStudent(student: Student)
    {
        students.remove(student)
    }

    fun updateStudent(student: Student)
    {
        val index = students.indexOfFirst { it.id == student.id }

        if (index != -1 )
        {
            students[index] = student
        }
    }
}