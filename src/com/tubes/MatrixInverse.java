package com.tubes;

class MatrixInverse extends primMatrix{
    
    MatrixInverse(int row, int col){super(row, col);}
    MatrixInverse(double[][] m, int row, int col){super(m, row, col);}
    
    void addRowTo(int row1, int row2, int toRow, boolean sum){super.addRowTo(row1, row2, toRow, sum);}
    void displayMatrix(){super.displayMatrix();}

    double determinanKofaktor(primMatrix M){return super.determinanKofaktor(M);}

    void transpose(){
        primMatrix temp = new primMatrix(COL, ROW);
        for(int i = 0 ; i<this.COL; i++){
            for(int j = 0; j<this.ROW; j++){
                temp.matrix[i][j] = this.matrix[j][i];
            }
        }
        int tempIdx = this.COL;
        this.COL = this.ROW;
        this.ROW = tempIdx;
        this.matrix = new double[this.ROW][this.COL];
        for (int i = 0; i<this.ROW; i++){
            for(int j = 0; j<this.COL; j++){
                this.matrix[i][j] = temp.matrix[i][j];
            }
        }

    }

    primMatrix subMatrix(int row, int col){
        primMatrix subMatrix = new primMatrix(ROW-1, COL-1);
        int subRow = 0;
        int subCol = 0;
        for(int i = 0; i<ROW; i++){
            for(int j = 0; j<COL; j++){
                if((i != row) && (j!=col)){
                    subMatrix.matrix[subRow][subCol] = this.matrix[i][j];
                    subCol +=1;
                    if (subCol == subMatrix.COL){
                        subRow +=1;
                        subCol = 0;
                    }
                }
            }
        }
        return subMatrix;
    }

    primMatrix Cofacator(){
        primMatrix mNew = new primMatrix(ROW, COL);
        for (int i = 0; i < ROW; ++i){
            for (int j = 0; j < COL; ++j){
                mNew.matrix[i][j] = (Math.pow(-1, (i+j)) * determinanKofaktor(subMatrix(i, j)));
            }
        }
        return mNew;
    }

    void adjoin(){
        this.matrix = Cofacator().matrix;
        transpose();
    }

    // Mencari invers dengan Kofaktor
    primMatrix inversCofac(){
        primMatrix M = new primMatrix(matrix, ROW, COL);
        double det = determinanKofaktor(M);
        primMatrix invers = new primMatrix(ROW, COL);
        adjoin();
        for (int i = 0; i < ROW; ++i){
            for (int j = 0; j < COL; ++j){
                invers.matrix[i][j] = this.matrix[i][j] * (1/det);
            }
        }
        return invers;
    }

    
    MatrixInverse identity(){
        MatrixInverse M = new MatrixInverse(this.ROW, this.COL);
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
    MatrixInverse InversOBE(){
        MatrixInverse M = new MatrixInverse(this.matrix, ROW, COL);
        MatrixInverse identity = identity();
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
