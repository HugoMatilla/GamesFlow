package com.hugomatilla.gamesflow.show_games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugomatilla.gamesflow.preferences.UserSettings
import kotlinx.android.synthetic.main.games_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import splitties.toast.toast

class GamesListFragment : Fragment() {

    private val settings: UserSettings by inject()

    companion object {
        const val TAG = "GAMES_LIST"
    }

    private val model: GamesListViewModel by viewModel()

    private var adapter: GamesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(gamesListModule)
        initRecyclerView()
        initSwipeRefresh()
        initModel()
        setupToolbar()
    }

    override fun onStop() {
        super.onStop()
        unloadKoinModules(gamesListModule)

    }

    private fun initModel() {
        model.data.observe(viewLifecycleOwner, Observer<List<GamePresentation>> {
            adapter?.setItems(it)
            swipeRefresh.isRefreshing = false
        })
        model.fetchGames()
    }

    private fun initRecyclerView() {
        adapter = GamesAdapter { performAction(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = GridLayoutManager(this.context, resources.getInteger(R.integer.list_columns))
        adapter?.setItems(getGames())
    }

    private fun performAction(game: GamePresentation) {
        toast(game.title)
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener { load() }
    }

    private fun load() {
        model.fetchGames()
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(2000L)
//            val res = DomainUseCase().getApi()
//            withContext(Main) {
//                toast(res)
//                swipeRefresh.isRefreshing = false
//            }
//        }
    }


    private fun setupToolbar() {
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_theme_mode) settings.toggleDarkMode()
            true
        }
    }

}
