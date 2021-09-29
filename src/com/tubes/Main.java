package com.tubes;
import java.util.Scanner;

public class Main { 
    public static void main(String[] args) {
        int inputMain;
        do{
            inputMain =  getMainOption();
            if(inputMain == 1){
                int optionSPL = getSPLOption();
                if(optionSPL == 1){
                    int inputMethod = metodeInput();
                }else if(optionSPL == 2){
                    int inputMethod = metodeInput();
                }else if(optionSPL == 3){
                    int inputMethod = metodeInput();
                }else if(optionSPL == 4){
                    int inputMethod = metodeInput();
                }
            }else if(inputMain == 2){
                int optionDeterminan = getDeterminanOption();
                if(optionDeterminan == 1){
                    int inputMethod = metodeInput();
                }else if(optionDeterminan == 2){
                    int inputMethod = metodeInput();
                }
            }else if(inputMain == 3){
                int optionInverse = getInverseOption();
                if(optionInverse == 1){
                    int inputMethod = metodeInput();
                }else if(optionInverse == 2){
                    int inputMethod = metodeInput();
                }
            }else if(inputMain == 4){
                int inputMethod = metodeInput();
            }else if(inputMain == 5){
                int inputMethod = metodeInput();
            }else if(inputMain == 6){
                System.out.print("\033[H\033[2J"); 
                System.out.flush();  
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
        input.close();
        return i;
    }

    private static int getSPLOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("SISTEM PERSAMAAN LINIER\n");
        System.out.println("===================================================");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-JordaN");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("===================================================");
        System.out.printf("pilih [1-4]\t: ");
        i = input.nextInt();
        input.close();
        return i;
    }

    private static int getDeterminanOption(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("DETERMINAN\n");
        System.out.println("===================================================");
        System.out.println("1. Reduksi Baris");
        System.out.println("2. Ekspansi Kofaktor");
        System.out.println("===================================================");
        System.out.printf("pilih [1-2]\t: ");
        i = input.nextInt();
        input.close();
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
        input.close();
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
        input.close();
        return i;
    }
}
