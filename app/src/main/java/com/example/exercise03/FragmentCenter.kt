package com.example.exercise03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCenter : Fragment(), RadioGroup.OnCheckedChangeListener {
    lateinit var frag1: Fragment1
    lateinit var frag2: Fragment2
    lateinit var myTrans: FragmentTransaction

    private val TAG_F1 = "Fragment1"
    private val TAG_F2 = "Fragment2"


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if (savedInstanceState == null) {
            frag1 = Fragment1.newInstance(ARG_PARAM1, ARG_PARAM2)
            frag2 = Fragment2.newInstance(ARG_PARAM1, ARG_PARAM2)
            myTrans = childFragmentManager.beginTransaction()
            myTrans!!.add(R.id.dfcontainer, frag1, this.TAG_F1)
            myTrans!!.detach(frag1!!)
            myTrans!!.add(R.id.dfcontainer, frag2, this.TAG_F2)
            myTrans!!.detach(frag2!!)
            myTrans!!.commit()
        }
        //frag1 = Fragment1.newInstance(ARG_PARAM1, ARG_PARAM2)
        //frag2 = Fragment2.newInstance(ARG_PARAM1, ARG_PARAM2)
        //myTrans = childFragmentManager.beginTransaction()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_center, container, false)
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCenter().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val myTrans = childFragmentManager.beginTransaction()
        when(checkedId) {
            R.id.rb_1 -> {
                //myTrans.replace(R.id.dfcontainer, frag1)
                myTrans.detach(frag2)
                myTrans.attach(frag1)
            }
            R.id.rb_2 -> {
                //myTrans.replace(R.id.dfcontainer, frag2)
                myTrans.detach(frag1)
                myTrans.attach(frag2)
            }
        }
        myTrans.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().findViewById(R.id.radio_group) as RadioGroup)
            .setOnCheckedChangeListener(this)

        if (savedInstanceState != null) {
            frag1 = childFragmentManager.findFragmentByTag(this.TAG_F1) as Fragment1
            frag2 = childFragmentManager.findFragmentByTag(this.TAG_F2) as Fragment2
        }

        childFragmentManager.setFragmentResultListener("msgfromchild", viewLifecycleOwner) {
            key, bundle ->
            val result = bundle.getString("msg1")
            (requireActivity().findViewById(R.id.tv_results) as TextView).text = result
        }
    }
}