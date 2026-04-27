package audio;

public interface AudioInput {
    float[] readFrame();
    void close();
}
