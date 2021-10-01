package com.tubes;
import java.util.Scanner;

class primMatrix {
    double[][] matrix;
    int ROW,COL;
    
    primMatrix(int row, int col){
        this.ROW = row;
        this.COL = col;
        this.matrix = new double[row][col];
    }

    primMatrix(double[][] m, int row, int col){
        this.matrix = m;
        this.ROW = row;
        this.COL = col;
    }
    
    //INPUT MATRIKS
    void inputMat(){
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i<this.ROW; i++){
            for (int j = 0; j<this.COL; j++){
                this.matrix[i][j] = scan.nextDouble();
            }
        }
    }
    
    //TAMPILKAN MATRIKS
    void displayMatrix(){
        for (int i = 0; i < this.ROW; i++) {
            for (int j = 0; j < this.COL; j++) {
                if(j != ((this.COL) - 1)) {
                    System.out.printf("%f ", this.matrix[i][j]);
                }else{
                    System.out.printf("%f\n", this.matrix[i][j]);
                }
            }
        }
    }
    
    void setELMT(int row, int col, double x){
        this.matrix[row][col] = x;
    }

    //OPERASI PENJUMLAHAN ANTAR DUA BARIS
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

    //TUKAR BARIS
    void swapRow(int row1, int row2){
        double temp;
        for(int i=0; i<this.COL; i++){
            temp = this.matrix[row1][i];
            this.matrix[row1][i] = this.matrix[row2][i];
            this.matrix[row2][i] = temp;
        }
    }
    primMatrix takeA(){
        primMatrix M = new primMatrix(ROW,COL-1);
        for(int i=0; i<this.ROW; i++){
            for(int j=0; j<this.COL-1; j++){
                M.matrix[i][j] = this.matrix[i][j];
            }
        }
        return M;
    }
    primMatrix takeb(){
        primMatrix M = new primMatrix(ROW,1);
        for(int i=0; i<this.ROW; i++){
            M.matrix[i][0] = this.matrix[i][COL-1];
        }
        return M;
    }

    //MENGALIKAN BARIS DENGAN KONSTANTA
    void multiplyRowConst(int row, double x){
        for(int i=0; i<this.COL; i++){
            this.matrix[row][i] *= x;
        }
    }

    //MENGALIKAN 2 BUAH BARIS
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
    
    //OPERASI ELEMINASI GAUSS
    primMatrix gauss(){
        primMatrix M = new primMatrix(this.matrix, this.ROW, this.COL);
        for(int i=0; i<M.ROW; i++){
            int tempIdxBrs = i;
            int tempIdxKol = i;
            boolean leadRowIsZero = false;
            boolean ZeroRow = false;
            if(M.matrix[tempIdxBrs][tempIdxKol] == 0){
                leadRowIsZero = true;
            }
            while(leadRowIsZero && tempIdxBrs < M.COL -1 ){
                if(tempIdxBrs == (M.ROW-1)  && tempIdxKol<(M.COL-1)){
                    tempIdxBrs = i;
                    tempIdxKol += 1;
                }else if(tempIdxBrs == (M.ROW-1) && tempIdxKol==(M.COL-1)){
                    leadRowIsZero = false;
                    ZeroRow = true;
                }else{
                    if (M.matrix[tempIdxBrs][tempIdxKol] != 0){
                        System.out.println("terjadi tukar baris");
                        M.swapRow(i, tempIdxBrs);
                        leadRowIsZero = false;
                    }else{
                        tempIdxBrs += 1;
                    }
                }
            }
            if(!ZeroRow){
                double denominator = M.matrix[i][tempIdxKol];
                M.multiplyRowConst(i, 1/denominator);
                for(int j=i+1; j<M.ROW; j++){
                    double numerator = M.matrix[j][tempIdxKol];
                    for (int k=0; k<M.COL; k++){
                        M.matrix[j][k] -= numerator*M.matrix[i][k];
                    }
                }
            }
        }
        return M;
    }

    //OPERASI ELEMINASI GAUSS JORDAN
    primMatrix gaussJordan(){
        primMatrix M = new primMatrix(this.matrix, ROW, COL);
        for(int i=0; i<M.ROW; i++){
            int tempIdxBrs = i;
            int tempIdxKol = i;
            boolean leadRowIsZero = false;
            boolean ZeroRow = false;
            if(M.matrix[tempIdxBrs][tempIdxKol] == 0){
                leadRowIsZero = true;
            }
            while(leadRowIsZero && tempIdxBrs < M.COL -1 ){
                if(tempIdxBrs == (M.ROW-1)  && tempIdxKol<(M.COL-1)){
                    tempIdxBrs = i;
                    tempIdxKol += 1;
                }else if(tempIdxBrs == (M.ROW-1) && tempIdxKol==(M.COL-1)){
                    leadRowIsZero = false;
                    ZeroRow = true;
                }else{
                    if (M.matrix[tempIdxBrs][tempIdxKol] != 0){
                        M.swapRow(i, tempIdxBrs);
                        leadRowIsZero = false;
                    }else{
                        tempIdxBrs += 1;
                    }
                }
            }
            if(!ZeroRow){
                double denominator = M.matrix[i][tempIdxKol];
                M.multiplyRowConst(i, 1/denominator);
                for(int j=0; j<M.ROW; j++){
                    double numerator = M.matrix[j][tempIdxKol];
                    for(int k=0; k<M.COL; k++){
                        if(j!=i){
                            M.matrix[j][k] -= numerator*M.matrix[i][k];
                        }
                    }
                }
            }
        }
        return M;
    }
    
    //DETERMINAN MATRIKS DENGAN METODE KOFAKTOR
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

    //DETERMINAN MATRIKS DENGAN OPERASI BARIS ELEMENTER
    double determinanOBE(){
        double det=1;
        int countSwap = 0;
        if(ROW != COL){
            det = 0;
        }
        else{
            for(int i=0; i<ROW; i++){
                if(matrix[i][i] == 0){
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

    //IDENTITAS MATRIKS DENGAN UKURAN YANG SAMA DENGAN OBJECT
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
    
    //INVERS MATRIKS DENGAN OPERASI BARIS ELEMENTER
    primMatrix InversOBE(){
        primMatrix M = new primMatrix(this.matrix, ROW, COL);
        primMatrix identity = identity();
        
            for(int i=0; i<M.ROW; i++){
                if(matrix[i][i] == 0){
                    boolean notFind = true;
                    int tempIdx = i+1;
                    while (notFind && tempIdx<ROW){
                        if (matrix[tempIdx][i] != 0){
                            swapRow(tempIdx, i);
                            identity.swapRow(tempIdx,i);
                            notFind = false;
                        }
                        tempIdx += 1;
                    }
                }
                double denominator = M.matrix[i][i];
                M.multiplyRowConst(i, 1/denominator);
                identity.multiplyRowConst(i, 1/denominator);
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
        return identity;
    }
    
}

