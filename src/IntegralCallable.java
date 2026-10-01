import java.util.concurrent.Callable;

public class IntegralCallable implements Callable<Double> {

    private final RecIntegral rec;
    private final double from;
    private final double to;
    private final double step;

    public IntegralCallable(RecIntegral rec, double from, double to, double step) {
        this.rec = rec;
        this.from = from;
        this.to = to;
        this.step = step;
    }

    @Override
    public Double call() {
        // b3ks Runnable, Callable kat-rje3 l-résultat direct
        return rec.CalcIntegral(from, to, step);
    }
}