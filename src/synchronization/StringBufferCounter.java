package synchronization;

public class StringBufferCounter {
    private StringBuffer stringBuffer;

    public StringBufferCounter() {
        stringBuffer = new StringBuffer(50000);
    }

    public void increment() {
        stringBuffer.append('1');
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }
}
