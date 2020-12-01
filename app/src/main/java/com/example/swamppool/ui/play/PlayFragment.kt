/*

 */

package com.example.swamppool.ui.play

import android.icu.util.Calendar
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

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        playViewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_play, container, false)

        var myToast: Toast = Toast.makeText(activity, "Test Toast", Toast.LENGTH_SHORT)

        var list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

        var playerCount: Int = 0;
        val txtPlayerCount: TextView = root.findViewById(R.id.text_playerCount)
        txtPlayerCount.text = getString(R.string.player_count, playerCount)

        val btnNewGame: Button = root.findViewById(R.id.btn_newGame)
        val button: Button = root.findViewById(R.id.button)
        val btnNextPlayer: Button = root.findViewById(R.id.btn_nextPlayer)

        button.setOnClickListener{
            // Save and Log when the game started (first number picked).
            val startTime: Calendar = Calendar.getInstance()
            Log.i("PLAY - Calendar", "Game started at: ${startTime.time}")

            val random: Int = list.random()
            list.remove(random)
            button.text = random.toString()
            playerCount += 1
            txtPlayerCount.text = getString(R.string.player_count, playerCount)

            // Logcat:
            var remaining: String = ""
            for (i in list) {
                remaining += "$i, "
            }
            remaining = remaining.dropLast(2) // Remove the final ", " from the string.
            Log.i("PLAY - button.OnClick - else", "Numbers remaining: $remaining")

            btnNextPlayer.isEnabled = true
            button.isEnabled = false
        }

        // New Game button.
        btnNewGame.setOnClickListener{
            // Save and Log when the game finished (New Game clicked).
            val endTime: Calendar = Calendar.getInstance()
            Log.i("PLAY - Calendar", "Game finished at: ${endTime.time}")

            // Reset list to include all balls again.
            list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
            button.text = getString(R.string.play_now)
            playerCount = 0
            txtPlayerCount.text = getString(R.string.player_count, playerCount)

            btnNextPlayer.isEnabled = false
            button.isEnabled = true

            myToast = Toast.makeText(activity, "New Game", Toast.LENGTH_SHORT)
            myToast.show()
        }

        // Next Player button.
        btnNextPlayer.setOnClickListener{
            // Disable button regardless of whether there's any balls left or not.
            btnNextPlayer.isEnabled = false

            if (list.isEmpty()) {
                button.text = getString(R.string.no_balls)
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
