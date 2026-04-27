package audio;

public class AudioInputManager implements AudioInput {
    @Override
    public float[] readFrame() {
        return new float[0];
    }

    @Override
    public void close() {
    }
}
