package movieRiew

import movieRiew.utilities.Storage
import movieRiew.utilities.Tools
import java.util.*

class Likes: LikesInterface {

    private val movies: Movies = Movies()


    override fun getAll(): Map<String, Pair<Like, Movie>> {

        val likes: MutableMap<String, Pair<Like, Movie>> = mutableMapOf()
        for(like in Storage.likes)
        {
            likes[like.id] = Pair(like, movies.get(like.movie))
        }

        return likes
    }

    override fun get(id: String): Like {

        return getAll().values.firstOrNull{ it.first.id == id }?.first ?: emptyLike()
    }

    private fun emptyLike(): Like
    {
        return Like("", "", "", 0L)
    }


    fun userLike(userId: String, movieId: String): Like
    {
        return getAll().values.find { it.first.user == userId && it.first.movie == movieId }?.first ?: emptyLike()
    }

    override fun getByUser(userId: String): Map<String, Pair<Like, Movie>>
    {
        return getAll().filterValues { it.first.user == userId }
    }


    override fun add(userId: String, movieId: String, date: Long ?): Like
    {
        val newLike = Like(id = UUID.randomUUID().toString(), date=date ?: Tools.currentDate(), user=userId, movie=movieId)
        val user = Users().get(userId)
        val movie = Movies().get(movieId)

        if (user.id.isEmpty())
        {
            throw Exception("El usuario no existe")
        }

        if (movie.id.isEmpty())
        {
            throw Exception("La película no existe")
        }

        if (Storage.likes.none { it.user == newLike.user && it.movie == newLike.movie }) {
            Storage.likes.add(newLike)
        }

        return newLike

    }

    override fun remove(id: String)
    {
        val like = get(id)

        if (like.id.isNotEmpty())
        {
            Storage.likes.removeAll{it.id == like.id}
        }

    }

}