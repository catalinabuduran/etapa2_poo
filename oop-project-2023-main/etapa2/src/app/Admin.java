package app;

import app.audio.Collections.Podcast;
import app.audio.Collections.Album;
import app.audio.Collections.infoCollection.InfoPodcast;
import app.audio.Collections.Playlist;
import app.audio.Collections.infoCollection.InfoAlbum;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;
import fileio.input.PodcastInput;
import fileio.input.UserInput;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public final class Admin {
    @Getter
    private List<User> users = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();
    private List<Podcast> podcasts = new ArrayList<>();
    private List<Episode> episodes = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();
    private static Admin instance;
    private int MAGIC_NUMBER = 5;
    private int timestamp = 0;

    /**
     *
     * @return
     */
    public static synchronized Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    /**
     * Constructor
     *
     */
    private Admin() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }
    /**
     * Set albums
     *
     * @param albums
     */
    public void setAlbums(final List<Album> albums) {
        Admin admin = Admin.getInstance();
        admin.albums = albums;
    }

    /**
     * Show podcasts
     *
     * @param username
     * @return
     */
    public List<InfoPodcast> showPodcasts(final String username) {
        Admin admin = Admin.getInstance();
        Host host = (Host) admin.getUser(username);
        List<InfoPodcast> podcastsInfoList = new ArrayList<>();
        List<Podcast> hostPodcasts = host.getPodcasts();

        for (Podcast podcast : hostPodcasts) {
            List<String> episodesNames = new ArrayList<>();
            for (Episode episode : podcast.getEpisodes()) {
                episodesNames.add(episode.getName());
            }

            InfoPodcast podcastInfo = new InfoPodcast(podcast.getName(), episodesNames);
            podcastsInfoList.add(podcastInfo);
        }

        return podcastsInfoList;
    }

    /**
     * Get song
     *
     * @return
     */
    public List<Song> getSong() {
        return songs;
    }

    /**
     * Set users
     *
     * @param userInputList
     */
    public void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Set songs
     *
     * @param songInputList
     */
    public void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * Set podcassts
     *
     * @param podcastInputList
     */
    public void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodesPodcast = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodesPodcast.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(), episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(),
                    podcastInput.getOwner(), episodesPodcast));
        }
    }

    /**
     * Get songs
     *
     * @return
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * get podcasts
     *
     * @return
     */
    public List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * get artists
     *
     * @return
     */
    public List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Artist) {
                artists.add((Artist) user);
            }
        }
        return artists;
    }

    /**
     * Get podcasts
     * am facut aceasta metoda sa mi returneze lista de podcasturi, nu copie
     * @return
     */
    public List<Podcast> getPod() {
        return podcasts;
    }

    /**
     * Get hosts
     *
     * @return
     */
    public List<Host> getHosts() {
        List<Host> hosts = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("host")) {
                Host host = (Host) user;
                hosts.add(host);
            }
        }
        return hosts;
    }

    /**
     * Get albums
     *
     * @return
     */
    public List<Album> getAlbums() {
        List<Album> artistAlbums = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("artist")) {
                Artist artist = (Artist) user;
                artistAlbums.addAll(artist.getAlbums());
            }
        }
        return artistAlbums;
    }

    /**
     * Get playlists
     *
     * @return
     */
    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Get user
     *
     * @param username
     * @return
     */
    public User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Online users
     *
     * @return
     */
    public List<String> onlineUsers() {
        List<String> usersOnline = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                usersOnline.add(user.getUsername());
            }
        }
        return usersOnline;
    }

    /**
     * All users
     *
     * @return
     */
    public List<String> allUsers() {
        List<String> allUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("user")) {
                allUsers.add(user.getUsername());
            }
        }
        for (User user : users) {
            if (user.getType().equals("artist")) {
                allUsers.add(user.getUsername());
            }
        }
        for (User user : users) {
            if (user.getType().equals("host")) {
                allUsers.add(user.getUsername());
            }
        }
        return allUsers;
    }

    /**
     * Update timestamp
     *
     * @param newTimestamp
     */
    public void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }
        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * get top 5 songs
     *
     * @return
     */
    public List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Get top 5 albums
     *
     * @return
     */
    public List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(albums);
        sortedAlbums.sort(Comparator.comparingInt(Album::getTotalLikes).reversed());
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * get top 5 artists
     *
     * @return
     */
    public List<String> getTop5Artists() {
        Admin admin = Admin.getInstance();
        List<Artist> sortedArtists = new ArrayList<>(admin.getArtists());
        sortedArtists.sort(Comparator.comparingInt(Artist::getTotalLikes).reversed());
        List<String> topArtists = new ArrayList<>();
        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topArtists.add(artist.getName());
            count++;
        }
        return topArtists;
    }

    /**
     * Get top 5 playlists
     *
     * @return
     */
    public List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * Reset
     *
     */
    public void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }

    /**
     * Add user
     * @param user
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * Add song
     *
     * @param song
     */
    public void addSongs(final Song song) {
        songs.add(song);
    }

    /**
     * Add episode
     *
     * @param episode
     */
    public void addEpisodes(final Episode episode) {
        episodes.add(episode);
    }

    /**
     * Add podcast
     *
     * @param podcast
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Show albums
     *
     * @param artistUsername
     * @return
     */
    public List<InfoAlbum> showAlbums(final String artistUsername) {
        User artist = getUser(artistUsername);
        if (artist == null || !(artist instanceof Artist)) {
            return null;
        }

        Artist artistAsArtist = (Artist) artist;
        List<InfoAlbum> albumInfoList = new ArrayList<>();
        List<Album> artistAlbums = artistAsArtist.getAlbums();

        for (Album album : artistAlbums) {
            List<String> songNames = new ArrayList<>();
            for (SongInput songInput : album.getSongs()) {
                songNames.add(songInput.getName());
            }

            InfoAlbum albumInfo = new InfoAlbum(album.getName(), songNames);
            albumInfoList.add(albumInfo);
        }

        return albumInfoList;
    }
}
