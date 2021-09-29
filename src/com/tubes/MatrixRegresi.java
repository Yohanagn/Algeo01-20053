package com.tubes;
import java.util.Scanner;

public class MatrixRegresi extends primMatrix{
    MatrixRegresi(int row, int col) {super(row, col);}
    MatrixRegresi(double [][] newmat, int row, int col) {super(newmat, row, col);}

    void displayMatrix() {super.displayMatrix();}

    primMatrix makeMatrixRegresi() {
        MatrixSPL mat = new MatrixSPL(this.ROW, this.COL + 1);
        int i = 0;
        int j = 0;

        for (i = 0; i < mat.ROW; i++) {
            for (j = 0; j < mat.COL; j++) {
                if (j == 0) {
                    mat.matrix[i][j] = 1;
                }
                else {
                    mat.matrix[i][j] = this.matrix[i][j-1];
                }
            }
        }
        return mat;
    }

    primMatrix matrixHasil() {
        double[][] mat = makeMatrixRegresi().matrix;
        int row = makeMatrixRegresi().ROW;
        int col = makeMatrixRegresi().COL;
        MatrixSPL Regresi = new MatrixSPL(mat, row, col);
        primMatrix hasil = Regresi.gaussJordan();
        return hasil;
    }

    void Regresi() {
        primMatrix hasil = matrixHasil();
        Scanner scan = new Scanner(System.in);
        String Solution = "Hasil Regresinya: ";
    
        for(int i = 0; i<hasil.ROW; i++){
            double x;
            System.out.print("Masukkan nilai X"+i+": ");
            x = scan.nextDouble();
            double nilaiEstimasi = 0;
            nilaiEstimasi += hasil.matrix[i][0]*x;
        }

        double nilai = 0;
        System.out.print("Nilai Estimasi X adalah:");
        Solution += "Nilai Estimasi X adalah: \n";
        for(int i = 0; i<hasil.ROW; i++) {
            nilai += hasil.matrix[i][0];
        }

        System.out.print(nilai +"\n");
        Solution += nilai+ "\n";
    }
}
