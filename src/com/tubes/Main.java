package com.tubes;
import java.io.IOException;
import java.util.Scanner;



public class Main { 
    public static void main(String[] args) throws IOException {       
        int inputMain;
        int baris, kolom;
        do{
            inputMain =  getMainOption();
            if(inputMain == 1){
                int optionSPL = getSPLOption();
                if(optionSPL == 1){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputKolom();
                        MatrixSPL M = new MatrixSPL(baris, kolom);
                        M.inputMat();
                        primMatrix tempMatrix = M.gauss();
                        M.matrix = tempMatrix.matrix;
                        M.check();
                        if(M.noSolution){
                            M.InvalidSolution();
                        }else if(M.oneSolution){
                            M.displaySolution(M.ExactSolution());
                        }else if(M.manySolution){
                            M.displaySolution(M.ParametricSolution());
                        }
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        MatrixSPL M = new MatrixSPL(mat, size[0], size[1]);
                        primMatrix tempMatrix = M.gauss();
                        M.matrix = tempMatrix.matrix;
                        M.check();
                        M.displaySolution(M.ExactSolution());
                    }
                }else if(optionSPL == 2){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputKolom();
                        MatrixSPL M = new MatrixSPL(baris, kolom);
                        M.inputMat();
                        primMatrix tempMatrix = M.gaussJordan();
                        M.matrix = tempMatrix.matrix;
                        M.check();
                        if(M.noSolution){
                            M.InvalidSolution();
                        }else if(M.oneSolution){
                            M.displaySolution(M.ExactSolution());
                        }else if(M.manySolution){
                            M.displaySolution(M.ParametricSolution());
                        }
                    }else if(inputMetode == 2){

                    }
                }else if(optionSPL == 3){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        MatrixSPL M = new MatrixSPL(baris, kolom);
                        M.inputMat();
                        double[][] mTemp = new double[baris][kolom];//Supaya disimpan ke file txt, matriks utama masih ada
                        for(int i = 0; i<baris; i++){
                            for(int j = 0; j<kolom; j++){
                                mTemp[i][j]=M.matrix[i][j];
                            }
                        }
                        double det = M.determinanKofaktor(M.takeA());
                        primMatrix tempMatrix = M.metodeInvers();
                        M.matrix = tempMatrix.matrix;
                        if(det == 0){
                            M.InvalidSolution();
                        }else{
                            M.displaySolution(M.ExactSolutionV2());
                            fromtoFile file = new fromtoFile();
                            if(file.confirmSave()){
                                file.splToFile(file.createFile(), mTemp, M.ExactSolutionV2());
                            }
                        } 
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        double[][] matTemp = file.fileToMat(filename);
                        MatrixSPL M = new MatrixSPL(mat, size[0], size[1]);
                        double det = M.determinanKofaktor(M.takeA());
                        primMatrix tempMatrix = M.metodeInvers();
                        M.matrix = tempMatrix.matrix;
                        if(det == 0){
                            M.InvalidSolution();
                        }else{
                            M.displaySolution(M.ExactSolutionV2());
                            if(file.confirmSave()){
                                file.splToFile(file.createFile(), matTemp, M.ExactSolutionV2());
                            }
                        } 
                    }
                }else if(optionSPL == 4){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        MatrixSPL M = new MatrixSPL(baris, kolom);
                        M.inputMat();
                        double[][] mTemp = new double[baris][kolom];//Supaya disimpan ke file txt, matriks utama masih ada
                        for(int i = 0; i<baris; i++){
                            for(int j = 0; j<kolom; j++){
                                mTemp[i][j]=M.matrix[i][j];
                            }
                        }
                        double det = M.determinanKofaktor(M.takeA());
                        primMatrix tempMatrix = M.Cramer();
                        M.matrix = tempMatrix.matrix;
                        if(det == 0){
                            M.InvalidSolution();
                        }else{
                            M.displaySolution(M.ExactSolutionV2());
                            fromtoFile file = new fromtoFile();
                            if(file.confirmSave()){
                                file.splToFile(file.createFile(), mTemp, M.ExactSolutionV2());
                            }
                        } 
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        double[][] matTemp = file.fileToMat(filename);
                        MatrixSPL M = new MatrixSPL(mat, size[0], size[1]);
                        double det = M.determinanKofaktor(M.takeA());
                        primMatrix tempMatrix = M.Cramer();
                        M.matrix = tempMatrix.matrix;
                        if(det == 0){
                            M.InvalidSolution();
                        }else{
                            M.displaySolution(M.ExactSolutionV2());
                            if(file.confirmSave()){
                                file.splToFile(file.createFile(), matTemp, M.ExactSolutionV2());
                            }
                        } 
                    }
                }
            }else if(inputMain == 2){
                int optionDeterminan = getDeterminanOption();
                if(optionDeterminan == 1){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        primMatrix M = new primMatrix(baris, kolom);
                        M.inputMat();
                        double[][] mTemp = new double[baris][kolom];//Supaya disimpan ke file txt, matriks utama masih ada
                        for(int i = 0; i<baris; i++){
                            for(int j = 0; j<kolom; j++){
                                mTemp[i][j]=M.matrix[i][j];
                            }
                        }
                        System.out.printf("Determinan Matriks : %f", M.determinanOBE());
                        fromtoFile file = new fromtoFile();
                        if(file.confirmSave()){
                            file.detToFile(file.createFile(), mTemp, M.determinanOBE());
                        }
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        double[][] mtemp = file.fileToMat(filename);
                        primMatrix M = new primMatrix(mat, size[0], size[1]);
                        System.out.printf("Determinan Matriks : %f", M.determinanOBE());
                        if(file.confirmSave()){
                            file.detToFile(file.createFile(), mtemp, M.determinanOBE());
                        }
                    }
                }else if(optionDeterminan == 2){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        primMatrix M = new primMatrix(baris, kolom);
                        M.inputMat();
                        System.out.printf("Determinan Matriks : %f", M.determinanKofaktor(M));
                        fromtoFile file = new fromtoFile();
                        if(file.confirmSave()){
                            file.detToFile(file.createFile(), M.matrix, M.determinanKofaktor(M));
                        }
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        primMatrix M = new primMatrix(mat, size[0], size[1]);
                        System.out.printf("Determinan Matriks : %f", M.determinanKofaktor(M));
                        if(file.confirmSave()){
                            file.detToFile(file.createFile(), M.matrix, M.determinanKofaktor(M));
                        }
                    }
                }
            }else if(inputMain == 3){
                int optionInverse = getInverseOption();
                if(optionInverse == 1){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        MatrixInverse M = new MatrixInverse(baris, kolom);
                        M.inputMat();
                        double[][] mTemp = new double[baris][kolom]; //Supaya disimpan ke file txt, matriks utama masih ada
                        for(int i = 0; i<baris; i++){
                            for(int j = 0; j<kolom; j++){
                                mTemp[i][j]=M.matrix[i][j];
                            }
                        }
                        MatrixInverse Mtemp = new MatrixInverse(M.matrix, baris, kolom);
                        double det = M.determinanKofaktor(M);
                        if (det==0){
                            System.out.println("Matriks tidak memiliki invers.");
                        }else{
                            primMatrix tempMatrix = M.InversOBE();
                            tempMatrix.displayMatrix();
                            fromtoFile file = new fromtoFile();
                            if(file.confirmSave()){
                                file.matToFile(file.createFile(), Mtemp.matrix, tempMatrix.matrix);
                            }
                        }                       
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        double[][] matTemp = file.fileToMat(filename);
                        MatrixInverse M = new MatrixInverse(mat, size[0], size[1]);
                        double det = M.determinanKofaktor(M);
                        if(det == 0){
                            System.out.println("Matriks tidak memiliki invers.");
                        }else{
                            primMatrix tempMatrix = M.InversOBE();
                            tempMatrix.displayMatrix();
                            if(file.confirmSave()){
                                file.matToFile(file.createFile(), matTemp, tempMatrix.matrix);
                            }
                        }
                    }
                }else if(optionInverse == 2){
                    int inputMetode = metodeInput();
                    if(inputMetode == 1){
                        baris = inputBaris();
                        kolom = inputBaris();
                        MatrixInverse M = new MatrixInverse(baris, kolom);
                        M.inputMat();
                        double[][] mTemp = new double[baris][kolom];//Supaya disimpan ke file txt, matriks utama masih ada
                        for(int i = 0; i<baris; i++){
                            for(int j = 0; j<kolom; j++){
                                mTemp[i][j]=M.matrix[i][j];
                            }
                        }
                        double det = M.determinanKofaktor(M);
                        if(det == 0){
                            System.out.println("Matriks tidak memiliki invers.");
                        }else{
                            primMatrix tempMatrix = M.inversCofac();
                            tempMatrix.displayMatrix();
                            fromtoFile file = new fromtoFile();
                            if(file.confirmSave()){
                                file.matToFile(file.createFile(), mTemp, tempMatrix.matrix);
                            }
                        }
                    }else if(inputMetode == 2){
                        fromtoFile file = new fromtoFile();
                        String filename = file.infile();
                        int[] size = file.ukuranMat(filename);
                        double[][] mat = file.fileToMat(filename);
                        double[][] matTemp = file.fileToMat(filename);
                        MatrixInverse M = new MatrixInverse(mat, size[0], size[1]);
                        double det = M.determinanKofaktor(M);
                        if(det == 0){
                            System.out.println("Matriks tidak memiliki invers.");
                        }else{
                            primMatrix tempMatrix = M.inversCofac();
                            tempMatrix.displayMatrix();
                            if(file.confirmSave()){
                                file.matToFile(file.createFile(), matTemp, tempMatrix.matrix);
                            }
                        }
                    }
                }
            }else if(inputMain == 4){
                int inputMetode = metodeInput();
                if(inputMetode == 1){
                    baris = inputBaris();
                    kolom = inputKolom();
                    MatrixInterpolasi M = new MatrixInterpolasi(baris, kolom);
                    M.inputMat();
                    primMatrix has = M.matrixHasil();
                    double[] est = M.interpolasi();
                    fromtoFile file = new fromtoFile();
                    if(file.confirmSave()){
                        file.interToFile(file.createFile(),M.matrix, has.matrix, est);
                    }
                }else{
                    fromtoFile file = new fromtoFile();
                    String filename = file.infile();
                    int[] size = file.ukuranMat(filename);
                    double[][] mat = file.fileToMat(filename);
                    MatrixInterpolasi M  = new MatrixInterpolasi(mat, size[0], size[1]);
                    primMatrix temp = M.matrixHasil();
                    double[] est = M.interpolasi();
                    if(file.confirmSave()){
                        file.interToFile(file.createFile(),M.matrix, temp.matrix, est);
                    }
                }
            }else if(inputMain == 5){
                int inputMetode = metodeInput();
                if(inputMetode == 1){
                    baris = inputBaris();
                    kolom = inputKolom();
                    MatrixRegresi M = new MatrixRegresi(baris, kolom);
                    M.inputMat();
                    primMatrix temp = M.HasilReg();
                    double[] reg = M.estimasiReq();
                    fromtoFile file = new fromtoFile();
                    if(file.confirmSave()){
                        file.regToFile(file.createFile(),M.matrix, temp.matrix, reg);
                    }
                }else{
                    fromtoFile file = new fromtoFile();
                    String filename = file.infile();
                    int[] size = file.ukuranMat(filename);
                    double[][] mat = file.fileToMat(filename);
                    MatrixRegresi M  = new MatrixRegresi(mat, size[0], size[1]);
                    primMatrix temp = M.HasilReg();
                    double[] reg = M.estimasiReq();
                    if(file.confirmSave()){
                        file.regToFile(file.createFile(),M.matrix, temp.matrix, reg);
                    }
                }
            }else if(inputMain == 6){
                System.out.print("\033[H\033[2J"); 
                System.out.flush();  
            }
            if(inputMain != 6){
                System.out.printf("Tekan <Enter> untuk melanjutkan");
                System.in.read();
            }
        }while(inputMain != 6);
    }
    
    private static int getMainOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("SISTEM PERSAMAAN LINIER,DETERMINAN, DAN APLIKASINYA");
        System.out.println("===================================================");
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println("===================================================");
        System.out.printf("pilih [1-6]\t: ");
        i = input.nextInt();

        return i;
    }

    private static int getSPLOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.println("===================================================");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-JordaN");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("===================================================");
        System.out.printf("pilih [1-4]\t: ");
        i = input.nextInt();
        return i;
    }

    private static int getDeterminanOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("\u000C");
        System.out.println("DETERMINAN\n");
        System.out.println("===================================================");
        System.out.println("1. Reduksi Baris");
        System.out.println("2. Ekspansi Kofaktor");
        System.out.println("===================================================");
        System.out.printf("pilih [1-2]\t: ");
        i = input.nextInt();
        return i;
    }
    private static int getInverseOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("INVERSE\n");
        System.out.println("===================================================");
        System.out.println("1. Operasi Baris Elementer");
        System.out.println("2. Adjoin");
        System.out.println("===================================================");
        System.out.printf("pilih [1-2]\t: ");
        i = input.nextInt();
        return i;
    }

    static int metodeInput(){
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("\nPilih metode input");
        System.out.println("1. Input Console");
        System.out.println("2. Input file");
        System.out.printf("pilih [1-2]\t: ");
        i = input.nextInt();
        return i;
    }
    
    static int inputBaris(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Masukkan Banyak Baris : ");
        int brs = input.nextInt();
        return brs;
    }
    static int inputKolom(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Masukkan Banyak Kolom : ");
        int klm = input.nextInt();
        return klm;
    }
}
