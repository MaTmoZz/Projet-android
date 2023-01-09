package com.example.myappli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var personnageList: RecyclerView  // Déclaration de l'objet "personnageList"
    private lateinit var buttonSecond: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Initialisation de l'objet "personnageList"
        personnageList = binding.personnageList

        // Configuration de l'objet "personnageList" avec un "layout manager"
        personnageList.layoutManager = LinearLayoutManager(context)

        // Configuration de l'objet "personnageList" avec une "animation"
        personnageList.itemAnimator = DefaultItemAnimator()

        _binding = FragmentSecondBinding.inflate(inflater, container, false).apply {
            this@SecondFragment.personnageList = personnageList
        }

        _binding = FragmentSecondBinding.inflate(inflater, container, false).apply {
            this@SecondFragment.personnageList = personnageList
            this@SecondFragment.buttonSecond = buttonSecond
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}