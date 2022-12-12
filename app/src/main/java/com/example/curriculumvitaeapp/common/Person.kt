package com.example.curriculumvitaeapp.common

import java.io.Serializable

class Person(
    var avatar: Int,
    var firstName: String,
    var lastName: String,
    var password: String,
    var profession: String,
    var about: String,
    var webs: String,
    var certifications: ArrayList<String>,
    var skills: ArrayList<String>,
    var educations: ArrayList<Education>,
    var contact: Contact,
    var projects: ArrayList<Project>,
    var jobs: ArrayList<Job>
) : Serializable {

    fun add(newSkill:String) {
        this.skills.add(newSkill)
    }

    fun removeLastSkill() {
        this.skills.removeLast()
    }
}