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

    primMatrix matrixHasil(){
        double[][] m = makeInterMatrix().matrix;
        int row = makeInterMatrix().ROW;
        int col = makeInterMatrix().COL;
        MatrixSPL interpolasi = new MatrixSPL(m, row, col);
        primMatrix hasil = interpolasi.Cramer();
        return hasil;
    }

   void interpolasi(){
        primMatrix hasil = matrixHasil();
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i<hasil.ROW; i++){
            if (i==0){
                System.out.print(hasil.matrix[i][0]);
            }else{
                if (hasil.matrix[i][0] < 0){
                    System.out.print(" " + hasil.matrix[i][0]+"x^(" + i + ")");
                }else{
                    System.out.print(" + " + hasil.matrix[i][0]+"x^(" + i + ")");
                }

            }
        }

        int n;
        System.out.print("\nBanyak titik yang ingin diestimasi : ");
        n = scan.nextInt();
        for(int i = 0; i<n; i++){
            double x;
            System.out.print("Masukkan nilai X yang ingin diestimasi ( dalam rentang X-min dan X-max yang telah dimasukkan ) : ");
            x = scan.nextDouble();
            double nilaiEstimasi = 0;

            for(int j = 0; j<hasil.ROW; j++){
                nilaiEstimasi += hasil.matrix[j][0]*Math.pow(x, j);
            }
            System.out.println("Nilai estimasi untuk X adalah : " + nilaiEstimasi);
        }
        
    }
}
