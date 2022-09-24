import java.util.Arrays;

public class Event {
    int numberOfBets;
    int min;
    int max;
    DrawingDevice primaryDrawingDevice;

    public Event(int numberOfBets, int min, int max) {
        this.numberOfBets = numberOfBets;
        this.min = min;
        this.max = max;
        primaryDrawingDevice = new DrawingDevice(this.min, this.max);
        // TODO add secondary drawing device
    }

    public int[] draw() {
        int[] draw = new int[numberOfBets];
        for (int i = 0; i < numberOfBets; i++) {
            draw[i] = primaryDrawingDevice.drawBall();
        }

        // TODO check why return draw here
        return draw;
    }

    public String display() {
        String display = "";
        int[] drawnBalls = primaryDrawingDevice.getDrawnBalls();

        for (int drawnBall : drawnBalls) {
            display += "[" + drawnBall + "] ";
        }

        return display;
    }

    public String evaluation(int[] bets) {
        int[] correctBets = {};
        for (int bet : bets) {
            int[] arr = Arrays.copyOf(correctBets, correctBets.length + 1);
            for (int number : primaryDrawingDevice.getDrawnBalls()) {
                if (bet == number) {
                    arr[arr.length - 1] = bet;
                    correctBets = arr;
                    break;
                }
            }
        }

        String evaluation = "Sie haben " + correctBets.length + " " + (correctBets.length == 1 ? "Zahl" : "Zahlen") + " richtig getippt";
        if (correctBets.length > 0) {
            evaluation += ": ";
            for (int correctBet : correctBets) {
                evaluation += "[" + correctBet + "] ";
            }
            evaluation += "\nHerzlichen Glückwunsch!";
        } else evaluation += ".";

        primaryDrawingDevice.reset();
        // TODO further actions to reset?

        return evaluation;
    }
}
