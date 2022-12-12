package com.example.curriculumvitaeapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.curriculumvitaeapp.common.Person

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
                val uriText = "mailto:${person.contact.emailAddressl}" +
                        "?subject=" + Uri.encode("Hello From MDP Maharishi") +
                        "&body=" + Uri.encode("Hello ${person.firstName}!")

                val uri = Uri.parse(uriText)

                val sendIntent = Intent(Intent.ACTION_SENDTO)
                sendIntent.data = uri
                startActivity(Intent.createChooser(sendIntent, "Send email"))
            }
        })

        var facebook = view.findViewById<LinearLayout>(R.id.facebook)
        facebook.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    var intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/${person.contact.facebook}"));
                    startActivity(intent);
                } catch (e: Exception) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.facebook.com/${person.contact.facebook}")
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
        var instagram = view.findViewById<LinearLayout>(R.id.instagram)
        instagram.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                try {
                    var intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/${person.contact.instagram}"));
                    startActivity(intent);
                } catch (e: Exception) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/${person.contact.instagram}")
                        )
                    );
                }
            }
        })
        return view
    }
}
