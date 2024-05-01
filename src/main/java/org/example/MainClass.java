package org.example;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainClass {

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
        System.out.println("EXAMPLE: C:\\\\Users\\\\Administrator\\\\Pictures\\\\Berserk_Chapters");
        path = sc.nextLine();

        if (startNumber <= endNumber) {
            downloadChapters(false, "");
            if (endNumber > 363) {
                downloadChapters(true, "3645");
            }
        }
    }

    public static void downloadChapters(boolean specialNumber, String specialChapter) {
        //Handling the chapter 364.5 chapter (they are in different format too)
        if (!specialNumber) {
            String currentChapter;
            try {
                for (int i = startNumber; i <= endNumber; i++) {
                    currentChapter = "https://cdn.readberserk.com/file/mangap/1/20" + i + "000/";
                    System.out.println(currentChapter);
                    //Define path
                    String folder = "Chapter-" + i;
                    File dir = new File(path + "\\" + folder);
                    //Downloads the missing chapters
                    if (!dir.exists()) {
                        for (int page = 1; page <= attemptPagesPerChapter; page++) {
                            try {
                                InputStream in = new URL(currentChapter + page + ".jpg").openStream();
                                //SavePath + / + ChapterFolder + / + Berserk + currentChapterNumber + page
                                Files.copy(in, Paths.get(path + "\\" + folder + "\\" + "Berserk-" + i + "-" + page + ".jpg"));

                            } catch (FileNotFoundException e) {
                                break;
                            } catch (FileAlreadyExistsException ignored) {
                            }
                        }
                    }
                }
            } catch (IOException ignored) {
                System.out.println("Something failed");
            }
        } else {
            String currentChapter = "https://cdn.readberserk.com/file/mangap/1/20" + specialChapter + "00/";
            System.out.println(specialChapter);
            //Define path
            String folder = "Chapter-" + specialChapter;
            File dir = new File(path + "\\" + folder);
            //Downloads the missing chapters
            if (!dir.exists()) {
                dir.mkdir();
                for (int page = 1; page <= attemptPagesPerChapter; page++) {
                    try {
                        InputStream in = new URL(currentChapter + page + ".jpeg").openStream();
                        //SavePath + / + ChapterFolder + / + Berserk + currentChapterNumber + page
                        Files.copy(in, Paths.get(path + "\\" + folder + "\\" + "Berserk-" + specialChapter + "-" + page + ".jpeg"));
                        System.out.println(Files.copy(in, Paths.get(path + "\\" + folder + "\\" + "Berserk-" + specialChapter + "-" + page + ".jpeg")));

                    } catch (FileNotFoundException e) {
                        break;
                    } catch (FileAlreadyExistsException ignored) {
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}