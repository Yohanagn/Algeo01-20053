package com.tubes;
import java.util.Scanner;

class primMatrix {
    double[][] matrix;
    int ROW,COL;
    
    //Matrix tanpa input
    primMatrix(int row, int col){
        this.ROW = row;
        this.COL = col;
        this.matrix = new double[row][col];
    }

    //Matrix dengan input, input bisa berasal dari file txt maupun main program
    primMatrix(double[][] m, int row, int col){
        this.matrix = m;
        this.ROW = row;
        this.COL = col;
    }
    
    void inputMat(){
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i<this.ROW; i++){
            for (int j = 0; j<this.COL; j++){
                this.matrix[i][j] = scan.nextDouble();
            }
        }
    }
    void displayMatrix(){
        for (int i = 0; i < this.ROW; i++) {
            for (int j = 0; j < this.COL; j++) {
                if(j != (this.COL - 1)) {
                    System.out.printf("%f ", this.matrix[i][j]);
                }else{
                    System.out.printf("%f\n", this.matrix[i][j]);
                }
            }
        }
    }
    
    void addRowTo(int row1, int row2, int toRow, boolean sum){
        if(sum){
            for(int i=0; i<this.COL; i++){
                this.matrix[toRow][i] = this.matrix[row1][i] + this.matrix[row2][i];
            }
        }else{
            for(int i=0; i<this.COL; i++){
                this.matrix[toRow][i] = this.matrix[row1][i] - this.matrix[row2][i];
            }
        }
    }
    
    void setELMT(int row, int col, double x){
        this.matrix[row][col] = x;
    }

    void swapRow(int row1, int row2){
        double temp;
        for(int i=0; i<this.COL; i++){
            temp = this.matrix[row1][i];
            this.matrix[row1][i] = this.matrix[row2][i];
            this.matrix[row2][i] = temp;
        }
    }

    void multiplyRowConst(int row, double x){
        for(int i=0; i<this.COL; i++){
            this.matrix[row][i] *= x;
        }
    }

    //Gauss
    void gauss(){

    }
    void gaussJordan(){
        
    }
    //Asumsi matrix persegi/ size matrix nxn
    double determinanKofaktor(primMatrix M){
        double det;
        if (M.COL == 2){
            return (M.matrix[0][0]*M.matrix[1][1] - M.matrix[1][0]*M.matrix[0][1]);
        }else{
            det = 0;
            for(int i=0; i<M.COL; i++){
                primMatrix MatrixN = new primMatrix(M.ROW-1,M.COL-1);
                int idxRowNewM = 0;
                for(int j=0; j<M.ROW; j++){
                    if(j != 0){
                        int idxColNewM = 0;
                        for(int k=0; k<M.COL; k++){
                            if(k!=i){
                                MatrixN.setELMT(idxRowNewM, idxColNewM, M.matrix[j][k]);
                            }
                        }
                        idxRowNewM += 1;
                    }
                }
                if (i%2 == 0){
                    det += M.matrix[0][i]*determinanKofaktor(MatrixN);
                }else{
                    det -= M.matrix[0][i]*determinanKofaktor(MatrixN);
                }
            }
        }
        return det;
    }

}

