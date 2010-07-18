package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Interface extends JFrame 
{
	  /**
	   * Cosntrutor que isntancia um JPanel e o adiciona a este JFrame.
	   */
	  public Interface() 
	  {

	    this.setTitle("Trabalho de Avaliacao e Desempenho");
	    this.setSize(400, 200);
	    
	    //Adiciona a capacidade de fechar a janela
	    addWindowListener(new WindowAdapter() 
	    {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });

	    //Instancia um novo JPanel
	    MyJPanel panel = new MyJPanel(); 
	    
	    //adicona o JPanel a este JFrame
	    this.getContentPane().add( panel ); 
	    
	    //manda mostrar o JFrame
	    this.show(); 
	  }
	  
	  public class MyJPanel  extends JPanel implements  ActionListener  
	  {
		  private JLabel label;
		  private JButton botao;
		  private JComboBox combo;

		  public MyJPanel()  
		  {
		    //Instancia um novo label e um novo botão
		    
		    String[] cenarios = { "Selecione um cenario", "Cenario 1", "Cenario 2", "Cenario 3", "Cenario 4" };

	        combo = new JComboBox(cenarios);
	        combo.setSelectedIndex(0);

	        botao = new JButton("Botão");
	        
		    //seta os limites do labbel e do botão
		    this.botao.setBounds(200,100,100,20);

		    //Adiciona o label e o botão a este Panel
		    this.add(this.combo);
		    this.add(this.botao);
		    

		    //adiciona ao um botão um "escutador", responsável por tratar seus cliques.
		    this.botao.addActionListener(this);
		    this.combo.addActionListener(this);
		  }

		  /**
		   * Método que trata quando uma ação é executada 
		   */
		
		  public void actionPerformed(ActionEvent e) 
		  {
			  if(e.getSource()==this.botao)
			  {
			      JOptionPane.showMessageDialog(null,"Botao!");
			  }
			  
			  if(e.getSource()==combo)
			  {	
				  JComboBox cb = (JComboBox)e.getSource();
				  
				  String cen = (String)cb.getSelectedItem();
				  
				  Integer numCen = null;
				  
				  if(cen.equals("Cenario 1"))
				  {
					  numCen = 1;
				  }
				  if(cen.equals("Cenario 2"))
				  {
					  numCen = 2;
				  }
				  if(cen.equals("Cenario 3"))
				  {
					  numCen = 3;
				  }
				  if(cen.equals("Cenario 4"))
				  {
					  numCen = 4;
				  }
				  
				  switch(numCen)
				  {
				  	case 1:
				  		JOptionPane.showMessageDialog(null,"Foi pro cenario 1!");
				  		break;
				  	case 2:
				  		JOptionPane.showMessageDialog(null,"Foi pro cenario 2!");
				  		break;
				  	case 3:
				  		JOptionPane.showMessageDialog(null,"Foi pro cenario 3!");
				  		break;
				  	case 4:
				  		JOptionPane.showMessageDialog(null,"Foi pro cenario 4!");
				  		break;
				  	default:
				  		JOptionPane.showMessageDialog(null,"Tem q escolher!");
				  		break;
				  }
			  }
			  
			  
		        
		        
		        
		  }
	}
}
