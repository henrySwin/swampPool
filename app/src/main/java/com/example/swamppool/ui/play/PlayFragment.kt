/*

 */

package com.example.swamppool.ui.play

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.swamppool.R
import kotlin.random.Random

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

        val button: Button = root.findViewById(R.id.button)

        var list = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
        button.setOnClickListener{

            if (list.isEmpty()) {
                textView.text = "No balls left to choose from."
                Log.i("PLAY - button.OnClick - if", "No balls left")
            }
            else {
                // val random = (1..15).random()
                val random = list.random()
                list.remove(random)
                Toast.makeText(activity, random.toString(), Toast.LENGTH_SHORT).show()
                textView.text = random.toString()
                var remaining: String = ""
                for (i in list) {
                    remaining += "$i, "
                }
                remaining = remaining.dropLast(2)
                Log.i("PLAY - button.OnClick - else", "Numbers remaining: $remaining")
            }
        }

        playViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
