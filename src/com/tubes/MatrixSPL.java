package com.tubes;

class MatrixSPL extends primMatrix {
    boolean oneSolution = false;
    boolean manySolution = false;
    boolean noSolution = false;
    
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

    void check(){
        primMatrix A = takeA();
        primMatrix b = takeb();
        for(int i=0; i<A.ROW; i++){
            boolean zeroInRow = true;
            for(int j=0; j<A.COL; j++){
                if(A.matrix[i][j] != 0){
                    zeroInRow = false;
                }
            }
            if(zeroInRow){
                if(b.matrix[i][0] != 0){
                    this.noSolution = true;
                }else{
                    this.manySolution= true;
                }
            }
        }
        if(!this.noSolution && !manySolution){
            oneSolution = true;
        }
    }

    void checkII(){
        if (determinanOBE() == 0){
            this.noSolution = true;
        }else{
            this.oneSolution = true;
        }
    }

    void InvalidSolution(){
        System.out.println("Tidak ada solusi");
    }

    String[] ExactSolution(){
        primMatrix A = takeA();
        primMatrix b = takeb();
        String[] has = new String[A.ROW];
        double[] tempSolution = new double[A.ROW]; 
        for(int i=(A.ROW)-1; i>=0; i--){
            double temp = b.matrix[i][0];
            for(int j=(i+1); j<A.COL; j++){
                temp -= tempSolution[j]*(A.matrix[i][j]);
            }
            tempSolution[i] = temp;
            has[i] =  "x" + (i+1) + " = " + temp;
        }
        return has;
    }
    String[] ExactSolutionV2(){
        String[] has = new String[this.ROW];
        for(int i=0; i<this.ROW; i++){
            has[i] = "x" + (i+1) + " = " + this.matrix[i][0];
        }
        return has;
    }
    String[] ParametricSolution(){
        primMatrix A = takeA();
        primMatrix b = takeb();
        String[] has = new String[A.ROW];
        for(int i=0; i<b.ROW; i++){
            has[i] = String.valueOf(b.matrix[i][0]) + " = ";
        }
        char[] gnrtVar = {'s','t','u','v','w','x','y','z','a','b',
                          'c','d','e','f','g','h','i','j','k','l',
                          'm','n','o','p','q','r'};
        for(int i=A.ROW-1; i>= 0; i--){
            boolean foundLeadRow = false;
            int idxleadRow=0;
            while(!foundLeadRow){
                if(A.matrix[i][idxleadRow] != 0){
                    foundLeadRow = false;
                }else{
                    idxleadRow += 1;
                }
            }
            has[i] = "x" + (idxleadRow+1) + " = ";
            for(int j=A.COL-1; j>idxleadRow; j--){
                int idxgetVar = 0;
                if(j != idxleadRow+1){
                    has[i] += (-1)*A.matrix[i][j] + gnrtVar[idxgetVar] + " + ";
                }else{
                    has[i] += (-1)*A.matrix[i][j] + gnrtVar[idxgetVar] ;
                }
                idxgetVar += 1;
            }
        }
        return has;
    }
    
    void displaySolution(String[] has){
        for(int i=0; i<ROW; i++){
            System.out.println(has[i]);
        }
    }
}
