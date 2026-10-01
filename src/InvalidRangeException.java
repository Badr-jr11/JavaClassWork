public class InvalidRangeException extends Exception {

    private double errVal;

    public InvalidRangeException(String message) {
        super(message);
    }

    public InvalidRangeException(String message, double num) {
        super(message);
        errVal = num;
    }

    public double getErrVal() {
        return errVal;
    }
}