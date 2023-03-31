package movieRiew.utilities

import movieRiew.Likes
import movieRiew.Movies
import movieRiew.Reviews
import movieRiew.Users
import movieRiew.Movie
import java.io.File

class Randomizer {

    val fakeComments: MutableList<FakeReview> = loadFakeComments()
    fun generateRandomReviews() {

        val reviews = Reviews()
        for (user in Users().getAll())
        {
            for(movie in randomMovies(5))
            {
                val fakeReview = randomReview()
                reviews.add(user.value.id, movie.value.id, fakeReview.rank, fakeReview.comment, Tools.randomDate())
            }
        }
    }


    fun generateRandomLikes() {

        val likes = Likes()
        for (user in Users().getAll())
        {
            for(movie in randomMovies(5))
            {
                likes.add(user.value.id, movie.value.id, Tools.randomDate())
            }
        }
    }


    fun randomMovies(take: Int): Map<String, Movie>
    {

        val allMovies = Movies().getAll().values.toList()
        val selectedMovies = mutableSetOf<Movie>()

        val requestedTake = if (take > allMovies.size) allMovies.size else take // ajustar si se solicita más de lo que existe

        while (selectedMovies.size < requestedTake) {
            val randomMovie = allMovies.random()
            if (!selectedMovies.contains(randomMovie)) {
                selectedMovies.add(randomMovie)
            }
        }

        return selectedMovies.associateBy { it.id }
    }

    private fun loadFakeComments(): MutableList<FakeReview> {

        val file = File(javaClass.getResource("db.comments.txt").toURI())
        val csvString = file.readText()
        val reviews: MutableList<FakeReview> = mutableListOf()

        csvString.lines().drop(1).forEach { line ->

            val fields = line.split("|")

            if (fields[0] != "") {
                reviews.add(FakeReview(fields[0], fields[1].toInt()))
            }

        }

        return reviews

    }

    private fun randomReview(): FakeReview
    {
        return fakeComments.random()
    }

}