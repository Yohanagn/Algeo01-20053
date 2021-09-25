package com.tubes;

class MatrixSPL extends primMatrix {
    MatrixSPL(int row, int col){super(row, col);}
    MatrixSPL(double[][] m, int row, int col){super(m, row, col);}

    void inputMat(){super.inputMat();}
    void displayMatrix(){super.displayMatrix();    }
    
    double determinanKofaktor(primMatrix M){
        return super.determinanKofaktor(M);
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

    primMatrix Cramer(){
        primMatrix A = takeA();
        primMatrix b = takeb();
        primMatrix Has = new primMatrix(ROW,1);
        double detA = determinanKofaktor(A);
        for(int i=0; i<A.COL; i++){
            A = takeA();
            primMatrix copyA = new primMatrix(A.matrix, A.ROW, A.COL);
            for(int j=0; j<A.ROW; j++){
                copyA.matrix[j][i] = b.matrix[j][0];
            }
            double detAx = determinanKofaktor(copyA);
            Has.matrix[i][0] = detAx/detA;
        } 
        return Has;
    }
}