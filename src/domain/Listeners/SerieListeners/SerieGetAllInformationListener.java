package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieGetAllInformationListener implements ActionListener {
    private GUI ui;
    private JComboBox selectedSerie;
    private SerieManagerImpl serieManager;

    public SerieGetAllInformationListener(GUI ui, JComboBox selectedSerie) {
        this.ui = ui;
        this.selectedSerie = selectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectedSerie.getSelectedItem() != "" && selectedSerie.getSelectedItem() != null){
            Serie serie = (Serie)selectedSerie.getSelectedItem();

            ui.getLblSerieViewTitlelb().setText(serie.getTitle());
            ui.getLblSerieGenre().setText(serie.getGenre());
            ui.getLblSerieLanguage().setText(serie.getLanguage());
            String minAge = Integer.toString(serie.getMinAge());
            ui.getLblSerieMinAge().setText(minAge);

            Serie recommendedSerie = serieManager.getSerieById(serie.getRecommendedSerie());
            ui.getLblSerieRecommended().setText(recommendedSerie.toString());

            ui.getLblSerieViewTitlelb().setVisible(true);
            ui.getLblSerieGenre().setVisible(true);
            ui.getLblSerieLanguage().setVisible(true);
            ui.getLblSerieMinAge().setVisible(true);
            ui.getLblSerieRecommended().setVisible(true);

            ui.getLblSerieTitleLabel().setVisible(true);
            ui.getLblSerieGenreLabel().setVisible(true);
            ui.getLblSerieLanguageLabel().setVisible(true);
            ui.getLblSerieMinAgeLabel().setVisible(true);
            ui.getLblSerieRecommendedLabel().setVisible(true);
        }
        else{
            serieManager.hideSerieLabels();
        }
    }
}
