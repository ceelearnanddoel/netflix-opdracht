package application;

import datastorage.MovieDAO;
import datastorage.ProfileDAO;
import domain.Movie;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieManagerImpl {
    private MovieDAO movieDAO = new MovieDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public void initializeMovieComponents(GUI gui) throws SQLException, ClassNotFoundException {
        ArrayList<Movie> moviesWithLongestDurationAndAgeIsBelow16 = this.MovieWithLongestDurationAndAgeUnder16();
        String longestMovie = "";
        for(Movie m : moviesWithLongestDurationAndAgeIsBelow16) {
            longestMovie += m;
        }
        gui.getTxtLongestDurationOfMovieBelow16().setText(longestMovie);

        ArrayList<Movie> movieArraylist = this.getMovies();
        this.addMoviesToComboBox(gui.getCbAmountOfViewsOfMovie(), movieArraylist);
        this.addMoviesToComboBox(gui.getCbUpdateMovie(), movieArraylist);
    }

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }

    public boolean update(String title, Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieUpdated = movieDAO.update(title, movie);
        return movieUpdated;
    }

    public ArrayList<Movie> getMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> allMovies = movieDAO.getAllMovies();
        return allMovies;
    }

    public void addMoviesToComboBox(JComboBox comboBox, ArrayList<Movie> arrayList) {
        // For each Movie in ArrayList, get the title and add it to the parameter comboBox
        for (Movie movie : arrayList) {
            comboBox.addItem(movie.getTitle());
        }
    }

    public ArrayList<String> watchedMovieByAccountArrayList(int id) throws SQLException, ClassNotFoundException {
        int accountId = id;
        ArrayList<Profile> profileList = profileDAO.getMatchingProfiles(accountId);
        ArrayList<String> movieList = new ArrayList<String>();
        for(Profile p : profileList) {
            ArrayList<String> watchedMoviesByProfileList = movieDAO.getWatchedMoviesByAccount(p.getProfileID());
            for(String movie : watchedMoviesByProfileList) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public ArrayList<Movie> MovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movieWithLongestDurationAndUnder16 = movieDAO.getMovieWithLongestDurationAndAgeUnder16();
        return movieWithLongestDurationAndUnder16;
    }

    public String amountOfViewers(Movie movie) throws SQLException, ClassNotFoundException {
        String amountOfViewers = movieDAO.getAmountOfViewers(movie);
        return amountOfViewers;
    }

    public Movie getMovieByTitle(String title) throws SQLException, ClassNotFoundException {
        Movie movie = movieDAO.getMovieId(title);
        return movie;
    }
}
