package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    static int startNumber = 307;
    //376 is the latest in: 2024-05-01
    static int endNumber = 347;
    static int attemptPagesPerChapter = 100;
    static String path = "C:\\Users\\Administrator\\Pictures\\Berserk_Chapters";

    public static void main(String[] args) {
        downloadChapters();
    }

    public static void downloadChapters() {
        try {
            for (int i = startNumber; i <= endNumber; i++) {
                String currentChapter = "https://cdn.readberserk.com/file/mangap/1/20" + i + "000/";

                System.out.println(currentChapter);
                //Define path
                String folder = "Chapter-" + i;
                File dir = new File(path + "\\" + folder);
                //Downloads the missing chapters
                if (!dir.exists()) {
                    dir.mkdirs();
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
    }
}