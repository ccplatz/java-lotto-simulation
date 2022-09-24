import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // TODO bring game base data to Event class
        int min = 1;
        int max = 50;
        int numberOfBets = 6;
        boolean onceAgain;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Hallo und guten Tag. An welcher Ziehung möchten Sie teilnehmen?\n" +
                    "1 für 6 aus 49\n" +
                    "2 für 5 aus 50\n");
            if (scanner.nextInt() == 2) {
                max = 51;
                numberOfBets = 5;
            }
            Event event = new Event(numberOfBets, min, max);
            System.out.println();

            System.out.printf("Willkommen zur heutigen Ziehung für %d aus %d.\n", numberOfBets, max);

            int[] bets = new int[numberOfBets];
            System.out.println("Bitte geben Sie Ihre Zahlen ein:");
            for (int i = 0; i < numberOfBets; i++) {
                bets[i] = scanner.nextInt();
            }
            System.out.println();

            System.out.println("Kommen wir nun zur Ziehung der heutigen Zahlen:");
            Thread.sleep(2000);
            int[] draw = event.draw();
            System.out.println(event.display());
            System.out.println();
            Thread.sleep(1000);
            System.out.println(event.evaluation(bets));
            System.out.println();

            Thread.sleep(2000);
            System.out.print("Möchten Sie erneut spielen?\n" +
                    "1 Erneute Runde\n" +
                    "2 Beenden\n");
            onceAgain = scanner.nextInt() == 1;
            if (!onceAgain) {
                System.out.println("Vielen Dank und bis bald!");
            }
        } while (onceAgain);
    }
}