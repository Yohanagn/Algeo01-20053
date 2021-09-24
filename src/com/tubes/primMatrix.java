package com.tubes;
import java.util.*;

public class primMatrix {
    
    public static double[][] inputMat(){
        Scanner scan = new Scanner(System.in);
        int row, col;

        do{
            System.out.print("Banyak baris : ");
            row = scan.nextInt();
            if (row<0){
                System.out.println("Masukkan Salah.");
            }
        }while(row<0);

        do{
            System.out.print("Banyak kolom : ");
            col = scan.nextInt();
            if (col<0){
                System.out.println("Masukkan Salah.");
            }
        }while(col<0);

        double[][] matrix = new double[row][col];
        int i,j;
        for (i = 0; i<row; i++){
            for (j = 0; j<col; j++){
                matrix[i][j] = scan.nextDouble();
            }
        }
        return matrix;
    }

    public static void displayMatrix(double[][] matrix){
        for (double[] brs : matrix){
            for(double el : brs){
                System.out.print(el + " ");
            }
            System.out.print("\n");
        }
    }
}
