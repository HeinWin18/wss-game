public class Log{
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void methodStart(String className, String methodName, String inputs){
        System.out.println("[METHOD START] " + className + "." + methodName + " called with inputs: " + inputs);
    }

    public static void methodEnd(String className, String methodName, String outputs){
        System.out.println("[METHOD END] " + className + "." + methodName + " returned: " + outputs);
    }

    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}