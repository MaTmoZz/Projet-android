package com.example.muchartssbu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.muchartssbu.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var vue: View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        vue = inflater.inflate(R.layout.fragment_second, container, false)
        _binding = FragmentSecondBinding.inflate(inflater)


        val perso = "Fox"
        val perso1 = "Kirby"
        val perso2 = "Falco"

        val tableau = listOf(perso, perso1, perso2)
        val recyclerView = vue.findViewById<RecyclerView>(R.id.recipes_list_view)
        recyclerView.adapter = Adapteur()
        (recyclerView.adapter as Adapteur).submitList(tableau)
        return vue
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vue.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}