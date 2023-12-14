package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.SongInput;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Album
 */
public class Album extends AudioCollection {
    private String name;
    private int releaseYear;
    private String description;
    private List<SongInput> songs;
    /**
     * Get number of tracks
     *
     * @return
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * Get track by index
     *
     * @param index the index
     * @return
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        ArrayList<Song> songsConverted = new ArrayList<Song>();
        for (SongInput songInput : songs) {
            songsConverted.add(new Song(songInput.getName(),
                    songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
        if (index >= 0 && index < songsConverted.size()) {
            return songsConverted.get(index);
        } else {
            return null;
        }
    }

    /**
     * Constructor
     *
     * @param name
     * @param owner
     * @param releaseYear
     * @param description
     * @param songs
     */
    public Album(final String name, final String owner, final int releaseYear,
                 final String description, final List<SongInput> songs) {
        super(name, owner);
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = songs;
    }

    /**
     * Another constructor
     *
     * @param nameAlbum
     * @param owner
     */
    public Album(final String nameAlbum, final String owner) {
        super(nameAlbum, owner);

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
     * Set name
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get release year
     *
     * @return
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Set release year
     *
     * @param releaseYear
     */
    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Get description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     *
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get songs
     *
     * @return
     */
    public List<SongInput> getSongs() {
        return songs;
    }

    /**
     * Set songs
     *
     * @param songs
     */
    public void setSongs(final List<SongInput> songs) {
        this.songs = songs;
    }

    /**
     * Song type song
     *
     * @param songsAlbums
     * @return
     */
    public List<Song> songTypeSong(final List<SongInput> songsAlbums) {
        List<Song> songsTypeSong = new ArrayList<>();
        for (SongInput songInput : songsAlbums) {
            Song song1 = new Song(songInput.getName(),
                    songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist());
            songsTypeSong.add(song1);
        }
        return songsTypeSong;
    }

    /**
     * Get total likes
     * @return
     */
    public int getTotalLikes() {
        int totalLikes = 0;
        List<Song> songs1 = songTypeSong(this.songs);
        for (Song song : songs1) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
