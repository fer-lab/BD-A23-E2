package movieRiew

import movieRiew.utilities.Storage
import movieRiew.utilities.Tools
import java.util.*

class Likes: LikesInterface {

    private val movies: Movies = Movies()

    override fun getAll(): Map<String, Pair<Like, Movie>> = Storage.likes.associate { it.id to (it to Movies().get(it.movie)) }

    override fun get(id: String): Like {

        return getAll().values.firstOrNull{ it.first.id == id }?.first ?: emptyLike()
    }

    private fun emptyLike(): Like = Like(id= "", user = "", movie = "", date = 0L)

    fun userLike(userId: String, movieId: String): Like
    {
        return getAll().values.find { it.first.user == userId && it.first.movie == movieId }?.first ?: emptyLike()
    }

    override fun getByUser(userId: String): Map<String, Pair<Like, Movie>>
    {
        return getAll().filterValues { it.first.user == userId }
    }


    override fun add(userId: String, movieId: String, date: Long ?): Like =
        run{

            val user = Users().get(userId)
            val movie = Movies().get(movieId)

            require(user.id.isNotEmpty()) { "El usuario no existe" }
            require(movie.id.isNotEmpty()) { "La película no existe" }
            require(Storage.likes.none { it.user == userId && it.movie == movieId }) {
                "Ya existe un like del usuario ${user.userName} para la película ${movie.name}"
            }

            Like(id = UUID.randomUUID().toString(), date=date ?: Tools.currentDate(), user=userId, movie=movieId)
        }.also { Storage.likes.add(it) }

    override fun remove(id: String)
    {
        Storage.likes.removeAll { it.id == id }
    }

}