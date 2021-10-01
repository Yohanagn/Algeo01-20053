package com.tubes;
import java.io.*;
import java.util.*;


public class fromtoFile {

    public static String letak(){
        return System.getProperty("user.dir") + "\\test\\testCase";
    }

    public static String saveLetak(){
        return System.getProperty("user.dir") + "\\test\\hasil";
    }

    public static boolean thereAreFile(String filename){
        File file = new File(letak() + "\\" + filename);
        try{
            Scanner scan = new Scanner(file);
            return true;
        } catch (FileNotFoundException e){
            return false;
        }
    }
    
    public static String infile(){
        Scanner scan = new Scanner(System.in);
        String file;
        do{

            System.out.print(("Masukkan nama file : "));
            file = scan.nextLine();
            if (!thereAreFile(file)){
                System.out.println("Nama File tidak ditemukan");
            }
        }while(!thereAreFile(file));
        return file;
    }

    // Mencari Ukuran Matriks
    public static int[] ukuranMat(String filename){
        int row = 0;
        int col = 0;
        int sumElmt = 0 ;
        int[] size = new int[2];

        File file = new File(letak() + "\\" + filename);

        try{
            Scanner rowfile = new Scanner(file);
            while (rowfile.hasNextLine()){
                row+=1;
                rowfile.nextLine();
            }
            Scanner sumElm = new Scanner(file);
            while (sumElm.hasNextDouble()){
                sumElmt +=1;
                sumElm.nextDouble();
            }

            col = sumElmt/row;
        } catch (FileNotFoundException e){
            
        }
        size[0] = row;
        size[1] = col;
        return size;
    }

    //Membuat file txt menjadi Matrix
    public static double[][] fileToMat(String filename){
        File file = new File(letak() +"\\"+ filename);
        int[] ukuran = ukuranMat(filename);
        int row = ukuran[0];
        int col = ukuran[1];
        double[][] Mat = new double[row][col];

        try{
            Scanner matrix = new Scanner(file);

            int i,j;
            for(i = 0; i<row; i++){
                for(j = 0; j<col; j++){
                    if(matrix.hasNextDouble()){
                        Mat[i][j] = matrix.nextDouble();
                    }
                }
            }
        } catch (FileNotFoundException e){

        }
        return Mat;
    }

    // save ke txt
    public static boolean confirmSave(){
        boolean save = false;
        int pilihan;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("\n====Save====");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            System.out.print("==> ");
            pilihan = scan.nextInt();
            if (pilihan == 1 ){
                save =true; 
            }else if(pilihan == 2){
                save = false;
            }else{
                System.out.println("Masukkan Anda salah.");
            }
        }while (!(pilihan == 1 || pilihan ==2 ));
        return save;
    }

    //Membuat file baru dan mengecek apakah file dengan nama tersebut sudah ada atau tidak
    public static String createFile(){
        String filename;
        File file;
        Scanner scan = new Scanner(System.in);
        boolean fileexist = false;

        do{
            System.out.print("Masukkan nama file baru : ");
            filename = scan.nextLine();
            try{
                file = new File(saveLetak() + "\\" + filename);
                if (file.createNewFile()){
                    fileexist = true;
                } else{
                    System.out.println("Nama yang Anda masukkkan telah ada. Coba masukkan nama yang baru");
                }
            }catch (IOException e){

            }
        } while(!fileexist);
        return filename;
    }
    

    // Membuat Matriks menjadi file --> Dipakai di matriks Balikan
    public static void matToFile(String filename,double[][] matrix, double[][] has){
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.write("\nHasil Invers : \n");
            for (double[] row : has){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.close();
        }catch (IOException e){

        }
    }

    public static void detToFile(String filename, double[][] matrix, double det){
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.write("\nDeterminan dari Matriks adalah" + det);
            newFile.close();
        }catch (IOException e){

        }
    }


    //SPL yang memiliki nilai tunggal --> Hanya dipakai untuk Cramer dan Metode Matriks Balikan
    public static void splToFile(String filename, double[][]matrix, String[] spl){
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.write("\nHasil persamaan : \n");
            int i = 0;
            while(i<spl.length && spl[i]!=null){
                    newFile.write(spl[i]);
                    newFile.write("\n");
                i++;
            }
            newFile.close();
        }catch (IOException e){

        }
    }

    //Interpolasi Ke File
    public static void interToFile(String filename,double[][] matrix, double[][] has, double[] est){
        int idxcol = has[0].length-1;
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.write("\nPersamaan Interpolasi : ");
            for(int i = 0; i<has.length; i++){
                if (i==0){
                    newFile.write("" + has[i][idxcol]);
                }else{
                    if (has[i][idxcol] < 0){
                        newFile.write(" "+ has[i][idxcol]+ "X^("+i+")");
                    }else{
                        newFile.write(" +"+ has[i][idxcol]+ "X^("+i+")");
                    }
                }
            }
            newFile.write("\n\n");
            newFile.write("Hasil Estimasi X= " + est[0] + " adalah " + est[1]);
            newFile.close();
        }catch (IOException e){

        }
    }


    //Regresi Ke File
    public static void regToFile(String filename,double[][] matrix, double[][] has, double[] req){
        int idxcol = has[0].length-1;
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.write("\nPersamaan Regresi : ");
            for(int i = 0; i<has.length; i++){
                if (i==0){
                    newFile.write("" + has[i][idxcol]);
                }else{
                    if (has[i][idxcol] < 0){
                        newFile.write(" "+ has[i][idxcol]+ "X"+i);
                    }else{
                        newFile.write(" +"+ has[i][idxcol]+ "X"+i);
                    }
                }
            }
            newFile.write("\n\n");
            newFile.write("Hasil Regresi dengan ");
            for(int i = 0; i<req.length; i++){
                if(i == req.length-1){
                    newFile.write("adalah " + req[i]);
                }else{
                    newFile.write("X"+(i+1)+"= "+req[i]+" ");
                }
            }
            newFile.close();
        }catch (IOException e){

        }
    }
}
    
