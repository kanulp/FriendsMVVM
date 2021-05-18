package com.kanulp.friendsmvvm.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.kanulp.friendsmvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var detailViewModel: DetailViewModel? =null
    var id : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        var first_name = view.findViewById<TextView>(R.id.txt_first_name)
        var last_name = view.findViewById<TextView>(R.id.txt_last_name)
        var email = view.findViewById<TextView>(R.id.txt_email)

        var args = DetailFragmentArgs.fromBundle(requireArguments())
        id  = Integer.parseInt(args.id)
        Log.d("DETAILLOG","found id with $id")
        return view
    }

}