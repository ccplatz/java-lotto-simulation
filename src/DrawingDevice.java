import java.util.Arrays;
import java.util.Random;

public class DrawingDevice {
    private final int min;
    private final int max;
    private int[] drawnBalls = {};

    public DrawingDevice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int drawBall() {
        int newBall;
        do {
            newBall = new Random().nextInt(max + min) + min;
        } while (drawnBallsContain(newBall));

        addToDrawnBalls(newBall);
        return newBall;
    }

    public void reset() {
        Arrays.fill(drawnBalls, 0);
    }

    private boolean drawnBallsContain(int ball) {
        boolean contains = false;
        for (int drawnBall : drawnBalls) {
            if (ball == drawnBall) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private void addToDrawnBalls(int ball) {
        int[] arr = Arrays.copyOf(drawnBalls, drawnBalls.length + 1);
        arr[arr.length - 1] = ball;
        drawnBalls = arr;
    }
}
