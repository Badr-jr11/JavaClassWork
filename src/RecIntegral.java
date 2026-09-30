public class RecIntegral {

    private double upperLim;
    private double lowLim;
    private double step;
    private double result;

    public RecIntegral(double lowLim, double upperLim, double step) {
        this.lowLim = lowLim;
        this.upperLim = upperLim;
        this.step = step;
        this.result = 0.0;
    }

    public RecIntegral(double lowLim, double upperLim, double step, double result) {
        this.lowLim = lowLim;
        this.upperLim = upperLim;
        this.step = step;
        this.result = result;
    }

    public double getLowLim() { return lowLim; }
    public double getUpperLim() { return upperLim; }
    public double getStep() { return step; }
    public double getResult() { return result; }

    public void setLowLim(double lowLim) { this.lowLim = lowLim; }
    public void setUpperLim(double upperLim) { this.upperLim = upperLim; }
    public void setStep(double step) { this.step = step; }
    public void setResult(double result) { this.result = result; }

    public double f(double x) {
        return Math.sqrt(x);   // variant 6
    }

    public double CalcIntegral(double lowLim, double upLim, double step) {
        double start = lowLim, h, sumS = 0;

        do {
            h = Math.min(step, (upLim - start));
            sumS += h * (f(start) + f(start + h)) / 2;
            start += h;
        } while (start < upLim);

        return sumS;
    }
}