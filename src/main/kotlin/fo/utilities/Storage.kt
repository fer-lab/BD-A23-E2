package fo.utilities

import fo.Like
import fo.Movie
import fo.Review
import fo.User
import java.io.File


class Storage {

    companion object {

        val movies: MutableList<Movie> = mutableListOf()
        val users: MutableList<User> = mutableListOf()
        val reviews: MutableList<Review> = mutableListOf()
        val likes: MutableList<Like> = mutableListOf()

        init {

            val storage = Storage()
            storage.loadMovies()
            storage.loadUsers()

            val randomizer = Randomizer()
            randomizer.generateRandomReviews()
            randomizer.generateRandomLikes()
        }


    }

    private fun loadUsers() {

        val file = File(javaClass.getResource("db.users.txt").toURI())
        val csvString = file.readText()

        csvString.lines().drop(1).forEach { line ->

            val fields = line.split("|")

            if (fields[0] != "") {
                val id = fields[0].toInt()
                val nick = fields[1]
                val name = fields[2]
                val type = fields[3]
                val passwd = fields[4]
                users.add(User(id, nick, name, type, passwd))
            }

        }

    }

    private fun loadMovies() {

        val file = File(javaClass.getResource("db.movies.txt").toURI())
        val csvString = file.readText()

        csvString.lines().drop(1).forEach { line ->
            val fields = line.split("|")

            if (fields[0] != "") {
                val id = fields[0].toInt()
                val name = fields[1]
                val genre = fields[2]
                val year = fields[3].toInt()
                val synopsis = fields[4]
                val director = fields[5]

                movies.add(Movie(id, name, genre, year, synopsis, director))
            }

        }

    }


}