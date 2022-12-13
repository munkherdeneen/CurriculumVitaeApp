package com.example.curriculumvitaeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.curriculumvitaeapp.common.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val KEY = "PERSON"
    val PREF = "CVPREF"
    var users = ArrayList<Person>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createPerson()

        val prefs = getSharedPreferences(PREF, MODE_PRIVATE)

        if (prefs.getBoolean("auth", false)) {
            val email = prefs.getString("email", "")
            if (email != null) {
                val foundUser = findUserByEmail(email)
                if(foundUser != null) {
                    startMainActivity(foundUser)
                }
            }
        }

        login.setOnClickListener {
            val inputEmail = et_email.text.toString()
            if (isValidated(inputEmail, et_password.text.toString())) {
                val editor = prefs.edit()

                editor.putBoolean("auth", true)
                editor.putString("email", inputEmail)
                editor.apply()

                val foundUser = findUserByEmail(inputEmail)
                if(foundUser != null) {
                    startMainActivity(foundUser)
                }
            } else {
                Toast.makeText(applicationContext, "Wrong credential", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startMainActivity(person: Person) {
        var mainIntent = Intent(applicationContext, MainActivity::class.java)

        mainIntent.putExtra(KEY, person)
        startActivity(mainIntent)
    }

    private fun isValidated(email: String, password: String): Boolean {
        for (user in users) {
            if (user.contact.emailAddress == email && user.password == password) {
                return true
            }
        }
        return false
    }

    private fun findUserByEmail(email: String): Person? {
        for (user in users) {
            if (user.contact.emailAddress == email) {
                println(user.contact.emailAddress)
                return user
            }
        }
        return null
    }

    fun createPerson() {
        //Contact
        var contact1: Contact = Contact("312998877", "menkhtaivan@miu.edu.mn","munkherdene-enkhtaivan","mooeo2020","munkherdeneen")

        //Education
        var education1: Education = Education("Bachelor of Information Technology", "National University of Mongolia ","2006-2010")
        var education1_1: Education = Education("Master of Computer Science", "MIU", "2021-2024")

        //Project
        var project1: Project = Project("Microservices project","2017-2019", "System developer")
        var project1_1: Project = Project("DevOps Transformation Program","2019-2021", "Project Lead")

        //Work
        var work1: Job = Job("Mobicom corporation", "2010-2013","System developer")
        var work1_1: Job = Job("Khan Bank of Mongolia", "2015-2021","Manager")

        //Person
        var person1Certs: ArrayList<String> = arrayListOf("LPIC1", "Oracle", "Agile", "DevOps organization Transformation", "Python")
        var person1Skills: ArrayList<String> = arrayListOf("Java", "NodeJS", "Javascript", "DevOps", "Transformation")
        var person1: Person = Person(R.drawable.munkh, "Munkh-Erdene", "Enkhtaivan", "munkh99", "DevOps engineer", "I have over 10 years of hands-on professional experience Coding, Design, Architecture, Automation, Operating, Maintaining, Leading, and Implementing solutions to enhance financial and banking services, automate processes, and meet system requirements. A talented engineer and team leader who is experienced in defining, managing, and executing full SDLC, from requirement through maintenance.\n", "www.leetcode.com/Munkherdene", person1Certs, person1Skills, arrayListOf(education1, education1_1),contact1, arrayListOf(project1,project1_1), arrayListOf(work1, work1_1))

        users.add(person1)
    }
}