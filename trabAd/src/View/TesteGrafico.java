package View;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TesteGrafico {

	public static void main(String[] args) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100,"Vermelho","TAM");
		dataset.addValue(50,"Azul","TAM");
		dataset.addValue(25,"Verde","TAM");
		dataset.addValue(30,"Vermelho","TAP");
		dataset.addValue(90,"Azul","TAP");
		dataset.addValue(15,"Verde","TAP");
		dataset.addValue(40,"Vermelho","NCM");
		dataset.addValue(65,"Azul","NCM");
		dataset.addValue(90,"Verde","NCM");
		JFreeChart j=ChartFactory.createBarChart("teste", "Rodada", "Valor", dataset,PlotOrientation.VERTICAL, true, false,false);
		
		ChartPanel panel=new ChartPanel(j);
		JFrame f=new JFrame();
		f.setSize(400, 400);
		f.add(panel);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
