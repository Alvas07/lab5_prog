package managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class ScriptManager {
    private static final Stack<String> fileNames = new Stack<>();
    private static final Stack<Scanner> scanners = new Stack<>();

    public static boolean isRecursive(String fileName) {
        return fileNames.contains(new File(fileName).getAbsolutePath());
    }

    public static void addPath(String fileName) throws FileNotFoundException {
        fileNames.push(new File(fileName).getAbsolutePath());
        scanners.push(new Scanner(new File(fileName)));
    }

    public static void removePath() {
        fileNames.pop();
        scanners.pop();
    }

    public static Scanner getLastScanner() {
        return scanners.lastElement();
    }
}
