package com.example.exercise03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentEdit : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentEdit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btn_send_value).setOnClickListener{ _ ->
            val value = view.findViewById<EditText>(R.id.et_name_surname).text
            /*parentFragmentManager.setFragmentResult("datafromchild",
                bundleOf("msg3" to ("Name and surname = $value"))
            )*/
            val navController = NavHostFragment.findNavController(this)
            navController.navigate(R.id.action_fragmentEdit_to_fragmentLeft,
                bundleOf("msg3" to value.toString()))
        }
    }
}