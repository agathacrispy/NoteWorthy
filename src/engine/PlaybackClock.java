package engine;

public class PlaybackClock {
    private long startNano = -1;

    public void start() {
        startNano = System.nanoTime();
    }

    public void reset() {
        startNano = -1;
    }

    public long elapsedMs() {
        if (startNano == -1) return 0;
        return (System.nanoTime() - startNano) / 1_000_000;
    }
}
