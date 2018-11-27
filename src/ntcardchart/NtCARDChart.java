/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcardchart;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ljanssen
 */
public class NtCARDChart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        JFrame frame = new JFrame();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //String path = args[0];
        
        String path1 = "/home/ljanssen/Documents/stuff/ERR_k20.hist";
        String path2 = "/home/ljanssen/Documents/stuff/ERR.hist";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path2))){
            String line = null;
            line = br.readLine();
            if (line.contains("F1")){
                line = br.readLine();
                String[] cols = null;
                while ((line = br.readLine()) != null){
                    String[] tmp = path1.split("/");
                    String series = tmp[tmp.length-1];
                    cols = line.split("\t");
                    String uniqueK1 = cols[0];

                    String numbK1 = cols[1];
                    Integer x = Integer.parseInt(uniqueK1); 
                    Integer y = Integer.parseInt(numbK1);

                    dataset.addValue(y, series, x);

                }

                JFreeChart chart = ChartFactory.createLineChart("K-mer Coverage Histogram", "Kmer coverage", "Frequency", dataset, PlotOrientation.VERTICAL, true, true, false);


                ChartPanel chartPanel = new ChartPanel(chart);
                frame.add(chartPanel);
                frame.setMinimumSize(new Dimension(800,800));
                frame.setVisible(true);
            }
            else {
                String[] cols2 = null;
                while ((line = br.readLine()) != null){
                    cols2 = line.split("\t");
                    String series = cols2[0];
                    String x1 = cols2[1];
                    Integer x = Integer.parseInt(x1);
                    String y1 = cols2[2];
                    Integer y = Integer.parseInt(y1);
                    
                    dataset.addValue(y, series, x);
                    
                    /*
                    if (Kmer.equals("20")){
                        String series1 = "K"+cols2[0];
                        String y20 = cols2[1];
                        Integer Y20 = Integer.parseInt(y20);
                        String x20 = cols2[2];
                        Integer X20 = Integer.parseInt(x20);

                        dataset.addValue(X20, series1, Y20);
                    }
                    else if (Kmer.equals("30")){
                        String series2 = "K"+cols2[0];
                        String y30 = cols2[1];
                        Integer Y30 = Integer.parseInt(y30);
                        String x30 = cols2[2];
                        Integer X30 = Integer.parseInt(x30);

                        dataset.addValue(X30, series2, Y30);
                    }
                    else if (Kmer.equals("40")){
                        String series3 = "K"+cols2[0];
                        String y40 = cols2[1];
                        Integer Y40 = Integer.parseInt(y40);
                        String x40 = cols2[2];
                        Integer X40 = Integer.parseInt(x40);

                        dataset.addValue(X40, series3, Y40);
                    }
                    */
                }

                JFreeChart chart = ChartFactory.createLineChart("K-mer Coverage Histogram", "Kmer coverage", "Frequency", dataset, PlotOrientation.VERTICAL, true, true, false);

                ChartPanel chartPanel = new ChartPanel(chart);
                frame.add(chartPanel);
                frame.setMinimumSize(new Dimension (800,800));
                frame.setVisible(true);
    
            }
        }
    }

        
}
            
  
    


