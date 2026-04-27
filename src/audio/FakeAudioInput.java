package audio;

// NOTE: DO NOT DELETE
// IMPORTANT FOR TESTING!!!

public class FakeAudioInput implements AudioInput {
    @Override
    public float[] readFrame() {
        return new float[0];
    }

    @Override
    public void close() {
    }
}
