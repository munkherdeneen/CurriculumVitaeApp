package com.example.curriculumvitaeapp.common

import java.io.Serializable

class Contact (var phoneNumber: String,
               var emailAddress: String,
               var linkedIn: String,
               var twitter: String,
               var github: String) : Serializable {
}