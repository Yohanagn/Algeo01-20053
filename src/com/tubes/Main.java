package com.tubes;

public class Main {
    
    public static void main(String[] args) {
        double[][] matrix;
        matrix = primMatrix.inputMat();
        primMatrix.displayMatrix(matrix);
        if(fromtoFile.confirmSave()){
            String filename = fromtoFile.createFile();
            fromtoFile.matToFile(filename, matrix);
        }
    }
}
