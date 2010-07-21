package View;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TesteGrafico {

	public static void main(String[] args) {
		
		/*DefaultXYDataset dataset=new DefaultXYDataset();
		XYSeries s=new XYSeries("x");
		s.add(1,1);
		s.add(1,2);
		s.add(1,3);
		dataset.
		
		ValueAxis axis1 = new NumberAxis("Eixo X");  
		ValueAxis axis2 = new NumberAxis("Eixo Y");
		StandardXYItemRenderer renderer = new StandardXYItemRenderer();  
		Plot plot=new XYPlot(dataset,axis1,axis2,renderer);
		JFreeChart j=new JFreeChart(plot);*/
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		JFreeChart j=ChartFactory.createBarChart("teste", "X", "Y", defaultCategoryDataset,PlotOrientation.VERTICAL, true, false,false);
		
		ChartPanel panel=new ChartPanel(j);
		JFrame f=new JFrame();
		f.setSize(100, 100);
		f.add(panel);
		f.setVisible(true);

	}
}
