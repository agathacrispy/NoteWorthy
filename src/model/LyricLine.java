package model;


/*
1. Flesh out LyricLine — add a constructor and getters for text, startMs, endMs. It's just a data class.

2. Implement SongLoader.loadLyrics() — parse the .lrc file into a List<LyricLine>. Each line like [00:01.50]Never gonna give you up becomes a LyricLine with startMs=1500. The endMs of each line is the startMs of the next.

3. Implement PlaybackClock — add start() (records start time via System.nanoTime()), reset(), and elapsedMs() (returns milliseconds since start).

4. Implement LyricsSync.getCurrentLine() — iterates the lyric list and returns the LyricLine whose startMs <= elapsedMs < endMs. Also return the next line for the dimmed preview.

5. Implement GameplayPanel — add a setLines(LyricLine current, LyricLine next) method that stores the lines and calls repaint(). Override paintComponent() to draw the current line large and centered, next line smaller and dimmed below it.

6. Wire the game loop in KaraokeGame — a background thread that ticks every ~50ms, calls LyricsSync, and pushes lines to GameplayPanel via SwingUtilities.invokeLater().
 */
public class LyricLine {
    private String text;
    private long startMs;
    public LyricLine(String text, long startMs) {
        this.text = text;
        this.startMs = startMs;
    }

    public String getLine() { return text; }
    public long getStartMs() { return startMs; };
}
