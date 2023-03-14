package t6

interface Likes {
    fun getByUser(userId: String): Map<String, Pair<Like, Movie>>

    fun get(id: String): Like

    fun getAll(): Map<String, Pair<Like, Movie>>

    fun add(userId: String, movieId: String, date: Long ?): Like

    fun remove(id: String)
}