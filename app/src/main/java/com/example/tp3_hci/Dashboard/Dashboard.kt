package com.example.tp3_hci.Dashboard

import android.view.View
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tp3_hci.R
import com.example.tp3_hci.databinding.FragmentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {

    private lateinit var navView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        navView =
            activity?.findViewById(R.id.nav_view)!! // Get reference to the BottomNavigationView
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navView.menu.findItem(R.id.navigation_dashboard)?.isChecked = true // Highlight the "Dashboard" menu item
    }

    override fun onPause() {
        super.onPause()
        navView.menu.findItem(R.id.navigation_dashboard)?.isChecked = false // Remove the highlight when leaving the fragment
    }
}
