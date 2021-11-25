package ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
			f.emprunt(s);
		} catch (ErreurEmpruntException e1) {
			e1.printStackTrace();
		}
	}
}