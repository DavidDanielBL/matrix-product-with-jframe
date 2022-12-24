/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author David
 */
public class ProductoMatricial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MetalLookAndFeel.setCurrentTheme(new Productomatricial.EstiloHalloween());
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame pantalla = new JFrame("MATRIX");
        pantalla.setVisible(true);
        pantalla.setBounds(10, 10, 400, 400);
        JTextArea fondo = new JTextArea();
        fondo.setBackground(Color.BLACK);
        fondo.setForeground(Color.GREEN);
        pantalla.add(fondo);
        String texto = "\n";
        String matrizA = "\n";
        String matrizB = "\n";
      //Se establecen las matrices 
        double[][] MatrizA = new double[][]{{1, 0,0 }, {0, 1, 0}, {0, 0, 1}};
        double[][] MatrizB = new double[][]{{20, 30, 50}, {4, 5, 8}, {7,14, 12}};

        
        double[][] resultado = new Productomatricial.ProductodeMatrices().producto(MatrizA,MatrizB);
        //Matriz A
         for (int i = 0; i < MatrizA.length; i++) {
            for (int j = 0; j < MatrizA[0].length; j++) {
               matrizA += "\t " + (double) Math.round(1000 * MatrizA[i][j]) / 1000;

            }
            matrizA += "\n";

        }
        //MATRIZ B
        for (int i = 0; i < MatrizB.length; i++) {
            for (int j = 0; j < MatrizB[0].length; j++) {
                      
             matrizB += "\t " + (double) Math.round(1000 * MatrizB[i][j]) / 1000;

            }
            matrizB += "\n";

        }

       
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                texto += "\t " + (double) Math.round(1000 * resultado[i][j]) / 1000;

            }
            texto += "\n";

        }
        texto ="MATRIZ A"+  matrizA+ "\n"+"\n"+"MATRIZ B"  +matrizB+ "\n"+"\n"+"MATRIZ PRODUCTO"+ "\n"+"\n"+texto + "\n";
           
        fondo.setText(texto);
    }
    
}
