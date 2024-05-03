package synchronization;

public class StringBuilderCounter {
    private StringBuilder stringBuilder;

    public StringBuilderCounter() {
        stringBuilder = new StringBuilder(50000);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void increment() {
        stringBuilder.append('1');
    }
}
