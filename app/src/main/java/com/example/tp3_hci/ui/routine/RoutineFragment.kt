package com.example.tp3_hci.ui.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tp3_hci.databinding.FragmentRoutinesBinding

class RoutineFragment : Fragment() {

    private var _binding: FragmentRoutinesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val routineViewModel =
            ViewModelProvider(this).get(RoutineViewModel::class.java)

        _binding = FragmentRoutinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRoutines
        routineViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}