package com.example.curriculumvitaeapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.curriculumvitaeapp.R
import com.example.curriculumvitaeapp.common.Person
import kotlinx.android.synthetic.main.fragment_contact.view.*

class ContactFragment : Fragment() {
    private val KEY = "person"
    private lateinit var person: Person

    fun newInstance(person: Person): ContactFragment {
        val args = Bundle()
        val fragment = ContactFragment()
        args.putSerializable(KEY, person)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_contact, container, false)
        person = arguments?.getSerializable(KEY) as Person
        view.txt_phone.text = "${person.contact.phoneNumber}"
        var phone = view.findViewById<LinearLayout>(R.id.phone)
        phone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${person.contact.phoneNumber}"));
                startActivity(intent);
            }
        })

        var gmail = view.findViewById<LinearLayout>(R.id.gmail)
        gmail.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val uriText = "mailto:${person.contact.emailAddress}" +
                        "?subject=" + Uri.encode("Hello From MDP Maharishi") +
                        "&body=" + Uri.encode("Hello ${person.firstName}!")

                val uri = Uri.parse(uriText)

                val sendIntent = Intent(Intent.ACTION_SENDTO)
                sendIntent.data = uri
                startActivity(Intent.createChooser(sendIntent, "Send email"))
            }
        })

        var linkedIn = view.findViewById<LinearLayout>(R.id.linkedIn)
        linkedIn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    var intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/${person.contact.linkedIn}"));
                    startActivity(intent);
                } catch (e: Exception) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.linkedin.com/in/${person.contact.linkedIn}")
                        )
                    );
                }
            }
        })

        var twitter = view.findViewById<LinearLayout>(R.id.twitter)
        twitter.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    var intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=${person.contact.twitter}"));
                    startActivity(intent);
                } catch (e: Exception) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/#!/${person.contact.twitter}")
                        )
                    );
                }
            }
        })

        var instagram = view.findViewById<LinearLayout>(R.id.git)
        instagram.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    var intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${person.contact.github}"));
                    startActivity(intent);
                } catch (e: Exception) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/${person.contact.github}")
                        )
                    );
                }
            }
        })
        return view
    }
}
