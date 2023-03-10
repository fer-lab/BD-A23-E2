package fo.utilities

import fo.*
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

class Randomizer {

    val fakeComments: MutableList<FakeReview> = loadFakeComments()
    fun generateRandomReviews() {

        val reviews = Reviews()
        for (user in Users().getAll())
        {
            for(movie in randomMovies(5))
            {
                val fakeReview = randomReview()
                reviews.add(movie.value.id, user.value.id, fakeReview.rank, fakeReview.comment, randomDate())
            }
        }
    }


    fun generateRandomLikes() {

        val likes = Likes()
        for (user in Users().getAll())
        {
            for(movie in randomMovies(5))
            {
                likes.add(movie.value.id, user.value.id, randomDate())
            }
        }
    }


    fun randomMovies(take: Int): Map<Int, Movie>
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

    fun randomDate(): Long {
        val startDate = LocalDate.now().minusMonths(6)
        val endDate = LocalDate.now()
        val startDateTime = LocalDateTime.of(startDate, LocalDateTime.MIN.toLocalTime())
        val endDateTime = LocalDateTime.of(endDate, LocalDateTime.MAX.toLocalTime())
        val randomDateTime = startDateTime.plusSeconds(ThreadLocalRandom.current().nextLong(startDateTime.toEpochSecond(ZoneOffset.UTC), endDateTime.toEpochSecond(ZoneOffset.UTC)))
        return randomDateTime.toEpochSecond(ZoneOffset.UTC)
    }
}