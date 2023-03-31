package movieRiew

interface ReviewsInterface {


    fun get(id: String): Review

    fun getByUser(userId: String): Map<String, Pair<Review, Movie>>

    fun getByMovie(movieId: String): Map<String, Pair<Review, Movie>>

    fun getByUserAndMovie(userId: String, movieId: String): Review

    fun getAll(): Map<String, Pair<Review, Movie>>

    fun add(userId: String, movieId: String, rank: Int, comment: String, date: Long ? = null): Review

    fun remove(id: String)
}