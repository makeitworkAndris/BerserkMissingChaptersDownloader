package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main2 {
    static int startNumber = 307;
    //376 is the latest in: 2024-05-01
    static int endNumber = 376;
    static int attemptPagesPerChapter = 100;
    static String path = "C:\\Users\\Administrator\\Pictures\\Berserk_Chapters";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers corresponding to the following instruction, than press enter.");
        System.out.println("Starting Chapter: (recommended to input 307, because that is missing from the download page)");
        startNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Input last chapter to download (recommended to input 376 -> last chapter in 2024-05-01 YYYY/MM/DD)");
        endNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Input number of images to download (default 100)");
        attemptPagesPerChapter = Integer.parseInt(sc.nextLine());
        System.out.println("Input path to download to:");
        path = sc.nextLine();
    }
}
