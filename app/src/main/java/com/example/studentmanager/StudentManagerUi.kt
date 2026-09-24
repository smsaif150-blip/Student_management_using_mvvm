package com.example.studentmanager


import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentmanager.data.Student
import com.example.studentmanager.ui.theme.Dark_Blue
import com.example.studentmanager.ui.theme.Dark_Brown
import com.example.studentmanager.ui.theme.Dark_Green
import com.example.studentmanager.ui.theme.Dark_Sweet
import com.example.studentmanager.ui.theme.Light_Blue
import com.example.studentmanager.ui.theme.Light_Blue_color
import com.example.studentmanager.ui.theme.Light_Brown
import com.example.studentmanager.ui.theme.Light_Green
import com.example.studentmanager.ui.theme.Light_Sweet
import com.example.studentmanager.ui.theme.StudentManagerTheme
import com.example.studentmanager.viewmodel.StudentViewModel

@Composable
fun StudentManager()
{
    val viewModel: StudentViewModel = viewModel()
    val students by viewModel.student.collectAsState()

    var name by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    var showDialog by remember{
        mutableStateOf(false)
    }

    var selectedStudent by remember {
        mutableStateOf<Student?>(null)
    }

    var EditStudent by remember {
        mutableStateOf<Student?>(null)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }
    var EditName by remember {
        mutableStateOf("")
    }
    var EditAge by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.getStudents()
    }

    val avatarColor = listOf(
        Light_Green,
        Light_Blue_color,
        Light_Sweet,
        Light_Brown
    )

    val characterColor = listOf(
        Dark_Green,
        Dark_Blue,
        Dark_Sweet,
        Dark_Brown
    )


    Box(modifier = Modifier.fillMaxSize().background(color = Color.Blue))
    {
        Column(modifier = Modifier.padding(start = 30.dp, top = 45.dp)) {
            Row {
                Box(modifier = Modifier.size(50.dp).background(color = Color.White.copy(alpha = 0.15f),
                    CircleShape),
                    contentAlignment = Alignment.Center)
                {
                    Icon(painter = painterResource(R.drawable.student_hat),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp))
                }

                Column(modifier = Modifier.fillMaxWidth().padding(start = 12.dp)) {
                    Text("Student Manager", color = Color.White, fontSize = 22.sp)
                    Text("Keep track of your students", color = Color.White, fontSize = 12.sp)
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize().padding(top = 110.dp).background(color = Light_Blue,
            RoundedCornerShape(topStart = 30.dp,topEnd = 30.dp)))
        {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier.fillMaxWidth().height(260.dp)
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 40.dp)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it}, modifier = Modifier.fillMaxWidth().height(52.dp),
                            placeholder = {
                                Text(text = "Enter student name", fontSize = 16.sp)
                                          },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    tint = Color.Black.copy(alpha = 0.3f)
                                )
                            },

                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_Blue,
                                unfocusedContainerColor = Light_Blue,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        OutlinedTextField(
                            value = age,
                            onValueChange = {age = it},
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            placeholder = {
                                Text(
                                    text = "Enter students age",
                                    fontSize = 16.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Color.Black.copy(alpha = 0.3f)
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_Blue,
                                unfocusedContainerColor = Light_Blue,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {viewModel.addStudent(name,age)
                                      name = ""
                                      age = ""}, colors = ButtonDefaults.buttonColors(Color.Blue),
                            modifier = Modifier.fillMaxWidth().height(40.dp),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Add Student")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(value = "", onValueChange = {},
                    placeholder = {
                        Text(text = "Search Student")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(52.dp).padding(horizontal = 20.dp))

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                if (students.isNotEmpty()) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(students){student->

                    Card(
                        modifier = Modifier.fillMaxWidth().height(60.dp).padding(
                            start = 20.dp,
                            end = 20.dp
                        ),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxSize().padding(start = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.size(45.dp)
                                    .background(color = avatarColor[students.indexOf(student) % avatarColor.size], shape = CircleShape),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Text(text = student.name.first().uppercase(), fontSize = 20.sp,
                                    color = characterColor[students.indexOf(student) % characterColor.size])
                            }

                            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                                Text(
                                    student.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("age : ${student.age}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }

                            Box(
                                modifier = Modifier.size(25.dp).background(
                                    color = Color.Blue.copy(alpha = 0.1f),
                                    RoundedCornerShape(4.dp)
                                )
                            )
                            {
                                IconButton(onClick = {
                                    EditStudent = student
                                    EditName = student.name
                                    EditAge = student.age
                                    showEditDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Create,
                                        contentDescription = null,
                                        tint = Color.Blue,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }


                            }
                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            Box(
                                modifier = Modifier.size(25.dp).background(
                                    color = Color.Red.copy(alpha = 0.1f),
                                    RoundedCornerShape(4.dp)
                                )
                            )
                            {
                                IconButton(onClick ={
                                    selectedStudent = student
                                    showDialog = true
                                }) {
                                    Icon(
                                        Icons.Default.Delete, null,
                                        tint = Color.Red,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.width(8.dp))
                                 }
                             }

                        }
                    }


                    if(showEditDialog)
                    {
                        AlertDialog(
                            onDismissRequest = {showEditDialog = false},
                            title = {Text("Dialog")},
                            text = {
                                Column {
                                    OutlinedTextField(
                                        value = EditName,
                                        onValueChange = {
                                            EditName = it
                                        },
                                        label = {Text("Edit student name")},
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))

                                    OutlinedTextField(
                                        value = EditAge,
                                        onValueChange = {
                                            EditAge = it
                                        },
                                        shape = RoundedCornerShape(12.dp),
                                        label = {Text("Edit Student age")}
                                    )
                                }

                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    EditStudent?.let { oldStudent ->
                                        viewModel.updateStudent(Student(
                                            oldStudent.id,EditName,EditAge
                                        ))
                                    }
                                    showEditDialog = false}) {
                                    Text("Confirm")
                                }

                            },
                            dismissButton = {
                                TextButton(onClick = {showEditDialog = false}) {
                                    Text(text = "Cancel")
                                }
                            }

                        )
                    }


                    if (showDialog)
                    {
                        AlertDialog(
                            onDismissRequest = {showDialog = false},
                            title = {Text("Dialog")},
                            text = {Text("Delete Student")},
                            confirmButton = {
                                TextButton(onClick = {
                                    selectedStudent?.let {
                                        viewModel.deleteStudent(it)
                                    }
                                    showDialog = false
                                    selectedStudent = null
                                }) {
                                    Text("Confirm")
                                }

                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    showDialog = false
                                    selectedStudent = null
                                }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSM()
{
    StudentManagerTheme {
        StudentManager()
    }
}