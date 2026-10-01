public class IntegralTask implements Runnable {

    private final RecIntegral rec;
    private final double from;
    private final double to;
    private final double step;
    private double result;

    public IntegralTask(RecIntegral rec, double from, double to, double step) {
        this.rec = rec;
        this.from = from;
        this.to = to;
        this.step = step;
    }

    @Override
    public void run() {
        // kol thread kay7seb ghi l-partie dyalo [from, to]
        result = rec.CalcIntegral(from, to, step);
    }

    public double getResult() {
        return result;
    }
}