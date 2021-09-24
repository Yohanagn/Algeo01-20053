package com.tubes;
import java.io.*;
import java.util.*;


public class fromtoFile {

    public static String letak(){
        return System.getProperty("user.dir") + "\\docs\\testCase";
    }

    public static String saveLetak(){
        return System.getProperty("user.dir") + "\\docs\\hasil";
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

    public static double[][] fileToMat(String filename){
        File file = new File(letak() +"\\"+ filename);
        int[] ukuran = ukuranMat(filename);
        int row = ukuran[0];
        int col = ukuran[1];
        double[][] toMat = new double[row][col];

        try{
            Scanner matrix = new Scanner(file);

            int i,j;
            for(i = 0; i<row; i++){
                for(j = 0; j<col; j++){
                    if(matrix.hasNextDouble()){
                        toMat[i][j] = matrix.nextDouble();
                    }
                }
            }
        } catch (FileNotFoundException e){

        }
        return toMat;
    }


    public static boolean confirmSave(){
        boolean save = false;
        int pilihan;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("====Save====");
            System.out.println("1. Ya");
            System.out.println("2.Tidak");
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

    public static void matToFile(String filename, double[][] matrix){
        try{
            FileWriter newFile = new FileWriter(saveLetak()+"\\"+filename);
            for (double[] row : matrix){
                for(double el : row){
                    newFile.write(el + " ");
                }
                newFile.write("\n");
            }
            newFile.close();
        }catch (IOException e){

        }
    }
}
