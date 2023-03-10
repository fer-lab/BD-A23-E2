package fo

import fo.utilities.cli.Action
import fo.utilities.cli.Banner
import fo.utilities.cli.Options

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
        val response = Action.display("Hola ${user.realName}! Bienvenido a MovieRew",
            Options("¿Qué quieres hacer?")
                .add("Vew mis películas favoritas")
                .add("Vew mis reseñas")
                .add("Vew una película")
                .addDivisor()
                .add("Cerrar Sesión", "X")
            , "Aquí podrás generar reviews de películas favoritas y no tan favoritas.")


        when (response.get()) {
            "1" -> goto(AppMenu.MY_MOVIES)
            "2" -> goto(AppMenu.MY_REVIEWS)
            "3" -> goto(AppMenu.MOVIES_ALL)
            "x" -> goto(AppMenu.LOGOUT)
        }
    }

    fun myMovies()
    {
        val actions = Options("Elige una película")
        val myMoviesList = moviesChangeIndex(likes.userLikes(user.id))

        for (movie in myMoviesList)
        {
            actions.add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }

        actions.addDivisor()
        actions.add("Volver", "V")
        actions.add("Menú principal", "M")
        actions.add("Cerrar Sesión", "X")

        val response = Action.display("Mis Películas Favoritas", actions)

        if (response.get().toIntOrNull() != null && myMoviesList.containsKey(response.get().toInt()))
        {
            displayMovie(myMoviesList[response.get().toInt()]!!, AppMenu.MY_MOVIES)
        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }

    }

    fun myReviews()
    {
        val actions = Options("Elige una película")
        val myMoviesList = moviesChangeIndex(likes.userLikes(user.id))

        for (movie in myMoviesList)
        {
            actions.add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }

        actions.addDivisor()
        actions.add("Volver", "V")
        actions.add("Menú principal", "M")
        actions.add("Cerrar Sesión", "X")

        val response = Action.display("Mis Reseñas", actions)

        if (response.get().toIntOrNull() != null && myMoviesList.containsKey(response.get().toInt()))
        {
            displayMovie(myMoviesList[response.get().toInt()]!!, AppMenu.MY_REVIEWS)
        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }
    }



    private fun reviewMovie(movie: Movie, backMenu: AppMenu) {

        var rank = 0
        while (true) {
            print("¿Qué tanto te gustó? (1-5): ")
            var currentRank = readln().toIntOrNull()

            if (currentRank == null)
            {
                println("Dees ingresar un número del 1 al 5 \n ")
                continue
            }

            if (currentRank in 1..5)
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

        reviews.add(movie.id, user.id, rank, comment)
        Banner.display("!Reseña Agregada!")
        gotoAction(fun()
        {
            displayMovie(movie, backMenu)
        }, 1)

    }

    private fun removeReview(movie: Movie, backMenu: AppMenu)
    {
        val review = reviews.userReview(user.id, movie.id)

        if (review.isValid())
        {
            review.remove()
            Banner.display("!Reseña Eliminada!")

        }
        else
        {
            Banner.display("La reseña no existe...")

        }

        gotoAction(fun()
        {
            displayMovie(movie, backMenu)
        }, 1)
    }

    private fun likeMovie(movie: Movie, backMenu: AppMenu) {

        likes.add(movie.id, user.id)
        Banner.display("!Película Agregada!")
        gotoAction(fun()
        {
            displayMovie(movie, backMenu)
        }, 1)


    }

    private fun removeLike(movie: Movie, backMenu: AppMenu)
    {
        val like = likes.userLike(user.id, movie.id)

        if (like.isValid())
        {
            like.remove()
            Banner.display("!Película Eliminada!")

        }
        else
        {
            Banner.display("La película no estaba en tus favoritos...")

        }

        gotoAction(fun()
        {
            displayMovie(movie, backMenu)
        }, 1)
    }

    private fun homeMovies()
    {

        val response = Action.display("Catálogo de Películas",

            Options("Elije una opción")
                .add("Películas por Género")
                .add("Películas por Año")
                .addDivisor()
                .add("Menú principal", "M")
                .add("Cerrar Sesión", "X")

            , "Aquí podrás generar reviews de películas favoritas y no tan favoritas.")


        when (response.get()) {
            "1" -> goto(AppMenu.MOVIES_BY_GENRE)
            "2" -> goto(AppMenu.MOVIES_BY_YEAR)
            "m" -> goto(AppMenu.HOME)
            "x" -> goto(AppMenu.LOGOUT)
        }
    }

    private fun moviesByGenre() {

        val actions = Options("Elije un género")
        val genresMap = movies.getGenres()

        for (genre in genresMap)
        {
            actions.add(genre.value, genre.key.toString())
        }

        actions
            .addDivisor()
            .add("Volver", "V")
            .add("Menú principal", "M")
            .add("Cerrar Sesión", "X")

        val response = Action.display("Catálogo de Películas por Género", actions)

        if (response.get().toIntOrNull() != null && genresMap.containsKey(response.get().toInt()))
        {
            displayMoviesByGenre(genresMap[response.get().toInt()].toString())
        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }

        goto(AppMenu.HOME)
    }

    private fun displayMoviesByGenre(genre: String) {

        val movieMap = moviesChangeIndex(movies.byGenre(genre))
        val actions = Options("Elije una película")

        for (movie in movieMap)
        {
            actions.add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }

        actions
            .addDivisor()
            .add("Volver", "V")
            .add("Menú principal", "M")
            .add("Cerrar Sesión", "X")

        val response = Action.display("Catálogo de Películas - Género: $genre",
            actions)

        if (response.get().toIntOrNull() != null && movieMap.containsKey(response.get().toInt()))
        {
            displayMovie(movieMap[response.get().toInt()]!!, AppMenu.MOVIES_BY_GENRE)

        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }

        goto(AppMenu.HOME)


    }

    private fun moviesByYear() {

        val actions = Options("Elije un año")
        val yearMap = movies.getYears()

        for (year in yearMap)
        {
            actions.add(year.value.toString(), year.key.toString())
        }

        actions
            .addDivisor()
            .add("Volver", "V")
            .add("Menú principal", "M")
            .add("Cerrar Sesión", "X")


        val response = Action.display("Catálogo de Películas por Año", actions)

        if (response.get().toIntOrNull() != null && yearMap.containsKey(response.get().toInt()))
        {
            displayMoviesByYear(yearMap[response.get().toInt()]!!.toInt())
        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }

        goto(AppMenu.HOME)

    }

    private fun displayMoviesByYear(year: Int) {

        val movieMap = moviesChangeIndex(movies.byYear(year))
        val actions = Options("Elije una película")

        for (movie in movieMap)
        {
            actions.add("${movie.value.name} (${movie.value.year}, ${movie.value.director})", movie.key.toString())
        }

        actions
            .addDivisor()
            .add("Volver", "V")
            .add("Menú Principal", "M")
            .add("Cerrar Sesión", "X")

        val response = Action.display("Catálogo de Películas - Año: $year",
            actions)

        if (response.get().toIntOrNull() != null && movieMap.containsKey(response.get().toInt()))
        {
            displayMovie(movieMap[response.get().toInt()]!!, AppMenu.MOVIES_BY_YEAR)

        }
        else
        {
            when (response.get()) {
                "v" -> goto(AppMenu.MOVIES_ALL)
                "m" -> goto(AppMenu.HOME)
                "x" -> goto(AppMenu.LOGOUT)
            }
        }

        goto(AppMenu.HOME)

    }

    private fun moviesChangeIndex(movieList: Map<Int, Movie>): Map<Int, Movie>
    {
        val moviesList = movieList.values.toList()

        val moviesIndexed = moviesList.mapIndexed { index, movie ->
            (index + 1) to movie
        }

        return moviesIndexed.toMap()
    }

    private fun likesChangeIndex(movieList: Map<String, Movie>): Map<Int, Movie>
    {
        val moviesList = movieList.values.toList()

        val moviesIndexed = moviesList.mapIndexed { index, movie ->
            (index + 1) to movie
        }

        return moviesIndexed.toMap()
    }

    private fun goto(sections: AppMenu, delay: Int = 0, delayMessage: String = "")
    {

        when (sections) {
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

    private fun displayMovie(movie: Movie, backMenu: AppMenu)
    {
        val actions = Options("¿Qué quieres hacer?")
        val reviewExist = reviews.userReview(user.id, movie.id).isValid()
        val likeExist = likes.userLike(user.id, movie.id).isValid()

        if (reviewExist)
        {
            actions.add("Remover Reseña")
        }
        else
        {
            actions.add("Realizar Reseña")
        }

        if (likeExist)
        {
            actions.add("Remover de Favoritos")
        }
        else
        {
            actions.add("Agregar a Favoritos")
        }

        actions.addDivisor()
        actions.add("Volver", "V")
        actions.add("Menú principal", "M")
        actions.add("Cerrar Sesión", "X")

        val response = Action.display("${movie.name} (${movie.year}, ${movie.director})", actions, "${movie.synopsis}", "Género: ${movie.genre}\nRating: ${movies.parseRating(movie)}\n${movies.parseReviews(movie)}")

        when (response.get()) {
            "1" -> if (reviewExist) removeReview(movie, backMenu) else reviewMovie(movie, backMenu)
            "2" -> if (likeExist) removeLike(movie, backMenu) else likeMovie(movie, backMenu)
            "v" -> goto(backMenu)
            "m" -> goto(AppMenu.HOME)
            "x" -> goto(AppMenu.LOGOUT)
        }

    }

}
