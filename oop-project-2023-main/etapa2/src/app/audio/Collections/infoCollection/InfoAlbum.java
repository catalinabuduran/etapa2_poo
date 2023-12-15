package app.audio.Collections.infoCollection;

import java.util.List;

public class InfoAlbum {
    private final String name;
    private final List<String> songsName;

    public InfoAlbum(final String name, final List<String> songs) {
        this.name = name;
        this.songsName = songs;
    }

    /**
     * Get name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get songs
     *
     * @return
     */
    public List<String> getSongs() {
        return songsName;
    }
}
