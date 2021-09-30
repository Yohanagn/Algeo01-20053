package com.tubes;

import java.util.Scanner;

class MatrixInterpolasi  extends primMatrix{
    
    MatrixInterpolasi(int row, int col){super(row, col);}
    MatrixInterpolasi(double[][] m, int row, int col){super(m, row, col);}

    void displayMatrix(){super.displayMatrix();}

    primMatrix makeInterMatrix(){
        primMatrix mNew = new primMatrix(ROW, ROW+1);

        for (int i = 0; i<mNew.ROW; i++){
            for(int j = 0; j<mNew.COL; j++){
                if(j == mNew.COL-1){
                    mNew.matrix[i][j] = this.matrix[i][1];
                }else{
                    mNew.matrix[i][j] = Math.pow(this.matrix[i][0], j);
                }
            }
        }
        return mNew;
    }
    
    // Hasil Interpolasiw
    primMatrix matrixHasil(){
        double[][] m = makeInterMatrix().matrix;
        int row = makeInterMatrix().ROW;
        int col = makeInterMatrix().COL;
        primMatrix interpolasi = new primMatrix(m, row, col);
        primMatrix hasil = interpolasi.gaussJordan();
        return hasil;
    }
    
    //Menampilkan Hasil Interpolasi dan Meng-estimasi nilai X masukan
    double[] interpolasi(){
        primMatrix hasil = matrixHasil();
        Scanner scan = new Scanner(System.in);
        System.out.println("Persamaan Interpolasi : ");
        for(int i = 0; i<hasil.ROW; i++){
            if (i==0){
                System.out.printf("%.3f", hasil.matrix[i][hasil.COL-1]);
            }else{
                if (hasil.matrix[i][hasil.COL-1] < 0){
                    System.out.printf(" %.3fX^(%d)", hasil.matrix[i][hasil.COL-1], i);
                }else{
                    System.out.printf(" +%.3fX^(%d)", hasil.matrix[i][hasil.COL-1], i);
                }

            }
        }
        double[] est = new double[2];
        double x;
        System.out.print("\nMasukkan nilai X yang ingin diestimasi ( dalam rentang X-min dan X-max yang telah dimasukkan ) : ");
        x = scan.nextDouble();
        double nilaiEstimasi = 0;

        for(int j = 0; j<hasil.ROW; j++){
            nilaiEstimasi += hasil.matrix[j][hasil.COL-1]*Math.pow(x, j);
        }
        System.out.printf("Nilai estimasi untuk X adalah : %.3f", nilaiEstimasi);
        est[0] = x;
        est[1] = nilaiEstimasi;
        return est;
    }
}
