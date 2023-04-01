package movieriew;

import java.util.List;
import java.util.Map;

public interface MoviesInterface {

    Movie get(String id);

    Map<String, Movie> getAll();

    Map<String, Movie> getByYear(int year);

    Map<String, Movie> getByGenre(String genre);

    Map<String, Movie> find(String query);

    List<Integer> getYears();

    List<String> getGenres();

    void remove(String id);
}
