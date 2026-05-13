import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Wilderness Survival System ===");

        System.out.print("Enter map width: ");
        int width = scanner.nextInt();

        System.out.print("Enter map height: ");
        int height = scanner.nextInt();

        System.out.println("Select difficulty: 1) Easy  2) Medium  3) Hard");
        int difficulty = scanner.nextInt();

        System.out.println("Select Vision type: 1) CautiousVision  2) FarSightVision 3)FoucsedVision 4)KeenEyedVision");
        int visionChoice = scanner.nextInt();

        System.out.println("Select Brain type: 1) AggressiveEast  2) BalancedBrain");
        int brainChoice = scanner.nextInt();

        GameManager game = new GameManager();
        game.initialize(width, height, difficulty, visionChoice, brainChoice);
        game.run();
    }
}