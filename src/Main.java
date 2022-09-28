import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean onceAgain;
        Scanner scanner = new Scanner(System.in);
        do {
            int gameType;
            do {
                System.out.print("Hallo und guten Tag. An welcher Ziehung möchten Sie teilnehmen?\n" +
                        "1 für 6 aus 49\n" +
                        "2 für 5 aus 50\n");
                gameType = scanner.nextInt();
            } while (gameType < 1 || gameType > 2);
            Event event = new Event(gameType);
            System.out.println();

            System.out.printf("Willkommen zur heutigen Ziehung für %d aus %d.\n", event.getPrimaryNumberOfBets(), event.getPrimaryMax());

            int[] primaryBets = new int[event.getPrimaryNumberOfBets()];
            System.out.println("Bitte geben Sie Ihre Zahlen ein:");
            for (int i = 0; i < event.getPrimaryNumberOfBets(); i++) {
                primaryBets[i] = scanner.nextInt();
            }
            System.out.println("Bitte geben Sie Ihre Zusatzzahl" + (event.getSecondaryNumberOfBets() > 1 ? "en" : "") + " ein:");
            int[] secondaryBets = new int[event.getSecondaryNumberOfBets()];
            for (int i = 0; i < event.getSecondaryNumberOfBets(); i++) {
                secondaryBets[i] = scanner.nextInt();
            }
            System.out.println();

            System.out.println("Kommen wir nun zur Ziehung der heutigen Zahlen:");
            Thread.sleep(2000);
            System.out.println(event.display());
            System.out.println();
            Thread.sleep(1000);
            System.out.println(event.evaluation(primaryBets, secondaryBets));
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