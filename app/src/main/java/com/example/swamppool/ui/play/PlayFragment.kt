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
        //val textView: TextView = root.findViewById(R.id.text_home)

        var myToast = Toast.makeText(activity, "Test Toast", Toast.LENGTH_SHORT)

        var list = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)

        val btnNewGame: Button = root.findViewById(R.id.btn_newGame)
        val button: Button = root.findViewById(R.id.button)
        val btnHide: Button = root.findViewById(R.id.btn_hide)

        button.setOnClickListener{

            //else {
                // val random = (1..15).random()
                val random = list.random()
                list.remove(random)
                //myToast = Toast.makeText(activity, random.toString(), Toast.LENGTH_SHORT)
                //myToast.show()
                button.text = random.toString()
                var remaining: String = ""
                for (i in list) {
                    remaining += "$i, "
                }
                remaining = remaining.dropLast(2) // Remove the final ", " from the string.
                Log.i("PLAY - button.OnClick - else", "Numbers remaining: $remaining")

                btnHide.isEnabled = true
                button.isEnabled = false
            //}
        }

        // New Game button.
        btnNewGame.setOnClickListener{
            // Reset list to include all balls again.
            list = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
            button.text = getString(R.string.play_now)

            myToast = Toast.makeText(activity, "New Game", Toast.LENGTH_SHORT)
            myToast.show()

            btnHide.isEnabled = false
            button.isEnabled = true
        }

        // Next Player button.
        btnHide.setOnClickListener{
            // Disable button regardless of whether there's any balls left or not.
            btnHide.isEnabled = false

            if (list.isEmpty()) {
                button.text = getString(R.string.no_balls);
                Log.i("PLAY - button.OnClick - if", "No balls left")
            }
            else {
                //myToast.cancel()
                button.text = getString(R.string.play_now)

                button.isEnabled = true
            }
        }

        playViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}
