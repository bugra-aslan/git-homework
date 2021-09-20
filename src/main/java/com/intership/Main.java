package com.intership;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


@Log4j2
public class Main {

    public static void infiniteLoop() {
        // This error is intentional
        // Because of some numerical analysis and binary magic the double values are not stored exactly as they are
        // Hence other than (1/2) * c values are not exact so in this counter the i will never be equal to 0.3
        // This is a critical mistake so it is needed to be fixed in the version r2.5.4.1
//        for (double i = 0.0; i != 0.3; i += 0.1) {
        for (int i = 0; i != 3; i++) {
            System.err.println("This loop continues to infinity!!!! nooot anymooar");
//            System.err.println("This loop continues to infinity!!!!");
        }
    }

    public static void notInfiniteLoop() {
        for (int i = 0; i < 3; i++) {
            System.out.println("This loop only iterates three times");
        }
    }

    public static void main(String[] args) {
        // This Main.java is getting longer, so it will be wise to divide it in the version r2.5.5
        String programRunCounterFileName = "ProgramRunCounter.counter";
        File programRunCounterFile = new File(programRunCounterFileName);
        int programRunCount = 0;


        try {
            if (!programRunCounterFile.createNewFile()) { // if file already exists will do nothing
                log.trace("File name: {} opened", programRunCounterFile);
                Scanner programRunCounterScanner = new Scanner(programRunCounterFile);
                if (programRunCounterScanner.hasNextInt())
                    programRunCount = programRunCounterScanner.nextInt();
            }

            // All the file io operations assumes that the program counter will not get too big.
            // So this is why integer is used, however it may get soo big. Change at version r3.0
            FileWriter programRunCounterFileWriter = new FileWriter(programRunCounterFile);
            PrintWriter printWriter = new PrintWriter(programRunCounterFileWriter);
            printWriter.printf("%d", ++programRunCount);
            printWriter.close();

            log.trace("The programRunCount before loops {}", programRunCount);

            if (programRunCount % 7 == 0) {
                infiniteLoop();
            } else {
                notInfiniteLoop();
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("File could not opened: {}", e.toString());
        }
    }
}
