/*

 */

package com.example.swamppool.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.swamppool.R

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        playViewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_play, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        playViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
