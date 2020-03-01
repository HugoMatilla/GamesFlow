package com.hugomatilla.gamesflow.home

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commit
import com.facebook.stetho.Stetho
import com.hugomatilla.gamesflow.livedata.observeNonNull
import com.hugomatilla.gamesflow.show_games.GamesListFragment

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
        viewModel.isDarkMode.observeNonNull(this) { setDarkMode(it) }
    }

    private fun setDarkMode(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isDark) window.navigationBarColor = Color.BLACK
    }

//    private fun setupBottomBar() {
//        bottomNavigation.visibility = View.GONE
//        bottomNavigation.setOnNavigationItemSelectedListener { toast(it.title).let { true } }
//    }
}

