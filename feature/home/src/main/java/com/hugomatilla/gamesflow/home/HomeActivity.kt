package com.hugomatilla.gamesflow.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.facebook.stetho.Stetho
import com.hugomatilla.gamesflow.livedata.observeNonNull
import com.hugomatilla.gamesflow.show_games.GamesListFragment
import com.hugomatilla.gamesflow.ui.setDarkMode

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        Stetho.initializeWithDefaults(this)
        setupModel()
        setupFragments()
    }

    private fun setupFragments() {
        val listFragment = GamesListFragment()
        supportFragmentManager.commit {
            //            addToBackStack("...")
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            add(R.id.fragment_container, listFragment, GamesListFragment.TAG)
        }
    }

    private fun setupModel() {
        viewModel.isDarkMode.observeNonNull(this) {
            println("🚛 Dark: $it")
            window.setDarkMode(it)
        }
    }

//    private fun setupBottomBar() {
//        bottomNavigation.visibility = View.GONE
//        bottomNavigation.setOnNavigationItemSelectedListener { toast(it.title).let { true } }
//    }
}

