package com.hugomatilla.gamesflow.show_games

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hugomatilla.gamesflow.show_games.data.Repository
import org.jetbrains.anko.AnkoLogger


class GamesListViewModel : ViewModel(), AnkoLogger {

    val data: LiveData<List<GamePresentation>>
    val error: LiveData<String?>
    val loading: LiveData<Boolean>
    private val repo = Repository()

    init {
        val repoResult = repo.subscribeToGamesUpdates()
        error = fromPublisher(repoResult.error)
        loading = fromPublisher(repoResult.loading)
        data = Transformations.map(fromPublisher(repoResult.data)) { it.toPresentation().toList() }
    }

    fun fetchGames() {
        repo.fetchAndSaveGames()
    }

}

