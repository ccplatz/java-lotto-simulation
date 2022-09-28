import java.util.Arrays;

public class Event {
    private int primaryNumberOfBets;
    private int primaryMax;
    private int secondaryMax;
    private int secondaryNumberOfBets;
    private final DrawingDevice primaryDrawingDevice;
    private final DrawingDevice secondaryDrawingDevice;
    private int[] primaryDrawnBalls;
    private int[] secondaryDrawnBalls;

    public Event(int gameType) {
        if (gameType == 1) {
            primaryMax = 50;
            primaryNumberOfBets = 6;
            secondaryMax = 10;
            secondaryNumberOfBets = 1;
        } else if (gameType == 2) {
            primaryMax = 51;
            primaryNumberOfBets = 5;
            secondaryMax = 13;
            secondaryNumberOfBets = 2;
        }
        int min = 1;
        primaryDrawingDevice = new DrawingDevice(min, primaryMax);
        secondaryDrawingDevice = new DrawingDevice(min, secondaryMax);
    }

    public int getPrimaryNumberOfBets() {
        return primaryNumberOfBets;
    }

    public int getPrimaryMax() {
        return primaryMax;
    }

    public int getSecondaryNumberOfBets() {
        return secondaryNumberOfBets;
    }

    public int[] draw(int numberOfBets, DrawingDevice drawingDevice) {
        int[] draw = new int[numberOfBets];
        for (int i = 0; i < numberOfBets; i++) {
            draw[i] = drawingDevice.drawBall();
        }

        return draw;
    }

    public String display() {
        String display = "";
        primaryDrawnBalls = this.draw(primaryNumberOfBets, primaryDrawingDevice);
        secondaryDrawnBalls = this.draw(secondaryNumberOfBets, secondaryDrawingDevice);

        for (int drawnBall : primaryDrawnBalls) {
            display += "[" + drawnBall + "] ";
        }
        for (int drawnBall : secondaryDrawnBalls) {
            display += "[" + drawnBall + "]* ";
        }

        return display;
    }

    public String evaluation(int[] primaryBets, int[] secondaryBets) {
        int[] correctPrimaryBets = checkForCorrectBets(primaryBets, primaryDrawnBalls);
        int[] correctSecondaryBets = checkForCorrectBets(secondaryBets, secondaryDrawnBalls);

        String evaluation = "Sie haben " + correctPrimaryBets.length + " " + (correctPrimaryBets.length == 1 ? "Zahl" : "Zahlen") + " richtig getippt";
        if (correctPrimaryBets.length > 0) {
            evaluation += ": ";
            for (int correctBet : correctPrimaryBets) {
                evaluation += "[" + correctBet + "] ";
            }
            evaluation += "\nSie haben " + correctSecondaryBets.length + " " + (correctSecondaryBets.length == 1 ? "Zusatzahl" : "Zusatzahlen") + " richtig.";
            evaluation += "\nHerzlichen Glückwunsch!";
        } else evaluation += ".";

        primaryDrawingDevice.reset();
        secondaryDrawingDevice.reset();

        return evaluation;
    }

    private int[] checkForCorrectBets(int[] bets, int[] drawnBalls) {
        int[] correctBets = {};
        for (int bet : bets) {
            int[] arr = Arrays.copyOf(correctBets, correctBets.length + 1);
            for (int drawnBall : drawnBalls) {
                if (bet == drawnBall) {
                    arr[arr.length - 1] = bet;
                    correctBets = arr;
                    break;
                }
            }
        }

        return correctBets;
    }
}
