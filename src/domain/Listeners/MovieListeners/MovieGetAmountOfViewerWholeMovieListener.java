package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieGetAmountOfViewerWholeMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;

    public MovieGetAmountOfViewerWholeMovieListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (ui.getCbAmountOfViewerWholeMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbAmountOfViewerWholeMovie().getSelectedItem().toString();
                String amountOfViewer = movieManager.viewersByMovie(strSelectedMovie);
                ui.getTxtAmountOfViewersWholeMovie().setText(amountOfViewer);
            }
        } catch (Exception ex) {
            ui.getTxtAmountOfViewersWholeMovie().setText("Er is iets fout gegaan met het ophalen van de gegevens.");
        }
    }
}