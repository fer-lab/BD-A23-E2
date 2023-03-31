import utilities.GoToAction
import utilities.GoToEntity
import utilities.GoToMenu
import utilities.cli.Banner
import utilities.cli.UI
import movieRew.Movie
import movieRew.User

open class App {

    val user: User = Auth.user()
    val movies: Movies = Movies()
    val likes: Likes = Likes()
    val reviews: Reviews = Reviews()

    companion object
    {
        fun devMode(): Boolean
        {
            return false
        }
    }

    fun run()
    {
        homeUser()
    }

    private fun homeUser()
    {
        val ui = UI("Hola ${user.realName}! Bienvenido a MovieRew")
        ui.actions()
            .setDescription("¿Qué quieres hacer?")
            .add("Vew mis películas favoritas")
            .add("Vew mis reseñas")
            .add("Vew una película")

        ui.defaultActions().setDisable().add("Cerrar Sesión", "X")


        when (ui.response().get()) {
            "1" -> goto(GoToMenu(AppMenu.MY_MOVIES))
            "2" -> goto(GoToMenu(AppMenu.MY_REVIEWS))
            "3" -> goto(GoToMenu(AppMenu.MOVIES_ALL))
        }
    }

    private fun myMovies() {

        val ui = UI("Mis Películas Favoritas")
        ui.setActionDescription("Elige una película")
        ui.setBack(GoToMenu(AppMenu.HOME))

        val myMoviesList = likes.getByUser(user.id).values.mapIndexed { index, pair -> (index + 1) to pair }.toMap()

        for (data in myMoviesList) {
            ui.actions().add("${data.value.second.name} (${data.value.second.year}, ${data.value.second.director})", data.key.toString())
        }

        if (myMoviesList.containsKey(ui.response().toInt()))
        {
            displayMovie(myMoviesList[ui.response().toInt()]?.second ?: movies.emptyMovie(), GoToMenu(AppMenu.MY_MOVIES))
        }

    }

    private fun myReviews() {

        val ui = UI("Mis Reseñas")
        ui.setActionDescription("Elige una película")
        ui.setBack(GoToMenu(AppMenu.HOME))

        val myReviews = reviews.getByUser(user.id).values.mapIndexed { index, pair -> (index + 1) to pair }.toMap()

        for (data in myReviews) {
            val movie = data.value.second
            ui.actions().add("${movie.name} (${movie.year}, ${movie.director})", data.key.toString())
        }

        if (myReviews.containsKey(ui.response().toInt()))
        {
            displayMovie(myReviews[ui.response().toInt()]?.second ?: movies.emptyMovie(), GoToMenu(AppMenu.MY_REVIEWS))
        }
    }



    private fun reviewMovie(movie: Movie, back: GoToEntity) {

        var rank: Int
        while (true) {
            print("¿Qué tanto te gustó? (1-5): ")
            var currentRank = readln().toIntOrNull()


            if (currentRank != null && currentRank in 1..5)
            {
                rank = currentRank
                break
            }
        }

        print("Comentario: ")
        var comment = readln()
        if (comment.isEmpty())
        {
            comment = "Sin comentario."
        }

        reviews.add(user.id, movie.id, rank, comment)
        Banner.display("!Reseña Agregada!")
        gotoAction(fun()
        {
            displayMovie(movie, back)
        }, 1)

    }

    private fun removeReview(movie: Movie, back: GoToEntity)
    {
        val review = reviews.getByUser(user.id).entries.firstOrNull() { it.value.first.movie == movie.id } ?.value?.first

        if (review != null)
        {
            reviews.remove(review.id)
            Banner.display("!Reseña Eliminada!")

        }
        else
        {
            Banner.display("La reseña no existe...")

        }

        gotoAction(fun()
        {
            displayMovie(movie, back)
        }, 1)
    }

    private fun likeMovie(movie: Movie, back: GoToEntity) {

        likes.add(user.id, movie.id, null)
        Banner.display("!Película Agregada!")
        gotoAction(fun()
        {
            displayMovie(movie, back)
        }, 1)


    }

    private fun removeLike(movie: Movie, back: GoToEntity)
    {
        val like = likes.userLike(user.id, movie.id)

        if (like.id.isNotEmpty())
        {
            likes.remove(like.id)
            Banner.display("!Película Eliminada!")

        }
        else
        {
            Banner.display("La película no estaba en tus favoritos...")

        }

        gotoAction(fun()
        {
            displayMovie(movie, back)
        }, 1)
    }

    private fun homeMovies()
    {

        val ui = UI("Catálogo de Películas")
        ui.setActionDescription("Elije una opción")
        ui.setBack(GoToMenu(AppMenu.HOME))

        ui.actions().add("Películas por Género")
        ui.actions().add("Películas por Año")

        when (ui.response().get()) {
            "1" -> goto(GoToMenu(AppMenu.MOVIES_BY_GENRE))
            "2" -> goto(GoToMenu(AppMenu.MOVIES_BY_YEAR))
        }
    }

