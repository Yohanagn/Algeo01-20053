package com.tubes;

class MatrixSPL extends primMatrix {
    boolean noSolution = false;
    boolean oneSolution = false;
    boolean manySolution = false;
    
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
        primMatrix  b = takeb();
        if (A.COL == A.ROW){
            int[] indexWithZeroRow = new int[99];
            int tempIdx =0;
            boolean isAllHaveLeadCoef = true;
            
            int i =0;
            while(i <A.ROW){
                boolean zeroRow = true;
                int j = 0;
                while(j <A.COL){
                    if(A.matrix[i][j] != 0){
                        zeroRow = false;
                    }
                    j++;
                }
                if(zeroRow){
                    isAllHaveLeadCoef = false;
                    indexWithZeroRow[tempIdx] = i;
                    tempIdx += 1;
                }
                i++;
            }

            if(isAllHaveLeadCoef){
                this.oneSolution = true;
            }else{
                boolean tempManySolution = true;
                for(int k=0; k<tempIdx; k++){
                    if(b.matrix[indexWithZeroRow[k]][0] != 0){
                        tempManySolution = false;
                    }
                }
                if(tempManySolution){
                    this.manySolution = true;
                }else{
                    this.noSolution = true;
                }
            }
        }else if(A.COL > A.ROW){
            this.manySolution = true;
        }
    }

    private float round(double d, int i) {
        return 0;
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
        for(int i=A.ROW-1; i>=0; i--){
            int j=0;
            boolean flag = true;
            //System.out.printf("%f", has[i]);
            while(j < A.COL && flag){
                if(A.matrix[i][j] != 0){
                    flag = false;
                    has[i] = "x" + (j+1) +  " = " + b.matrix[i][0];
                }
                j++;
            }
        }
        char[] gnrtVar = {'s','t','u','v','w','x','y','z','a','b',
                          'c','d','e','f','g','h','i','j','k','l',
                          'm','n','o','p','q','r'};
        for(int i=A.ROW-1; i>= 0; i--){
            boolean ZeroRow = false;
            boolean foundLeadRow = false;
            int idxleadRow=0;
            while(!foundLeadRow & !ZeroRow){
                if(idxleadRow == (A.COL-1) && A.matrix[i][idxleadRow] == 0){
                    ZeroRow = true;
                }else if(A.matrix[i][idxleadRow] != 0){
                    foundLeadRow = true;
                }else{
                    idxleadRow += 1;
                }
            }
            if(!ZeroRow){
                int idxgetVar = 0;
                for(int j=A.COL-1; j>idxleadRow; j--){
                    if(j != idxleadRow && A.matrix[i][j] != 0){
                        has[i] += " + " + (-1)*A.matrix[i][j] +""+ gnrtVar[idxgetVar] ;
                    }
                    idxgetVar += 1;
                }
            }
        }
        return has;
    }
    
    void displaySolution(String[] has){
        for(int i=0; i<ROW; i++){
            if(has[i] != null ){
                System.out.println(has[i]);
            }
        }
    }
}
