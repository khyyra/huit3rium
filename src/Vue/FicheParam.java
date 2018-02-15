package Vue;

import Controleur.Parametres.ActionAnnulerParam;
import Controleur.Parametres.ActionValiderParam;
import Modele.Projet;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aurélie on 01/11/2017.
 */
public class FicheParam extends JInternalFrame {

	private JTextField nbBalises;
	private JTextField nbMemos;
	private Projet projet;

	public FicheParam(Projet projet) {
		// Créer la fenêtre
		super("Modifer les paramètres", true, false, false, true);

		this.projet = projet;

		// Modifier la mise en page de la fenetre
		this.getContentPane().setLayout(new BorderLayout());

		// Créer des panels pour les boutons et les paramètres
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());

		// Modifier la couleur de fond de la fenêtre
		panel1.setBackground(Color.DARK_GRAY);
		panel2.setBackground(Color.DARK_GRAY);

		// Ajouter un champs pour le nombre de mémos 
		JLabel memo = new JLabel("Nombre de mémos", JLabel.CENTER);
		nbMemos = new JTextField(3);
		nbMemos.setBackground(Color.LIGHT_GRAY);
		memo.setLabelFor(nbMemos);
		panel1.add(memo);
		panel1.add(nbMemos);

		// ajouter un champs pour le nombre de balises
		JLabel balise = new JLabel("Nombre de balises", JLabel.CENTER);
		nbBalises = new JTextField(3);
		nbBalises.setBackground(Color.LIGHT_GRAY);
		balise.setLabelFor(nbBalises);
		panel1.add(balise);
		panel1.add(nbBalises);

		// ajouter les boutons
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionValiderParam(this));
		valider.setBackground(Color.LIGHT_GRAY);
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionAnnulerParam(this));
		annuler.setBackground(Color.LIGHT_GRAY);
		panel2.add(valider);
		panel2.add(annuler);

		// ajouter les panels
		this.getContentPane().add(panel1, BorderLayout.CENTER);
		this.getContentPane().add(panel2, BorderLayout.SOUTH);

		// Afficher la fenêtre
		this.pack();
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	}

	public JTextField getNbBalises() {
		return nbBalises;
	}

	public JTextField getNbMemos() {
		return nbMemos;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public void setNbBalises(String nbBalises) {
		this.nbBalises.setText(nbBalises);
	}

	public void setNbMemos(String nbMemos) {
		this.nbMemos.setText(nbMemos);
	}

	/** 
	 * Annuler les modifications sur les paramètres.
	 */
	public void annulerModifier() {
		this.setNbBalises("" + projet.getNbBalise());
		this.setNbMemos("" + projet.getNbMemo());
		this.hide();
	}

	/**
	 * Valider les modifications sur la fiche paramètres.
	 */
	public void validerModifier() {	
		// vrai s'il y a une erreur dans le formulaire
		Boolean stop = false;

		// On vérifie que le formulaire est bien rempli
		// Si une case est vide
		if (this.getNbBalises().getText().equals("")) {
			this.getNbBalises().setBackground(Color.RED);
			stop = true;
		}
		if (this.getNbMemos().getText().equals("")) {
			this.getNbMemos().setBackground(Color.RED);
			stop = true;
		}

		// on vérifie que c'est un entier
		try {
			Integer.parseInt(this.getNbBalises().getText());
			this.getNbBalises().setBackground(Color.LIGHT_GRAY);
		} catch(NumberFormatException e) {
			this.getNbBalises().setBackground(Color.RED);
			stop = true;
		}

		try {
			Integer.parseInt(this.getNbMemos().getText());
			this.getNbMemos().setBackground(Color.LIGHT_GRAY);
		} catch(NumberFormatException e) {
			this.getNbMemos().setBackground(Color.RED);
			stop = true;
		}

		// Si pas de problème, en enregistre
		if (!stop) {
			this.projet.setNbBalise(Integer.parseInt(this.getNbBalises().getText()));
			this.projet.setNbMemo(Integer.parseInt(this.getNbMemos().getText()));

			// Signaler une modification
			this.projet.setCritEnreg(false);

			// Signaler que les fiches ne sont plus conformes aux paramètres
			this.projet.setConforme();
			this.hide();
		}
	}
}