    private fun moviesByGenre() {

        val ui = UI("Catálogo de Películas por Género")
        ui.setBack(GoToMenu(AppMenu.MOVIES_ALL))
        ui.setActionDescription("Elije un género")

        val genresMap = movies.getGenres().mapIndexed { index, element -> (index + 1) to element }.toMap()

        for (genre in genresMap)
        {
            ui.actions().add(genre.value, genre.key.toString())
        }


        if (genresMap.containsKey(ui.response().toInt()))
        {
            displayMoviesByGenre(genresMap[ui.response().toInt()].toString())
        }

        goto(GoToMenu(AppMenu.HOME))
    }

    private fun displayMoviesByGenre(genre: String) {


        val ui = UI("Catálogo de Películas - Género: $genre")
        ui.setBack(GoToMenu(AppMenu.MOVIES_BY_GENRE))
        ui.setActionDescription("Elije una película")

        val movieMap = movies.getByGenre(genre).values.mapIndexed { index, movie -> (index + 1) to movie }.toMap()

        for (movie in movieMap)
        {
            ui.actions().add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }

        if (movieMap.containsKey(ui.response().toInt()))
        {
            displayMovie(movieMap[ui.response().toInt()]!!, GoToAction(fun()
            {
                displayMoviesByGenre(genre)
            }))

        }


    }

    private fun moviesByYear() {

        val ui = UI("Catálogo de Películas por Año")
        ui.setActionDescription("Elije un año")

        val yearMap = movies.getYears().mapIndexed { index, element -> (index + 1) to element }.toMap()

        for (year in yearMap)
        {
            ui.actions().add(year.value.toString(), year.key.toString())
        }

        if (yearMap.containsKey(ui.response().toInt()))
        {
            displayMoviesByYear(yearMap[ui.response().toInt()]!!.toInt())
        }

    }

    private fun displayMoviesByYear(year: Int) {

        val ui = UI("Catálogo de Películas - Año: $year")
        ui.setActionDescription("Elije una película")
        ui.setBack(GoToMenu(AppMenu.MOVIES_ALL))

        val movieMap = movies.getByYear(year).values.mapIndexed { index, movie -> (index + 1) to movie }.toMap()

        for (movie in movieMap)
        {
            ui.actions().add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }


        if (movieMap.containsKey(ui.response().toInt()))
        {
            displayMovie(movieMap[ui.response().toInt()]!!, GoToAction(fun()
            {
                displayMoviesByYear(year)
            }))

        }

    }

    fun goto(goto: GoToEntity, delay: Int = 0, delayMessage: String = "")
    {

        if (goto.isMenu())
        {
            when (goto.getMenu()) {
                AppMenu.HOME -> gotoAction(fun() { homeUser() }, delay, delayMessage)
                AppMenu.LOGOUT -> gotoAction(fun() { Auth.logOut() }, delay, delayMessage)
                AppMenu.MY_MOVIES -> gotoAction(fun() { myMovies() }, delay, delayMessage)
                AppMenu.MY_REVIEWS -> gotoAction(fun() { myReviews() }, delay, delayMessage)
                AppMenu.MOVIES_ALL -> gotoAction(fun() { homeMovies() }, delay, delayMessage)
                AppMenu.MOVIES_BY_GENRE -> gotoAction(fun() { moviesByGenre() }, delay, delayMessage)
                AppMenu.MOVIES_BY_YEAR -> gotoAction(fun() { moviesByYear() }, delay, delayMessage)
                else -> homeUser()
            }
        }
        else if (goto.isAction())
        {
            gotoAction(goto.getAction(), delay, delayMessage)
        }
        else
        {
            homeUser()
        }

    }

    private fun gotoAction(action: () -> Unit, delay: Int = 0, delayMessage: String = "")
    {
        if (delay > 0)
        {
            print(if (delayMessage.isEmpty()) "Porfavor espera" else delayMessage)

            val startTime = System.currentTimeMillis()
            var count = 0

            while (System.currentTimeMillis() < startTime + delay*1000) {
                count++
                print(".")
                Thread.sleep(1000)
            }

        }

        action()
    }

    private fun displayMovie(movie: Movie, backTo: GoToEntity)
    {
        val ui = UI("${movie.name} (${movie.year}, ${movie.director})")
        ui.setActionDescription("¿Qué quieres hacer?")
        ui.setBack(backTo)

        ui.addBody(movie.synopsis)
        ui.addBody("Género: ${movie.genre}\nRating: ${movies.parseRating(movie)}\n${movies.parseReviews(movie)}")

        val reviewExist = reviews.getByUser(user.id).values.any { it.first.movie == movie.id}
        val likeExist = likes.getByUser(user.id).values.any { it.first.movie == movie.id }

        ui.actions().add(if (reviewExist) "Remover Reseña" else "Realizar Reseña")
        ui.actions().add(if (likeExist) "Remover de Favoritos" else "Agregar a Favoritos")

        when (ui.response().get()) {
            "1" -> if (reviewExist) removeReview(movie, backTo) else reviewMovie(movie, backTo)
            "2" -> if (likeExist) removeLike(movie, backTo) else likeMovie(movie, backTo)
        }

    }

}
