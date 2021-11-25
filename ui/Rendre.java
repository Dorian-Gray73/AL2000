package ui;

import javax.swing.*;

import fc.CD;
import fc.ErreurRenduException;
import fc.FacadeNf;
import fc.Film;
import fc.Dao.FilmDaoImp;
import fc.Dao.LocationDaoImp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fc.ErreurRenduException;

public class Rendre extends JPanel{
	JPanel panelAff;
	FacadeNf out;
    public Rendre(JPanel panel,FacadeNf out) {
        super();
		panelAff = panel;
		this.out = out;
        JPanel dmgPanel = new JPanel(new GridLayout(3,1));
			JPanel numPanel = new JPanel(new GridLayout(3,1));
			JTextField locField = new JTextField();
			JLabel dmgLabel = new JLabel();
			JLabel numLabel = new JLabel();
			JButton valButton = new JButton("Validation");
			JButton retourButton = new JButton("retour");
			JCheckBox dmgdBox = new JCheckBox();    
			
			

			retourButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					panelAff.removeAll();
					panelAff.add(new Principale(panelAff,out));
					panelAff.revalidate();
					panelAff.repaint();
				}
			});


			dmgLabel.setText("Cocher si le cd a ete endommage");
			numLabel.setText("Entrer votre numero de location");

			dmgPanel.add(dmgLabel);
			dmgPanel.add(dmgdBox);
			dmgPanel.add(retourButton);

			numPanel.add(numLabel);
			numPanel.add(locField);
			numPanel.add(valButton);

			panelAff.removeAll();
			panelAff.add(numPanel,BorderLayout.WEST);
			panelAff.add(dmgPanel,BorderLayout.EAST);

			panelAff.revalidate();
			panelAff.repaint();

			valButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					try {
						String numStr = locField.getText();
						int num = Integer.parseInt(numStr);
						boolean damage = dmgdBox.isSelected();

						out.rendre(num, out.getCDEnCours(), damage); 

						JOptionPane.showMessageDialog(null, " Votre rendu a ete effectué", "Rendu",
							JOptionPane.INFORMATION_MESSAGE);

							
					} 
					catch(ErreurRenduException e2){
						JOptionPane.showMessageDialog(null, "Le rendu a échoué", "Il n'y a pas de location a ce numero",
							JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Le rendu a échoué", "Il y a un soucis au niveau de vos insertion",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					}
				}
			});


		}
    }
