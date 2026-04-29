package loader;

import model.LyricLine;
import model.PitchFrame;
import model.Song;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class SongLoader {
    ArrayList<LyricLine> lyrics = new ArrayList<LyricLine>();

    public void loadLyrics() {
        try
        {
            FileReader fr = new FileReader("songs/dangerous-woman/lyrics.lrc");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null)
            {
                Pattern pattern = Pattern.compile("\\[(\\d{2}):(\\d{2})\\.(\\d{2})\\](.*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    long minutes = Long.parseLong(matcher.group(1));
                    long seconds = Long.parseLong(matcher.group(2));
                    long centiseconds = Long.parseLong(matcher.group(3));
                    String text = matcher.group(4).trim();
                    long startMs = minutes * 60000 + seconds * 1000 + centiseconds * 10;
                    if (lyrics != null) { lyrics.add(new LyricLine(text, startMs)); }
                }

                for (LyricLine ll : lyrics) {
                    System.out.println(ll.getLine());
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("can't read");
        }

    }

}
