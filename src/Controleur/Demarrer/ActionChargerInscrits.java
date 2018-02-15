package Controleur.Demarrer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modele.Projet;

public class ActionChargerInscrits implements ActionListener {
    private Projet projet;

    public ActionChargerInscrits(Projet projet) {
        this.projet = projet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // demander le fichier à ouvrir
        JFileChooser chooser = new JFileChooser();
        String[] chemin = this.projet.getNomFichierLicencie().split("\\\\");
        
        String path  = "";
        if (chemin.length >= 4)
        	path = chemin[0] + File.separator + chemin[1] + File.separator 
        		+ chemin[2] + File.separator + chemin[3] + File.separator;
        else
        	path = "C" + File.separator + "Users" + File.separator;
        
        chooser.setCurrentDirectory(new File(path));
        chooser.setFileFilter(new FileNameExtensionFilter("fichier excel", "xls"));
        
        // si validation de l'ouverture
        if (chooser.showDialog(chooser, "Ouvrir") == 0) {
            this.projet.chargerCrit(chooser.getSelectedFile().toString());
        }
    }
}

