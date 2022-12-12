package com.example.curriculumvitaeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.curriculumvitaeapp.R
import com.example.curriculumvitaeapp.common.FlowLayout
import com.example.curriculumvitaeapp.common.Person

import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_small.view.*

class AboutFragment : Fragment() {
    private val KEY = "person"
    private lateinit var person: Person

    fun newInstance(person: Person): AboutFragment {
        val args = Bundle()
        val fragment = AboutFragment()
        args.putSerializable(KEY, person)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        person = arguments?.getSerializable(KEY) as Person

        val eduParent = view.findViewById<LinearLayout>(R.id.educations)
        val certParent = view.findViewById<FlowLayout>(R.id.certs)

        for (edu in person.educations) {
            val item = inflater.inflate(R.layout.item, container, false)
            item.title.text= edu.title
            item.place.text= edu.school
            item.year.text= edu.years
            eduParent.addView(item)
        }

        for (str in person.certifications) {
            val item = inflater.inflate(R.layout.item_small, container, false)
            item.item_txtView.text = str
            certParent.addView(item)
        }

        return view
    }
}
