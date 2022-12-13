package com.example.curriculumvitaeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.curriculumvitaeapp.R
import com.example.curriculumvitaeapp.common.Person
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private val KEY = "person"
    private lateinit var person: Person

    fun newInstance(person: Person): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        args.putSerializable(KEY, person)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        person = arguments?.getSerializable(KEY) as Person

        view.name.text = "${person.firstName} ${person.lastName}"
        view.position.text = person.profession
        view.about.text = person.about
        view.avatar.setImageResource(person.avatar)

        return view
    }
}
