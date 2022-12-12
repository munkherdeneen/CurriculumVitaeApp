package com.example.curriculumvitaeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.curriculumvitaeapp.common.*
import com.example.curriculumvitaeapp.fragments.AboutFragment
import com.example.curriculumvitaeapp.fragments.ContactFragment
import com.example.curriculumvitaeapp.fragments.HomeFragment
import com.example.curriculumvitaeapp.fragments.JobFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val KEY = "PERSON"
    val PREF = "CVPREF"
    var users = ArrayList<Person>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPerson()

        val person = users.get(0)
        supportActionBar?.title = person.firstName
        supportActionBar?.subtitle = person.profession

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment().newInstance(person), "Home")
        adapter.addFragment(AboutFragment().newInstance(person), "About")
        adapter.addFragment(JobFragment().newInstance(person), "Work")
        adapter.addFragment(ContactFragment().newInstance(person), "Contact")

        viewPager.adapter = adapter

        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.about)
        tabs.getTabAt(2)!!.setIcon(R.drawable.work)
        tabs.getTabAt(3)!!.setIcon(R.drawable.contact)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.m1 -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true;
            }

            R.id.m3 -> {
                val pref = getSharedPreferences("CVPREF", Context.MODE_PRIVATE)
                val edit = pref.edit();
                edit.remove("auth")
                edit.remove("email")
                edit.apply()
                finish()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun createPerson() {
        //---------------------------------Contact---------------------------------//
        var contact1: Contact = Contact("312998877", "menkhtaivan@miu.edu.mn","munkherdene","mooeo2020","https://github.com/munkherdeneen")

        //---------------------------------Contact end---------------------------------//
        //---------------------------------Education---------------------------------//
        var education1: Education = Education("Bachelor of Information Technology", "National University of Mongolia ","2006-2010")
        var education1_1: Education = Education("Master of Computer Science", "MIU", "2021-2024")
        //---------------------------------Education end---------------------------------//
        //---------------------------------Project---------------------------------//
        var project1: Project = Project("Microservices project","2017-2019", "System developer")
        var project1_1: Project = Project("DevOps Transformation Program","2019-2021", "Project Lead")
        //---------------------------------Project end---------------------------------//
        //---------------------------------Work---------------------------------//
        var work1: Job = Job("Mobicom corporation", "2010-2013","System developer")
        var work1_1: Job = Job("Khan Bank of Mongolia", "2015-2021","Manager")
        //---------------------------------Work end---------------------------------//

        //---------------------------------Person1---------------------------------//
        var person1Certs: ArrayList<String> = arrayListOf("LPIC1", "Oracle", "Agile", "DevOps organization Transformation", "Python")
        var person1Skills: ArrayList<String> = arrayListOf("Java", "NodeJS", "Javascript", "DevOps", "Transformation")
        var person1: Person = Person(R.drawable.munkh, "Munkh-Erdene", "Enkhtaivan", "miu123", "Front-End Developer", "I am over 8+ years of IT experience which includes 6+ years of Extensive experience as a Front End Developer and 3 years of Experience as a UI/UX Developer and 2 years of Experience as mobile application Developer.\n", "www.leetcode.com/gzo326", person1Certs, person1Skills, arrayListOf(education1, education1_1),contact1, arrayListOf(project1,project1_1), arrayListOf(work1))

        users.add(person1)
    }
}