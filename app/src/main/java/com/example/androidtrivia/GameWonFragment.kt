package com.example.androidtrivia

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.btnNextMatch.setOnClickListener(
            Navigation.createNavigateOnClickListener(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        )

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.winner_menu, menu)
                    if (getShareIntent().resolveActivity(activity!!.packageManager) == null) {
                        menu.findItem(R.id.shareVictory)?.setVisible(false)
                    }
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.shareVictory -> {
                            shareSuccess()
                            true
                        }

                        else -> false
                    }
                }

            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        return Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_success_text, args.numCorrect, args.totalQuestions)
            )
        }
    }


    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

}