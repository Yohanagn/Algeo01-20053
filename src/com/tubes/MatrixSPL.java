package com.tubes;

class MatrixSPL extends primMatrix {
    //Inherit dari primMatrix
    MatrixSPL(int row, int col){super(row, col);}
    MatrixSPL(double[][] m, int row, int col){super(m, row, col);}

    void inputMat(){super.inputMat();}
    void displayMatrix(){super.displayMatrix();}
    
    double determinanKofaktor(primMatrix M){
        return super.determinanKofaktor(M);
    }
    
    primMatrix takeA(){return super.takeA();}
    primMatrix takeb(){return super.takeb();}
    
    //Fungsi SPL Metode Gauss dan Gauss Jordan (Inherit dari primMatrix) 
    primMatrix gauss(){return super.gauss();}
    primMatrix gaussJordan(){return super.gaussJordan();}
    
    //Fungsi SPL Metode Invers
    primMatrix metodeInvers(){
        primMatrix b = takeb();
        primMatrix A = takeA();
        primMatrix inverseM = A.InversOBE();
        return multiplyMatrix(inverseM, b);
    }

    //Fungsi SPL Metode Cramer
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
