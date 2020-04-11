package com.hugomatilla.gamesflow.show_games.data


import com.hugomatilla.gamesflow.db.AppDB
import com.hugomatilla.gamesflow.db.game.Game
import com.hugomatilla.gamesflow.show_games.data.cloud.GameCloud
import com.hugomatilla.gamesflow.show_games.data.cloud.GamesCloudService
import com.hugomatilla.gamesflow.show_games.data.cloud.IGamesCloudService
import com.hugomatilla.gamesflow.show_games.data.cloud.getAllGames
import io.reactivex.processors.PublishProcessor
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import org.reactivestreams.Publisher

data class RepositoryResult<T : Any?>(
    val data: Publisher<T>,
    val error: Publisher<String?>,
    val loading: Publisher<Boolean>
)

private val _error: PublishProcessor<String?> = PublishProcessor.create()
private val _loading: PublishProcessor<Boolean> = PublishProcessor.create()

class Repository : AnkoLogger {

    private val service: IGamesCloudService = GamesCloudService().create()
    fun fetchAndSaveGames() {
        _loading.onNext(true)
        service.getAllGames(
            onSuccess = {
                saveGamesInDB(it.results)
                info("Success: ${it.results.size}")
                _error.onNext("")
                _loading.onNext(false)
            },
            onError = {
                _error.onNext(it)
                _loading.onNext(false)
                warn(it)
            }
        )
    }

    private fun saveGamesInDB(projects: List<GameCloud>) {
        // TODO assume that it never fails
        AppDB.getInstance().gameDao().insertAll(projects.map { it.toDb() })
    }

    fun subscribeToGamesUpdates(): RepositoryResult<List<Game>> {
        val db = AppDB.getInstance()
        val data = db.gameDao().getAllRx()
        return RepositoryResult(data, _error, _loading)
    }
}

private fun GameCloud.toDb() = Game(
    id = id,
    title = name,
    imageUrl = background_image,
    caption = "",
    rating = 5,
    genre = "ACTION",
    starred = false
)