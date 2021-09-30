package com.tubes;
import java.lang.String;

public class solution extends primMatrix{
    boolean oneSolution = false;
    boolean manySolution = false;
    boolean noSolution = false;

    solution(double[][] m, int row, int col){super(m, row, col);}
    @Override
    primMatrix takeA() {return super.takeA();}
    primMatrix takeb() {return super.takeb();}
    
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

    String InvalidSolution(){
        return "Tidak ada solusi";
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
    /*
    String[] ParametricSolution(){

    }
    */
    void displaySolution(String[] has){
        for(int i=0; i<ROW; i++){
            System.out.println(has[i]);
        }
    }

    
}
