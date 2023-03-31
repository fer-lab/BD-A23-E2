package movieRiew.utilities

import movieRiew.*
import java.io.File


class Storage {

    companion object {

        @kotlin.jvm.JvmField
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

        fun getDBFields(fileName: String): List<List<String>> {

            val fileURI = Storage().javaClass.getResource(fileName)?.toURI()
            val file = fileURI?.let { File(it) } ?: return emptyList()

            val csvString = file.readText()
            return csvString.lines().drop(1).map { it.split("|") }
        }


    }

    private fun loadUsers() {

        getDBFields("db.users.txt").forEach { fields ->

            if (fields[0].isNotBlank()) {

                users.add(
                    User(
                        Tools.uuid(),
                        fields[0],
                        fields[1],
                        UserType.values().find { it.code == fields[2] } ?: UserType.USER,
                        fields[3]
                    )
                )
            }

        }

    }

    private fun loadMovies() {

        getDBFields("db.movies.txt").forEach { fields ->

            if (fields[1].isNotBlank()) {

                movies.add(
                    Movie(
                        Tools.uuid(),
                        fields[1],
                        fields[2],
                        fields[3].toInt(),
                        fields[4],
                        fields[5]
                    )
                )
            }

        }

    }

}