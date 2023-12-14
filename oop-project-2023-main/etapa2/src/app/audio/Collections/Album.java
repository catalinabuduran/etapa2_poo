package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.SongInput;


import java.util.*;

public class Album extends AudioCollection {
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

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

    private String name;
    private int releaseYear;
    private String description;
    private List<SongInput> songs;


    public Album(final String name, final String owner, final int releaseYear,
                 final String description, final List<SongInput> songs) {
        super(name, owner);
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.songs = songs;
    }
    public Album(final String nameAlbum, final String owner) {
        super(nameAlbum, owner);

    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<SongInput> getSongs() {
        return songs;
    }
    public void setSongs(final List<SongInput> songs) {
        this.songs = songs;
    }
    public List<Song> songTypeSong(final List<SongInput> songs) {
        List<Song> songsTypeSong = new ArrayList<>();
        for (SongInput songInput : songs) {
            Song song1 = new Song(songInput.getName(),
                    songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist());
            songsTypeSong.add(song1);
        }
        return songsTypeSong;
    }

    public int getTotalLikes() {
        int totalLikes = 0;
        List<Song> songs1 = songTypeSong(this.songs);
        for (Song song : songs1) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
