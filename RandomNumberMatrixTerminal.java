import java.util.Random;

public class RandomNumberMatrixTerminal implements Runnable {

    private boolean running;

    public RandomNumberMatrixTerminal() {
        running = true;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                printRandomNumberMatrix();
            }
            sleep(1); // Adjust delay to 1 millisecond
        }
    }

    private void printRandomNumberMatrix() {
        Random random = new Random();
        clearScreen(); // Clear the terminal screen
        System.out.println("Random Number Matrix (50x50):");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 10000; j++) {
                int randomNumber = random.nextInt(100); // Generate a random number between 0 and 99
                sb.append(String.format("%3d ", randomNumber)); // Append number right-aligned within 3 spaces
            }
            sb.append("\n"); // Move to the next line after each row
        }
        System.out.print(sb.toString()); // Print the entire matrix at once
        System.out.println("Press Enter to toggle running state...");
    }

    private void clearScreen() {
        System.out.print("\u001b[2J\u001b[H"); // ANSI escape sequence to clear screen
        System.out.flush();
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void toggleRunning() {
        running = !running;
    }

    public static void main(String[] args) {
        RandomNumberMatrixTerminal matrix = new RandomNumberMatrixTerminal();
        Thread thread = new Thread(matrix);
        thread.start();

        // Read input to toggle running state
        try {
            while (true) {
                System.in.read(); // Wait for Enter key press
                matrix.toggleRunning(); // Toggle running state
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

