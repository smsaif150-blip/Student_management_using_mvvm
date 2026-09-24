package com.example.studentmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.studentmanager.data.Student
import com.example.studentmanager.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class StudentViewModel: ViewModel() {

    private val repository = StudentRepository()


    private val _student = MutableStateFlow<List<Student>>(emptyList())
    val student: StateFlow<List<Student>> = _student

    fun getStudents()
    {
        _student.value = repository.getStudents()
    }

    fun addStudent(name: String,age: String)
    {
        repository.addStudent(Student(id = UUID.randomUUID().toString(),name = name,age = age))
        getStudents()
    }

    fun deleteStudent(student: Student)
    {
        repository.deleteStudent(student)
        getStudents()
    }

    fun updateStudent(student: Student)
    {
        repository.updateStudent(student)
        getStudents()
    }
}