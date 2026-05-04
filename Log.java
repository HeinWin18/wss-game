// public class Log{
//     public static void info(String message) {
//         System.out.println("[INFO] " + message);
//     }

//     public static void methodStart(String className, String methodName, String inputs){
//         System.out.println("[METHOD START] " + className + "." + methodName + " called with inputs: " + inputs);
//     }

//     public static void methodEnd(String className, String methodName, String outputs){
//         System.out.println("[METHOD END] " + className + "." + methodName + " returned: " + outputs);
//     }

//     public static void error(String message) {
//         System.out.println("[ERROR] " + message);
//     }
// }

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileWriter("game_log.txt", false), true);
        } catch (IOException e) {
            System.out.println("CRITICAL: Failed to create log file!");
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        if (writer != null) writer.println("[INFO] " + message);
    }

    public static void question(String message){
        if (writer != null) writer.println("[QUESTION] " + message);
    }

    public static void methodStart(String className, String methodName, String inputs){
        if (writer != null) writer.println("[METHOD START] " + className + "." + methodName + " called with inputs: " + inputs);
    }

    public static void methodEnd(String className, String methodName, String outputs){
        if (writer != null) writer.println("[METHOD END] " + className + "." + methodName + " returned: " + outputs);
    }

    public static void error(String message) {
        if (writer != null) writer.println("[ERROR] " + message);
    }

    public static void close() {
        if (writer != null) {
            writer.close();
        }
    }
}