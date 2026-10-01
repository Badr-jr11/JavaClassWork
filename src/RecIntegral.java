import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class RecIntegral implements Externalizable {

    private static final long serialVersionUID = 1L;

    private static final double MIN_VALUE = 0.000001;
    private static final double MAX_VALUE = 1000000.0;

    private double upperLim;
    private double lowLim;
    private double step;
    private double result;

    // darori l Externalizable: constructeur khawi o public
    public RecIntegral() {
    }

    public RecIntegral(double lowLim, double upperLim, double step) throws InvalidRangeException {
        validateRange(lowLim);
        validateRange(upperLim);
        validateRange(step);
        validateLimits(lowLim, upperLim);

        this.lowLim = lowLim;
        this.upperLim = upperLim;
        this.step = step;
        this.result = 0.0;
    }

    public RecIntegral(double lowLim, double upperLim, double step, double result) throws InvalidRangeException {
        validateRange(lowLim);
        validateRange(upperLim);
        validateRange(step);
        validateLimits(lowLim, upperLim);

        this.lowLim = lowLim;
        this.upperLim = upperLim;
        this.step = step;
        this.result = result;
    }

    private void validateRange(double value) throws InvalidRangeException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new InvalidRangeException(
                "The data value must be in the range from " +
                MIN_VALUE + " to " + MAX_VALUE + "\n", value);
        }
    }

    private void validateLimits(double lowLim, double upperLim) throws InvalidRangeException {
        if (lowLim > upperLim) {
            throw new InvalidRangeException(
                "The lower limit (" + lowLim + ") must not be greater than the upper limit ("
                + upperLim + ")\n", lowLim);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(lowLim);
        out.writeDouble(upperLim);
        out.writeDouble(step);
        out.writeDouble(result);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        lowLim = in.readDouble();
        upperLim = in.readDouble();
        step = in.readDouble();
        result = in.readDouble();
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