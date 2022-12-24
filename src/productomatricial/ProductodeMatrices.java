package Productomatricial;



import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class ProductodeMatrices {

    
    public static void main(String[] args) {
       
        MetalLookAndFeel.setCurrentTheme(new EstiloHalloween());
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

        
        double[][] resultado = new ProductodeMatrices().producto(MatrizA,MatrizB);
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

    public double[][] producto(double[][] A, double[][] B) {
     
        int filas = A.length;
        int columnas = B[0].length;
        double[][] matrizproducto = new double[filas][columnas];
        //Usamos Pthread de Java 
       //Usamos una lista de hilos
        LinkedList<Thread> listadehilos = new LinkedList<Thread>();
       //Cada elemento tiene un hilo
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Thread hilo = new Thread(
                        new ProductoHilo(A, B, matrizproducto, fila, columna));
                listadehilos.add(hilo);
                hilo.start();
                hilo.run();
            }
        }

        //Esperar a que los hilos hijos terminen
        for (Thread hilo : listadehilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       	
        return matrizproducto;
    //Se devuelve la matriz producto
    }
}


class ProductoHilo implements Runnable {

    private double[][] A;
    private double[][] B;
    private double[][] matrizproducto;
    private int fila;
    private int columna;

    public ProductoHilo(double[][] A, double[][] B, double[][] matrizproducto, int fila, int columna) {
        this.A = A;
        this.B = B;
        this.matrizproducto = matrizproducto;
        this.fila = fila;
        this.columna = columna;
    }

 
    public void run() {
        matrizproducto[fila][columna] = 0.0;
        for (int i = 0; i < B.length; i++) {
            matrizproducto[fila][columna] =matrizproducto[fila][columna]+ A[fila][i] * B[i][columna];
        }
    }
}

