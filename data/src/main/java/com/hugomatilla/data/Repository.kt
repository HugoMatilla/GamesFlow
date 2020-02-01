package com.hugomatilla.data


import com.hugomatilla.gamesflow.data.db.Game
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