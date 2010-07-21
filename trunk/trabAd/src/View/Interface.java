package View;



import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Interface extends JFrame 
{
	private JPanel jContentPane = null;

	public JRadioButton Cenario1 = null;
	public JRadioButton Cenario2 = null;
	public JRadioButton Cenario3 = null;
	public JRadioButton Cenario4 = null;
	
	
	public JLabel labelCenario = null;
	
	private JButton btnSimular;
	public Interface() 
	{
	  super();
	  initialize();
	}
	  
	  private void initialize() {
			this.setSize(400, 200);
			this.setFont(new Font("Serif", Font.PLAIN, 12));
			this.setTitle("Simulação de Redes CSMA-CD");
			this.setContentPane(getJContentPane());
	  }
	  
	  private Container getJContentPane() {
	  
		  GridBagConstraints gridRadioCenario = new GridBagConstraints();
		  gridRadioCenario.fill = GridBagConstraints.HORIZONTAL;
		  gridRadioCenario.gridy = 0;
		  gridRadioCenario.weightx = 3.0;
		  gridRadioCenario.anchor = GridBagConstraints.WEST;
		  gridRadioCenario.insets = new Insets(4, 7, 4, 4);
		  gridRadioCenario.gridx = 0;


		  GridBagConstraints gridBtnSimular = new GridBagConstraints();
		  //gridBtnSimular.fill = GridBagConstraints.VERTICAL;
		  gridBtnSimular.gridy = 0;
		  gridBtnSimular.gridx = 2;
		  gridBtnSimular.weightx = 3.0;
		  gridBtnSimular.gridwidth = 1;
		  gridBtnSimular.anchor = GridBagConstraints.WEST;
		  gridBtnSimular.insets = new Insets(4, 7, 4, 4);

		  btnSimular = new JButton("Simular");
		  
		  Cenario1 = new JRadioButton("1",true);
		  Cenario2 = new JRadioButton("2",false);
		  Cenario3 = new JRadioButton("3",false);
		  Cenario4 = new JRadioButton("4",false);
		 
		  
		  JPanel radioPanel = new JPanel();
			radioPanel.setLayout(new GridLayout(3, 1));
			radioPanel.add(Cenario1);
			radioPanel.add(Cenario2);
			radioPanel.add(Cenario3);
			radioPanel.add(Cenario4);
			
			
			radioPanel.setBorder(BorderFactory.createTitledBorder(
			           BorderFactory.createEtchedBorder(), "Cenário"));
			
			jContentPane = new JPanel();
			jContentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jContentPane.setLayout(new GridBagLayout());


			jContentPane.add(btnSimular, gridBtnSimular);
			jContentPane.add(radioPanel, gridRadioCenario);
			
			return jContentPane;
	  }
	  
	  public Integer getRadioSelected(){
			if(Cenario1.isSelected())
				return 1;
			else if(Cenario2.isSelected())
				return 2;
			else if(Cenario3.isSelected())
				return 3;
			else if(Cenario4.isSelected())
				return 4;
			
			else return 6;
		}
	  
	  public void addSimularListener(ActionListener listener){
			btnSimular.addActionListener(listener);
		}	  
	  
}