package com.tubes;
import java.util.Scanner;

public class MatrixRegresi extends primMatrix{
    MatrixRegresi(int row, int col) {super(row, col);}
    MatrixRegresi(double [][] newmat, int row, int col) {super(newmat, row, col);}

    void displayMatrix() {super.displayMatrix();}
    primMatrix multiplyMatrix(primMatrix m1, primMatrix m2){return super.multiplyMatrix(m1, m2);}
        // Ini Pakai Rumus (X'X)^(-1)(X'Y) rumus cepat dari internet;
    primMatrix createX(){
        primMatrix x = new primMatrix(ROW, COL);

        for(int i = 0; i<x.ROW; i++){
            for(int j = 0; j<x.COL; j++){
                if(j == 0){
                    x.matrix[i][j] = 1;
                }else{
                    x.matrix[i][j] = this.matrix[i][j-1];
                }
            }
        }
        return x;
    }

    primMatrix createXtrans(){
        primMatrix x = createX();
        MatrixInverse temp = new MatrixInverse(x.matrix, x.ROW, x.COL);
        temp.transpose();
        primMatrix xTrans = new primMatrix(temp.matrix, temp.ROW, temp.COL);
        return xTrans;
    }

    primMatrix createY(){
        primMatrix y = new primMatrix(ROW, 1);
        for(int i =0; i<y.ROW; i++){
            y.matrix[i][0] = this.matrix[i][COL-1];
        }
        return y;
    }

    primMatrix xAndxTrans(){
        primMatrix x = createX();
        primMatrix xTrans = createXtrans();
        primMatrix temp = multiplyMatrix(xTrans, x);
        MatrixInverse temp2 = new MatrixInverse(temp.matrix, temp.ROW, temp.COL);
        primMatrix hasil = temp2.inversCofac();
        return hasil;
    }
    primMatrix xTransAndY(){
        primMatrix y = createY();
        primMatrix xTrans = createXtrans();
        primMatrix hasil = multiplyMatrix(xTrans, y);
        return hasil;
    }

    primMatrix hasilRegresiLB(){
        primMatrix temp1 = xAndxTrans();
        primMatrix temp2 = xTransAndY();
        primMatrix hasilReg = multiplyMatrix(temp1, temp2);
        return hasilReg;
    }

    // Ini Pakai Rumus Yang ada di Spesifikasi Tubes
    primMatrix sigma(){
        primMatrix sigma = new primMatrix(1, COL-1);
        for(int i = 0; i<COL-1; i++){
            for(int j = 0; j<ROW; j++){
                sigma.matrix[0][i] +=  this.matrix[j][i];
            }
        }
        return sigma;
    }

    primMatrix sigmaKuadrat(){
        primMatrix sigmaKuadrat = new primMatrix(1, COL-1);
        for(int i = 0; i<COL-1; i++){
            for(int j = 0; j<ROW; j++){
                sigmaKuadrat.matrix[0][i] +=  Math.pow(this.matrix[j][i], 2);
            }
        }
        return sigmaKuadrat;
    }

    primMatrix otherSigma(){
        double temp = (((0.5)*(Math.pow(COL-2, 2))) + ((0.5)*(COL-2)));
        int colOther = (int) temp;
        primMatrix other = new primMatrix(1, colOther);
        int tempCol = 0;
        for(int i = 0; i<COL-1;i++){
            for(int j = i+1; j<COL-1; j++){
                for(int k =0; k< ROW; k++){
                    other.matrix[0][tempCol] += this.matrix[k][i]*this.matrix[k][j];
                }
                tempCol++;
            }
        }
        return other;
    }

    primMatrix makeA(){
        primMatrix A = new primMatrix(COL, COL);
        primMatrix temp1 = new primMatrix(COL, COL);
        primMatrix sigma = sigma();
        primMatrix sigmaKuadrat = sigmaKuadrat();
        primMatrix otherSigma = otherSigma();
        int idxSigma = 0;
        int idxOther = 0;
        for(int i = 0; i < temp1.ROW; i++){
            for(int j = 0; j<temp1.COL; j++){
                if(i==j){
                    if(i==0&j==0){
                        temp1.matrix[i][j] = ROW;
                    }else{
                        temp1.matrix[i][j] = sigmaKuadrat.matrix[0][j-1];
                    }
                }else if(i==0){
                    temp1.matrix[i][j] = sigma.matrix[0][idxSigma];
                    idxSigma++;
                }else if(i>0 && j>=i){
                    temp1.matrix[i][j] = otherSigma.matrix[0][idxOther];
                    idxOther++;
                }
                
            }
        }
        MatrixInverse temp2 = new MatrixInverse(temp1.matrix, temp1.ROW, temp1.COL);
        temp2.transpose();

        for(int i = 0; i < temp1.ROW; i++){
            for(int j = 0; j<temp1.COL; j++){
                if(i!=j){
                    A.matrix[i][j] = temp1.matrix[i][j] + temp2.matrix[i][j];
                }else{
                    A.matrix[i][j] = temp1.matrix[i][j];
                }
            }
        }
        return A;
    }

    primMatrix makeH(){
        primMatrix H = new primMatrix(COL, 1);
        int tempRow = H.ROW-1;
            for(int j = COL-2; j>=0; j--){
                for(int k =0; k< ROW; k++){
                    H.matrix[tempRow][0] += this.matrix[k][COL-1]*this.matrix[k][j];
                }
                tempRow--;
            }
            for(int i = 0; i<ROW; i++){
                H.matrix[0][0] += this.matrix[i][COL-1];
            }
        return H;
    }

    primMatrix createMatrixReg(){
        primMatrix A = makeA();
        primMatrix H = makeH();
        primMatrix MatReg = new primMatrix(A.ROW, (A.ROW+1));
        for(int i = 0; i<MatReg.ROW; i++){
            for(int j = 0; j<MatReg.COL; j++){
                if(j==MatReg.COL-1){
                    MatReg.matrix[i][j] = H.matrix[i][0];
                }else{
                    MatReg.matrix[i][j] = A.matrix[i][j];
                }
            }
        }
        return MatReg;
    }

    primMatrix HasilReg(){
        primMatrix MatReg = createMatrixReg();
        primMatrix temp = new MatrixSPL(MatReg.matrix, MatReg.ROW, MatReg.COL);
        primMatrix hasilreq = temp.gaussJordan();
        return hasilreq;
    }
}
