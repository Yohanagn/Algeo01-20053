package com.tubes;


class inversMat extends primMatrix{
    
    inversMat(int row, int col){
        super(row, col);
    }
    inversMat(double[][] m, int row, int col){
        super(m, row, col);
    }

    double determinanKofaktor(primMatrix M){
        return super.determinanKofaktor(M);
    }
    void displayMatrix(){
        super.displayMatrix();
    }

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
}
