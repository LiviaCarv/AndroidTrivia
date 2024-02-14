package com.example.androidtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.androidtrivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.btnNextMatch.setOnClickListener(
            Navigation.createNavigateOnClickListener(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        )
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "Num correct: ${args.numCorrect} e Total: ${args.totalQuestions}",Toast.LENGTH_SHORT).show()

        return binding.root
    }

}