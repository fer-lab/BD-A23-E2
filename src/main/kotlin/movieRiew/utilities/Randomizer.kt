package movieRiew.utilities

import movieRiew.*

class Randomizer {

    val fakeComments: MutableList<FakeReview> = loadFakeComments()
    fun generateRandomReviews() {

        val reviews = Reviews()
        for (user in Users().getAll()) {
            for (movie in randomMovies(5)) {
                val fakeReview = fakeComments.random()
                reviews.add(user.value.id, movie.value.id, fakeReview.rank, fakeReview.comment, Tools.randomDate())
            }
        }
    }


    fun generateRandomLikes() {

        val likes = Likes()
        for (user in Users().getAll()) {
            for (movie in randomMovies(5)) {
                likes.add(user.value.id, movie.value.id, Tools.randomDate())
            }
        }
    }


    private fun randomMovies(take: Int): Map<String, Movie> {

        val allMovies = Movies().getAll().values.toList()
        val selectedMovies = mutableSetOf<Movie>()

        val requestedTake = if (take > allMovies.size) allMovies.size else take

        while (selectedMovies.size < requestedTake) {
            val randomMovie = allMovies.random()
            if (!selectedMovies.contains(randomMovie)) {
                selectedMovies.add(randomMovie)
            }
        }

        return selectedMovies.associateBy { it.id }
    }

    private fun loadFakeComments(): MutableList<FakeReview> {

        val reviews: MutableList<FakeReview> = mutableListOf()

        Storage.getDBFields("db.comments.txt").forEach { fields ->
            if (fields[0].isNotEmpty()) {
                reviews.add(FakeReview(fields[0], fields[1].toInt()))
            }

        }

        return reviews

    }

}