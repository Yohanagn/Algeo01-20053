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
    
    void addRowTo(int row1, int row2, double num, boolean sum){
        if(sum){
            for(int i=0; i<this.COL; i++){
                this.matrix[row1][i] = this.matrix[row1][i] + this.matrix[row2][i]*num;
            }
        }else{
            for(int i=0; i<this.COL; i++){
                this.matrix[row1][i] = this.matrix[row1][i] - this.matrix[row2][i]*num;
            }
        }
    }
    
    void setELMT(int row, int col, double x){
        this.matrix[row][col] = x;
    }

    int getFirstIndex(int ROW) {
        boolean flag = false;
        int i = 0;

        while (i < this.COL && !(flag)) {
            if (this.matrix[ROW][i] != 0) {
                flag = true;
            }
            else {
                i++;
            }
        }
        if (flag){
            return i;
        }
        else {
            return this.COL;
        }
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
    
    primMatrix multiplyMatrix(primMatrix m1, primMatrix m2){
        primMatrix m3 = new primMatrix(m1.ROW, m2.COL);
        for(int i=0; i<m1.ROW; i++){
            for(int j=0; j<m2.COL; j++){
                double temp = 0;
                for(int k=0; k<m1.COL; k++){
                    temp += m1.matrix[i][k]*m2.matrix[k][j];
                }
                m3.matrix[i][j] = temp;
            }
        }
        return m3;
    }

    //Gauss
    void gauss(){
        int k = 0;
        int i, j;
        for (i = 0; i < this.ROW; i++) {
            int l = 0;
            for (j = 0; j < this.COL; j++) {
                if(this.matrix[i][j] != 0) {
                    l = j;
                    break;
                }
            }
            if (l == this.COL - 1) {
                this.multiplyRowConst(i, 1/(this.matrix[i][l]));
            }
            if (this.matrix[i][i+k] == 0){
                for (j = i + 1; j < this.COL; j++) {
                    if (this.matrix[j][i+k] != 0){
                        this.swapRow(i, j);
                        break;
                    }
                }    
            }
            if (this.matrix[i][i+k] != 0) {
                this.multiplyRowConst(i, 1/(this.matrix[i][i+k]));
            }
            for (j = i+1; j<this.COL; j++) {
                double number = -1 * (this.matrix[j][i+k] / this.matrix[i][i+k];
                this.addRowTo(j, i, number, true);
            }
        }
    }

    void gaussJordan(){
        int i, j;
        int row, col;
        this.gauss();

        for (i = this.ROW; i >= 1; i--) {
            while (this.matrix[row][i] == 0) {
                i--;
            }
            if (this.matrix[row][i] == 0) {
                return;
            }
            else {
                for (j = i - 1; j >= 1; j--) {
                    double num = -1 * this.matrix[j][getFirstIndex(i)];
                    this.addRowTo(j, i, num, true);
                }   
            }
        }

    }
    
    //Asumsi matrix persegi/ size matrix nxn
    double determinanKofaktor(primMatrix M){
        double det;
        if (M.COL == 2){
            return (M.matrix[0][0]*M.matrix[1][1]) - (M.matrix[1][0]*M.matrix[0][1]);
        }else{
            det = 0;
            for(int i=0; i<M.COL; i++){
                primMatrix MatrixN = new primMatrix((M.ROW)-1,(M.COL)-1);
                int idxRowNewM = 0;
                for(int j=0; j<M.ROW; j++){
                    if(j != 0){
                        int idxColNewM = 0;
                        for(int k=0; k<M.COL; k++){
                            if(k!=i){
                                MatrixN.setELMT(idxRowNewM, idxColNewM, M.matrix[j][k]);
                                idxColNewM += 1;
                            }
                        }
                        idxRowNewM += 1;
                    }
                }
                if (i%2 == 0){
                    det += (M.matrix[0][i])*M.determinanKofaktor(MatrixN);
                }else{
                    det -= (M.matrix[0][i])*M.determinanKofaktor(MatrixN);
                }

            }
        }
        return det;
    }
    
    double determinanOBE(){
        double det=1;
        int countSwap = 0;
        if(ROW != COL){
            det = 0;
        }
        else{
            for(int i=0; i<ROW; i++){
                if(matrix[i][i] == 0){
                    //mencari pada kolom dibawahnya yang tidak 0
                    boolean notFind = true;
                    int tempIdx = i+1;
                    while (notFind && tempIdx<ROW){
                        if (matrix[tempIdx][i] != 0){
                            swapRow(tempIdx, i);
                            countSwap += 1;
                            notFind = false;
                        }
                        tempIdx += 1;

                    }
                }
                double denominator = matrix[i][i];
                for(int j=i+1; j<ROW; j++){
                    double numerator = matrix[j][i];
                    for(int k=0; k<COL; k++){
                        matrix[j][k] = matrix[j][k] - ((numerator/denominator)*matrix[i][k]);
                    }
                }
            }
            for(int i=0; i<ROW; i++){
                det *= matrix[i][i];
            }
        }
        return Math.pow(-1, countSwap)*det;
    }
    
    primMatrix identity(){
        primMatrix M = new primMatrix(this.ROW, this.COL);
        for(int i=0 ; i<this.ROW; i++){
            for(int j=0 ; j<this.COL; j++){
                if(i == j){
                    M.matrix[i][j] = 1;
                }else{
                    M.matrix[i][j] = 0;
                }
            }
        }
        return M;
    }
                                      
    primMatrix InversOBE(){
        primMatrix M = new primMatrix(this.matrix, ROW, COL);
        primMatrix identity = identity();
        if (determinanKofaktor(M) == 0){
            identity = null;
        }else{
            for(int i=0; i<M.ROW; i++){
                double denominator = M.matrix[i][i];
                for(int j=0; j<M.COL ;j++){
                    M.multiplyRowConst(i, 1/denominator);
                    identity.multiplyRowConst(i, 1/denominator);
                }
                for(int k=i+1; k<M.ROW; k++){
                    double numerator = M.matrix[k][i];
                    for(int l=0; l<M.COL; l++){
                        M.matrix[k][l] -= numerator*M.matrix[i][l];
                        identity.matrix[k][l] -= numerator*identity.matrix[i][l];
                    }
                } 
            }
            for(int i=(M.ROW-1); i>0; i--){
                for(int j=(i-1); j>=0; j--){
                    double numerator = M.matrix[j][i];
                    for(int k=(M.COL-1); k>= 0; k--){
                        M.matrix[j][k] -= numerator*M.matrix[i][k];
                        identity.matrix[j][k] -= numerator*identity.matrix[i][k];
                    }
                }
            }
        }
        return identity;
    }
}

