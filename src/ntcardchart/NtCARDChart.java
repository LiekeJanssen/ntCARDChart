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
        JFrame frame = new JFrame();                //create JFrame
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();      //set dataset
        //String path = args[0];
        
        String path1 = "/home/ljanssen/Documents/SPAdes_sample_data/testntcard_k100.hist";  //path to the file
        String path2 = "/home/ljanssen/Stuff/ERR.hist"; //(args[0]);                        //path to file, in commented part rather use args[0] for user input
        
        try (BufferedReader br = new BufferedReader(new FileReader(path2))){            //read file from path2
            String line = null;                                                         //make variable line empty
            line = br.readLine();                                                       //read first line
            if (line.contains("F1")){                                                   
                line = br.readLine();
                String[] cols = null;                                                   //creating an array "cols" when the column F1 is found in the file and 
                while ((line = br.readLine()) != null){                                 //read the lines
                    String[] tmp = path1.split("/");                                    //using the file names to put on the image later
                    String series = tmp[tmp.length-1];
                    cols = line.split("\t");
                    String uniqueK1 = cols[0];                                          

                    String numbK1 = cols[1];
                    Integer x = Integer.parseInt(uniqueK1); 
                    Integer y = Integer.parseInt(numbK1);

                    dataset.addValue(y, series, x);                                     //column 1 is the unique kmer column and numbK1 is the column with the coverage, put those on the x- and y-axis

                }

                JFreeChart chart = ChartFactory.createLineChart("K-mer Coverage Histogram", "Kmer coverage", "Frequency", dataset, PlotOrientation.VERTICAL, true, true, false);        //create and put data in JFreeChart


                ChartPanel chartPanel = new ChartPanel(chart);
                frame.add(chartPanel);
                frame.setMinimumSize(new Dimension(800,800));
                frame.setVisible(true);
                                                                                            //set sizes and make visible
            }
            else {
                String[] cols2 = null;                                                        //when column does not start with F1 go on with the same
                while ((line = br.readLine()) != null){
                    cols2 = line.split("\t");
                    String series = cols2[0];
                    String x1 = cols2[1];
                    Integer x = Integer.parseInt(x1);
                    String y1 = cols2[2];
                    Integer y = Integer.parseInt(y1);
                    
                    dataset.addValue(y, series, x);
                    
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
            
  
    


