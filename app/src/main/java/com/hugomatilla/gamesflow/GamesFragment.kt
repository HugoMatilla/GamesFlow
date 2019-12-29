package com.hugomatilla.gamesflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugomatilla.gamesflow.domain.DomainUseCase
import kotlinx.android.synthetic.main.games_list_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import splitties.toast.toast

class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefresh()
    }

    private fun initRecyclerView() {
        val adapter = GamesAdapter { performAction(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter.setItems(getGames())
    }

    private fun performAction(game: GamePresentation) {
        toast(game.title)
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener { load() }
    }

    private fun load() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000L)
            val res = DomainUseCase().getApi()
            withContext(Main) {
                toast(res)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GamesFragment().apply { arguments = Bundle().apply { } }
    }
}
