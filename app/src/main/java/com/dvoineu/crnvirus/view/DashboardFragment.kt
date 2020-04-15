package com.dvoineu.crnvirus.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.dvoineu.crnvirus.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvAbout.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_aboutCovid19)
        }

        cv10Advices.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_advices)
        }

        cvSymptoms.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_symptoms)
        }

        cvFaq.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_faq)
        }

        cvSelfCheck.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_checkSelf)
        }

        cvUsefulSources.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_dashboard_to_usefulResouces)
        }




    }
}
