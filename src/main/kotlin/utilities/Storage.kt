package utilities

import movieRew.*
import java.io.File


class Storage {

    companion object {

        val movies: MutableList<Movie> = mutableListOf()
        val users: MutableList<User> = mutableListOf()
        val likes: MutableList<Like> = mutableListOf()
        val reviews: MutableList<Review> = mutableListOf()

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

                users.add(User(
                    Tools.uuid(),
                    fields[0],
                    fields[1],
                    UserType.values().find { it.code == fields[2] } ?: UserType.USER,
                    fields[3]))
            }

        }

    }

    private fun loadMovies() {

        val file = File(javaClass.getResource("db.movies.txt").toURI())
        val csvString = file.readText()


        csvString.lines().drop(1).forEach { line ->
            val fields = line.split("|")

            if (fields[1] != "") {


                movies.add(
                    Movie(
                        Tools.uuid(),
                    fields[1],
                    fields[2],
                    fields[3].toInt(),
                    fields[4],
                    fields[5])
                )
            }


        }

    }


}