package app;

import app.audio.Collections.*;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.user.*;
import fileio.input.*;
import lombok.Getter;

import java.util.*;

public class Admin extends LibraryEntry {
    @Getter
    private static List<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static List<Host> hosts = new ArrayList<>();
    private static List<Episode> episodes = new ArrayList<>();


    public static void setAlbums(final List<Album> albums) {
        Admin.albums = albums;
    }

    private static List<Album> albums = new ArrayList<>();
    private static int timestamp = 0;

    public Admin(final String name) {
        super(name);
    }

    public Admin(final String username, final int age, final String city) {
        super(username, age, city);
    }

    public static List<InfoPodcast> showPodcasts(final String username) {
        Host host = (Host) Admin.getUser(username);

        List<InfoPodcast> podcastsInfoList = new ArrayList<>();
        List<Podcast> hostPodcasts = host.getPodcasts();

        // Pentru fiecare album, construim informațiile necesare și le adăugăm în lista de rezultate
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

    public static List<Song> getSong() {
        return songs;
    }

    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(), episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }
    public static List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Artist) {
                artists.add((Artist) user);
            }
        }
        return artists;
    }
    public static List<Podcast> getPod() {
        return podcasts;
    }

    public static List<Host> getHosts() {
        List<Host> hosts = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("host")) {
                Host host = (Host) user;
                hosts.add(host);
            }
        }
        return hosts;
    }

    public static List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("artist")) {
                Artist artist = (Artist) user;
                albums.addAll(artist.getAlbums());
            }
        }
        return albums;
    }

    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<String> onlineUsers() {
        List<String> usersOnline = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                usersOnline.add(user.getUsername());
            }
        }
        return usersOnline;
    }

    public static List<String> allUsers() {
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


    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }
        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= 5) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }
//    public static List<String> getSortedSongs(User user) {
//        //List<Song> sortedSongs = new ArrayList<>(user.getLikedSongs());
//        user.getLikedSongs().sort(Comparator
//                .comparingInt(Song::getLikes)
//                .reversed() // Sortați descrescător după numărul de like-uri
//                .thenComparing(Comparator.comparing(Song::getName)));
//        List<String> topSongs = new ArrayList<>();
//        int count = 0;
//        for (Song song : user.getLikedSongs()) {
//            if (count >= 5) {
//                break;
//            }
//            topSongs.add(song.getName());
//            count++;
//        }
//        return topSongs;
//    }
    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(albums);
        sortedAlbums.sort(Comparator.comparingInt(Album::getTotalLikes).reversed());
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= 5) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= 5) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }

    public static void addUser(final User user) {
        users.add(user);
    }

    public static void addSongs(final Song song) {
        songs.add(song);
    }
    public static void addEpisodes(final Episode episode) {
        episodes.add(episode);
    }

    public static void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    public static List<InfoAlbum> showAlbums(final String artistUsername) {
        User artist = getUser(artistUsername);
        if (artist == null || !(artist instanceof Artist)) {
            return null;
        }

        Artist artistAsArtist = (Artist) artist;
        List<InfoAlbum> albumInfoList = new ArrayList<>();

        // Obținem lista de albume ale artistului
        List<Album> artistAlbums = artistAsArtist.getAlbums();

        // Pentru fiecare album, construim informațiile necesare și le adăugăm în lista de rezultate
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
