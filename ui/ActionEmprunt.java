package ui;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;

import fc.CD;
import fc.ErreurEmpruntException;
import fc.FacadeNf;
import fc.Support;

public class ActionEmprunt extends AbstractAction {
    Support s;
    FacadeNf f;

    public ActionEmprunt(Support s, FacadeNf facade){
        super("Emprunter");
        this.s = s;
        this.f = facade;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int code = f.emprunt(s);
			if (s instanceof CD) {
				f.setCDEnCours(s);
			} 
			

			JOptionPane.showMessageDialog(null, "Le numéro de votre emprunt est : " + code, "Retenu",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ErreurEmpruntException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Votre solde n'est pas suffisant. ", "Carte bloquée",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}