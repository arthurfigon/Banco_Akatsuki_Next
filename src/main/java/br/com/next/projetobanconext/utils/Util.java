package br.com.next.projetobanconext.utils;

import java.util.Scanner;

public class Util {

    public static int lerInt(String texto){
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        return scanner.nextInt();
    }

    public static String lerString(String texto){
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        return scanner.nextLine();
    }

    public static double lerDouble(String texto){
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        return scanner.nextDouble();
    }
}
