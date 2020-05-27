package thread;

public class ImportEvaluationTimer extends Thread {
    @Override
    public void run() {
        try {
            sleep(10000);
            System.out.println("Valutazioni importate correttamente dal database");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
