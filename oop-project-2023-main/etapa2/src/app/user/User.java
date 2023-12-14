package app.user;

import app.Admin;
import app.audio.Collections.Playlist;
import app.audio.Collections.InfoAlbum;
import app.audio.Collections.Album;
import app.audio.Collections.Podcast;
import app.audio.Collections.AudioCollection;
import app.audio.Collections.PlaylistOutput;
import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.player.Player;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends LibraryEntry {
    private String currentPage;
    private boolean online;
    private static LibraryEntry lastSelected;
    private static LibraryEntry lastLoaded;
    private String ownerCurrentPage;
    @Getter
    private String username;
    @Getter
    private int age;
    @Getter
    private String city;
    @Getter
    private ArrayList<Playlist> playlists;
    private final Player player;
    private static final int MAGIC_NUMBER3 = 3;
    private static final int MAGIC_NUMBER5 = 5;
    private static final int MAGIC_NUMBER6 = 6;
    private static final int MAGIC_NUMBER28 = 28;
    private static final int MAGIC_NUMBER12 = 12;
    private static final int MAGIC_NUMBER1900 = 1900;
    private static final int MAGIC_NUMBER2023 = 2023;
    private final SearchBar searchBar;
    private boolean lastSearched;
    private String type;
    @Getter
    private ArrayList<Playlist> followedPlaylists;
    @Getter
    private ArrayList<Song> likedSongs;
    private String lastSearchType;

    /**
     * Get last loaded
     *
     * @return
     */
    public static LibraryEntry getLastLoaded() {
        return lastLoaded;
    }

    /**
     * Get owner current page
     *
     * @return
     */
    public String getOwnerCurrentPage() {
        return ownerCurrentPage;
    }

    /**
     * Set owner current page
     *
     * @param ownerCurrentPage
     */
    public void setOwnerCurrentPage(final String ownerCurrentPage) {
        this.ownerCurrentPage = ownerCurrentPage;
    }

    /**
     * Get last selected
     *
     * @return
     */
    public static LibraryEntry getLastSelected() {
        return lastSelected;
    }
    /**
     * Get Last Search Type
     *
     * @return
     */
    public String getLastSearchType() {
        return lastSearchType;
    }

    /**
     * Set last search type
     *
     * @param lastSearchType
     */
    public void setLastSearchType(final String lastSearchType) {
        this.lastSearchType = lastSearchType;
    }

    /**
     * Get type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set type
     *
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get player
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get current page
     *
     * @return
     */
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * Set current page
     *
     * @param currentPage
     */
    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Is online
     *
     * @return
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Set online
     *
     * @param online
     */
    public void setOnline(final boolean online) {
        this.online = online;
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public User(final String username, final int age,
                final String city) {
        super(username);
        this.username = username;
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        this.setOnline(true);
        this.setCurrentPage("HomePage");
        this.setOwnerCurrentPage(null);
    }

    /**
     *
     * @param username
     * @param age
     * @param city
     * @param type
     */
    public User(final String username, final int age,
                final String city, final String type) {
        super(username);
        this.username = username;
        this.age = age;
        this.city = city;
        this.type = type;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        this.setOnline(true);
        this.setCurrentPage("HomePage");
        this.setOwnerCurrentPage(null);
    }
    /**
     * Search array list.
     *
     * @param filters the filters
     * @param typeSearch    the type
     * @return the array list
     */
    public ArrayList<String> search(final Filters filters, final String typeSearch) {
        searchBar.clearSelection();
        player.stop();
        this.setLastSearchType(typeSearch);

        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
        List<LibraryEntry> libraryEntries = searchBar.search(filters, typeSearch);

        for (LibraryEntry libraryEntry : libraryEntries) {
            results.add(libraryEntry.getName());
        }
        return results;
    }
    /**
     * Select string.
     * Aici verific tipul userului selectat si in functie
     * de acesta setez pagina si ownerul.
     * @param itemNumber the item number
     * @return the string
     */
    public String select(final int itemNumber, final String userName) {
        User user = Admin.getUser(userName);

        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }

        if (user != null) {
            if (user.getLastSearchType().equals("artist")) {
                lastSelected = selected;
                user.setCurrentPage("ArtistPage");
                user.setOwnerCurrentPage(selected.getName());
                return "Successfully selected %s's page.".formatted(selected.getName());
            } else if (user.getLastSearchType().equals("host")) {
                lastSelected = selected;
                user.setCurrentPage("HostPage");
                user.setOwnerCurrentPage(selected.getName());
                return "Successfully selected %s's page.".formatted(selected.getName());
            } else {
                return "Successfully selected %s.".formatted(selected.getName());
            }
        }
        return "no";
    }
    /**
     * Load string.
     *
     * @return the string
     */
    public String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }

        if (!searchBar.getLastSearchType().equals("song")
                && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
            return "You can't load an empty audio collection!";
        }
        String loadType = searchBar.getLastSearchType();
        if (searchBar.getLastSearchType().equals("album")) {
            player.setSource(searchBar.getLastSelected(), "playlist");
        } else {
            player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());
        }
        lastLoaded = lastSelected;
        searchBar.clearSelection();

        player.pause();
        return "Playback loaded successfully.";
    }
    /**
     * Play pause string.
     *
     * @return the string
     */
    public String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }
    /**
     * Repeat string.
     *
     * @return the string
     */
    public String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";

        switch (repeatMode) {
            case REPEAT_ALL:
                repeatStatus = "repeat all";
                break;
            case REPEAT_ONCE:
                repeatStatus = "repeat once";
                break;
            case REPEAT_INFINITE:
                repeatStatus = "repeat infinite";
                break;
            case REPEAT_CURRENT_SONG:
                repeatStatus = "repeat current song";
                break;
            case NO_REPEAT:
                repeatStatus = "no repeat";
                break;
            default:
                repeatStatus = "Unknown";
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     * Shuffle string.
     *
     * @param seed the seed
     * @return the string
     */
    public String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }

        if (!player.getType().equals("playlist")) {
            return "The loaded source is not a playlist or an album.";
        }

        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     * Forward string.
     *
     * @return the string
     */
    public String forward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to forward.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipNext();

        return "Skipped forward successfully.";
    }

    /**
     * Backward string.
     *
     * @return the string
     */
    public String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     * Like string.
     *
     * @return the string
     */
    public String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }

        if (!player.getType().equals("song")
                && !player.getType().equals("playlist")
                && !player.getType().equals("album")) {
            return "Loaded source is not a song.";
        }

        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();
        return "Like registered successfully.";
    }

    /**
     * Next string.
     *
     * @return the string
     */
    public String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        return "Skipped to next track successfully. The current track is %s.".formatted(
                player.getCurrentAudioFile().getName());
    }

    /**
     * Prev string.
     *
     * @return the string
     */
    public String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }

        player.prev();

        return "Returned to previous track successfully. The current track is %s.".formatted(
                player.getCurrentAudioFile().getName());
    }

    /**
     * Create playlist string.
     *
     * @param name      the name
     * @param timestamp the timestamp
     * @return the string
     */
    public String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }

        playlists.add(new Playlist(name, username, timestamp));

        return "Playlist created successfully.";
    }

    /**
     * Add remove in playlist string.
     *
     * @param id the id
     * @return the string
     */
    public String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }

        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }

        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }

        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     * Switch playlist visibility string.
     *
     * @param playlistId the playlist id
     * @return the string
     */
    public String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }

        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }

    /**
     * Show playlists array list.
     *
     * @return the array list
     */
    public ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     * Follow string.
     *
     * @return the string
     */
    public String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String currentType = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }

        if (!currentType.equals("playlist")) {
            return "The selected source is not a playlist.";
        }

        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(username)) {
            return "You cannot follow or unfollow your own playlist.";
        }

        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     * Gets player stats.
     *
     * @return the player stats
     */
    public PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     * Show Preferred Songs
     *
     * @return
     */
    public ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     * Show preferred songs array list.
     *
     * @return the array list
     */
    public String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     * Simulate time.
     *
     * @param time the time
     */
    public void simulateTime(final int time) {
        player.simulatePlayer(time);
    }

    /**
     * Switch Connection Status
     * Verific daca un user este online, il fac offline si ii opresc
     * timestampul; procedez asemanator in cazul in care e offline
     * @param currentUsername
     * @return
     */
    public String switchConnectionStatus(final String currentUsername) {
        User user = Admin.getUser(currentUsername);
       if (user.getType().equals("artist") || user.getType().equals("host")) {
            return String.format("%s is not a normal user.", currentUsername);
       }
       if (!user.isOnline()) {
           user.setOnline(true);
           player.resumeTime();
           return String.format("%s has changed status successfully.", currentUsername);
       } else {
           user.setOnline(false);
           player.stopTime();
           return String.format("%s has changed status successfully.", currentUsername);
       }
    }

    /**
     * Add user
     * verific tipul userului pe care vreau sa l sterg si verific daca exista
     * vreun alt user care sa asculte ceva ce ii apartine userului pe care vreau
     * sa l sterg.
     * @param username
     * @param age
     * @param city
     * @param userType
     * @return
     */
    public static String addUser(final String username, final int age,
                                 final String city, final String userType) {
        User user = Admin.getUser(username);

        if (user != null) {
            return String.format("The username %s is already taken.", username);
        }
        if (userType.equals("user")) {
            Admin.addUser(new User(username, age, city, userType));
        } else if (userType.equals("artist")) {
            User artist = new Artist(username, age, city, userType);
            artist.setCurrentPage("ArtistPage");
            artist.setType("artist");
            Admin.addUser(artist);
            artist.setOnline(false);
        } else if (userType.equals("host")) {
            User host = new Host(username, age, city, userType);
            host.setCurrentPage("HostPage");
            host.setType("host");
            Admin.addUser(host);
            host.setOnline(false);
        }
        return String.format("The username %s has been added successfully.", username);
    }

    /**
     * Add podcast
     * cand adaug un nou podcast, adaug fiecare episod in lista de episoade si fiecare
     * podcast in lista de podcasturi
     *
     * @param username
     * @param podcastName
     * @param timestamp
     * @param episodes
     * @return
     */
    public static String addPodcast(final String username, final String podcastName,
                                    final Integer timestamp,
                                    final List<EpisodeInput> episodes) {
        User user = Admin.getUser(username);
        if (user == null) {
            return String.format("The username %s doesn't exist.", username);
        }
        if (!(user.getType().equals("host"))) {
            return String.format("%s is not a host.", username);
        }
        Host host = (Host) user;
        if (host.hasDuplicatePodcast(podcastName)) {
            return String.format("%s has another podcast with the same name.", username);
        }

        List<Episode> podcastEpisodes = new ArrayList<>();
        for (EpisodeInput episodeInput : episodes) {
            Episode episode = new Episode(episodeInput.getName(),
                    episodeInput.getDuration(), episodeInput.getDescription());
            podcastEpisodes.add(episode);
            Admin.addEpisodes(episode);
        }

        Podcast podcast = new Podcast(podcastName, username, podcastEpisodes);
        Admin.getPod().add(podcast);
        host.getPodcasts().add(podcast);

        return String.format("%s has added new podcast successfully.", username);
    }

    /**
     * Add album
     * cand adaug un album nou, album fiecare cantec in lista de cantece si
     * fiecare album in lista de albume
     *
     * @param username
     * @param albumName
     * @param releaseYear
     * @param description
     * @param songs
     * @return
     */
    public static String addAlbum(final String username, final String albumName,
                                  final int releaseYear, final String description,
                                  final List<SongInput> songs) {
        User artist = Admin.getUser(username);
        if (artist == null) {
            return String.format("The username %s doesn't exist.", username);
        }

        if (!(artist.getType().equals("artist"))) {
            return String.format("%s is not an artist.", username);
        }

        Artist artistAsArtist = (Artist) artist;
        if (artistAsArtist.hasAlbum(albumName)) {
            return String.format("%s has another album with the same name.", username);
        }
        if (artistAsArtist.hasDuplicateSongs(songs)) {
            return String.format("%s has the same song at least twice in this album.", username);
        }

        for (SongInput songInput : songs) {
            Song song = new Song(songInput.getName(), songInput.getDuration(), albumName,
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), username);

            Admin.addSongs(song);
        }
        Album album = new Album(albumName, username, releaseYear, description, songs);
        List<Album> alb = new ArrayList<Album>(Admin.getAlbums());
        alb.add(album);
        Admin.setAlbums(alb);
        artistAsArtist.addAlbum(album);
        return String.format("%s has added new album successfully.", username);
    }

    /**
     * Remove Event
     *
     * @param username
     * @param eventName
     * @return
     */
    public static String removeEvent(final String username,
                                     final String eventName) {
        User user = Admin.getUser(username);
        if (user == null) {
            return String.format("The username %s doesn't exist.", username);
        }
        if (!(user.getType().equals("artist"))) {
            return String.format("%s is not an artist.", username);
        }
        Artist artist = (Artist) user;
        if (!artist.hasDuplicateEvent(eventName)) {
            return String.format("%s has no event with the given name.", username);
        }
        Event eventRemove = null;
        for (Event event : artist.getEvents()) {
            if (event.getName().equals(eventName)) {
                eventRemove = event;
                break;
            }
        }
        artist.removeAnEvent(artist.getEvents(), eventRemove);
        return String.format("%s deleted the event successfully.", username);
    }

    /**
     * Add Event
     *
     * @param username
     * @param eventName
     * @param eventDescription
     * @param eventDate
     * @return
     */
    public static String addEvent(final String username, final String eventName,
                                  final String eventDescription,
                                  final String eventDate) {
        User user = Admin.getUser(username);
        if (user == null) {
            return String.format("The username %s doesn't exist.", username);
        }

        if (!(user.getType().equals("artist"))) {
            return String.format("%s is not an artist.", username);
        }

        Artist artist = (Artist) user;
        if (artist.hasDuplicateEvent(eventName)) {
            return String.format(username, " has another event with the same name.");
        }
        if (!isValidDate(eventDate)) {
            return "Event for " + username + " does not have a valid date.";
        }

        Event newEvent = new Event(eventName, eventDescription, eventDate);
        artist.addEvent(newEvent);
        return String.format(username + " has added new event successfully.");
    }

    private static boolean isValidDate(final String date) {
        try {
            // Verific formatul datei
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            Date parsedDate = dateFormat.parse(date);

            // Verific condițiile specifice pentru luna februarie
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(MAGIC_NUMBER3, MAGIC_NUMBER5));
            int year = Integer.parseInt(date.substring(MAGIC_NUMBER6));

            if (day > MAGIC_NUMBER28 || day < 1 || month > MAGIC_NUMBER12
                    || month < 1 || year < MAGIC_NUMBER1900 || year > MAGIC_NUMBER2023) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Add Merch
     *
     * @param username
     * @param merchName
     * @param merchDescription
     * @param price
     * @return
     */
    public static String addMerch(final String username, final String merchName,
                                  final String merchDescription, final double price) {
        User user = Admin.getUser(username);
        if (user == null) {
            return String.format("The username %s doesn't exist.", username);
        }
        if (!(user.getType().equals("artist"))) {
            return String.format("%s is not an artist.", username);
        }

        Artist artist = (Artist) user;

        if (artist.hasDuplicateMerch(merchName)) {
            return String.format(username + " has merchandise with the same name.");
        }
        if (price < 0) {
            return "Price for merchandise can not be negative.";
        }
        Merch newMerch = new Merch(merchName, merchDescription, price);
        artist.addMerch(newMerch);
        return String.format(username + " has added new merchandise successfully.");
    }

    /**
     * Print Current Page
     *
     * @param username
     * @return
     */
    public static String
        printCurrentPage(final String username) {
        User user = Admin.getUser(username);

        if (user == null) {
            return String.format("User with username %s not found.", username);
        } else {
            StringBuilder result = new StringBuilder();

            switch (user.getCurrentPage()) {
                case "HomePage":
                    result.append("Liked songs:\n\t");
                    result.append(user.showPreferredSongs()).append("\n\n");
                    result.append("Followed playlists:\n\t[");
                    boolean firstPlaylist = true;
                    for (Playlist playlist : user.getFollowedPlaylists()) {
                        if (!firstPlaylist) {
                            result.append(", ");
                        }
                        result.append(playlist.getName());
                        firstPlaylist = false;
                    }
                    result.append("]");
                    break;
                case "Home":
                    result.append("Liked songs:\n\t");
                    result.append(user.showPreferredSongs()).append("\n\n");
                    result.append("Followed playlists:\n\t[");
                    boolean firstPlaylist2 = true;
                    for (Playlist playlist : user.getFollowedPlaylists()) {
                        if (!firstPlaylist2) {
                            result.append(", ");
                        }
                        result.append(playlist.getName());
                        firstPlaylist2 = false;
                    }
                    result.append("]");
                    break;
                case "LikedContent":
                    result.append("Liked songs:\n\t[");
                    boolean firstSong = true;
                    for (Song song : user.likedSongs) {
                        if (!firstSong) {
                            result.append(", ");
                        }
                        result.append(song.getName() + " - " + song.getArtist());
                        firstSong = false;
                    }
                    result.append("]\n\n");
                    result.append("Followed playlists:\n\t[");
                    boolean variable = true;
                    for (Playlist playlist : user.getFollowedPlaylists()) {
                        if (!variable) {
                            result.append(", ");
                        }
                        result.append(playlist.getName() + " - " + playlist.getOwner());
                        variable = false;
                    }
                    result.append("]");
                    break;
                case "ArtistPage":
                    if (lastSelected instanceof Artist) {
                    Artist artist = (Artist) lastSelected;
                    result.append("Albums:\n\t[");
                    for (InfoAlbum album : Admin.showAlbums(artist.getUsername())) {
                        result.append(album.getName());
                    }
                    result.append("]\n\n");
                    result.append("Merch:\n\t[");
                    boolean firstMerch = true;
                    for (Merch merch : artist.getMerch()) {
                        if (!firstMerch) {
                            result.append(", ");
                        }
                        result.append(merch.getName()).append(" - ").append((int)
                                Math.floor(merch.getPrice())).append(":\n\t").append(
                                        merch.getDescription());
                        firstMerch = false;
                    }
                    result.append("]\n\n");
                    result.append("Events:\n\t[");
                    boolean firstEvent = true;
                    for (Event event : artist.getEvents()) {
                        if (!firstEvent) {
                            result.append(", ");
                        }
                        result.append(event.getName()).append(" - ").append(
                                event.getDate()).append(":\n\t").append(event.getDescription());
                        firstEvent = false;
                    }
                    result.append("]");
                    break;
                    }
                case "HostPage":
                    if (lastSelected instanceof Host) {
                    Host host = (Host) lastSelected;
                    result.append("Podcasts:\n\t[");
                    boolean firstHost = false;
                    for (User user1 : Admin.getUsers()) {
                        if (user1.getUsername().equals(host.getUsername())) {
                            if (firstHost) {
                                result.append(", ");
                            }
                            boolean firstPodcast = false;
                            for (Podcast podcast : host.getPodcasts()) {
                                if (firstPodcast) {
                                    result.append(", ");
                                }
                                result.append(podcast.getName());
                                result.append(":\n\t[");
                                boolean firstEpisode = false;
                                for (Episode episode : podcast.getEpisodes()) {
                                    if (firstEpisode) {
                                        result.append(", ");
                                    }
                                    result.append(episode.getName());
                                    result.append(" - ");
                                    result.append(episode.getDescription());
                                    firstEpisode = true;
                                }
                                result.append("]\n");
                                firstPodcast = true;
                            }
                            result.append("]");
                            firstHost = true;
                        }
                    }
                    result.append("\n\n");
                    result.append("Announcements:\n\t[");
                    boolean firstAnnouncement = false;
                    for (User user1 : Admin.getUsers()) {
                        if (user1.getUsername().equals(host.getUsername())) {
                            for (Announcement announcement : host.getAnnouncements()) {
                                if (firstAnnouncement) {
                                    result.append(", ");
                                }
                                result.append(announcement.getName());
                                result.append(":\n\t");
                                result.append(announcement.getDescription());
                                firstAnnouncement = true;
                            }
                        }
                    }

                    result.append("\n]");
                    break;
                    }
                default:
                    result.append("Unknown page.\n");
                    break;
            }

            return result.toString();
            }
        }

    /**
     * Remove podcast
     *
     * @param username
     * @param podcastName
     * @return
     */
    public static String removePodcast(final String username,
                                           final String podcastName) {
            User user = Admin.getUser(username);
            if (user == null) {
                return String.format("The username %s doesn't exist.", username);
            }

            if (!(user.getType().equals("host"))) {
                return String.format("%s is not a host.", username);
            }

            Host host = (Host) user;
            if (!host.hasPodcast(podcastName)) {
                return String.format("%s doesn't have a podcast with the given name.", username);
            }
            if (hasUserInteractions(host)) {
                return String.format("%s can't delete this podcast.", username);
            }

            host.getPodcasts().removeIf(p -> p.getName().equals(podcastName));
            Admin.getPod().removeIf(s -> s.getName().equals(podcastName));

            return String.format("%s deleted the podcast successfully.", username);
        }

    /**
     * Add announcement
     *
     * @param username
     * @param announcementName
     * @param timestamp
     * @param description
     * @return
     */
        public static String addAnnouncement(final String username,
                                             final String announcementName,
                                             final int timestamp,
                                             final String description) {
            User user = Admin.getUser(username);
            if (user == null) {
                return String.format("The username %s doesn't exist.", username);
            }
            if (!(user.getType().equals("host"))) {
                return String.format("%s is not a host.", username);
            }
            Host host = (Host) user;
            if (host.hasDuplicateAnnouncement(announcementName)) {
                return String.format("%s has another podcast with the same name.", username);
            }
            Announcement newAnnouncement = new Announcement(description, announcementName);
            host.getAnnouncements().add(newAnnouncement);

            return String.format("%s has successfully added new announcement.", username);
        }

    /**
     * Remove Announcement
     *
     * @param username
     * @param announcementName
     * @return
     */
    public static String removeAnnouncement(final String username,
                                                final String announcementName) {
            User user = Admin.getUser(username);
            if (user == null) {
                return String.format("The username %s doesn't exist.", username);
            }
            if (!(user.getType().equals("host"))) {
                return String.format("%s is not a host.", username);
            }
            Host host = (Host) user;
            if (!host.hasAnnouncement(announcementName)) {
                return String.format("%s has no announcement with the given name.", username);
            }
            Announcement announcementRemove = null;
            for (Announcement announcement : host.getAnnouncements()) {
                if (announcement.getName().equals(announcementName)) {
                    announcementRemove = announcement;
                    break;
                }
            }
            host.removeAnAnnouncement(host.getAnnouncements(), announcementRemove);
            return String.format("%s has successfully deleted the announcement.", username);
        }

    /**
     * Delete user
     *
     * @param username
     * @return
     */
    public static String deleteUser(final String username) {
            User user = Admin.getUser(username);

            if (user == null) {
                return String.format("The username %s doesn't exist.", username);
            }
            for (User otherUser : Admin.getUsers()) {
                if (otherUser.getOwnerCurrentPage() != null) {
                    if (otherUser.getCurrentPage().equals("ArtistPage")
                            && user.getType().equals("artist")
                            && otherUser.getOwnerCurrentPage().equals(username)) {
                        return String.format("%s can't be deleted.", username);
                    }
                }
            }
            if (user.getType().equals("artist")) {
                if (hasUserInteractions(user)) {
                    return String.format("%s can't be deleted.", username);
                } else {
                    Artist artist = (Artist) user;
                    List<Album> artistAlbums = artist.getAlbums();
                    for (Album album : artistAlbums) {
                        for (SongInput songInput : album.getSongs()) {
                            Song newSong = new Song(songInput.getName(),
                                    songInput.getDuration(), songInput.getAlbum(),
                                    songInput.getTags(), songInput.getLyrics(),
                                    songInput.getGenre(), songInput.getReleaseYear(),
                                    songInput.getArtist());
                            Admin.getSong().removeIf(s -> s.getName().equals(newSong.getName()));
                            newSong.dislike();
                            for (User user1 : Admin.getUsers()) {
                                    List<Song> userLikedSongs = user1.getLikedSongs();
                                    userLikedSongs.removeIf(s ->
                                            s.getName().equals(newSong.getName()));
                            }
                        }
                    }
                    Admin.getAlbums().removeAll(artistAlbums);
                    artistAlbums.clear();
                }
            } else if (user.getType().equals("host")) {
                    for (User otherUser : Admin.getUsers()) {
                        if (otherUser.getOwnerCurrentPage() != null) {
                            if (otherUser.getCurrentPage().equals(user.getCurrentPage())
                                    && otherUser.getOwnerCurrentPage().equals(user.getUsername())) {
                                return String.format("%s can't be deleted.", username);
                            }
                        }
                    }
                    if (hasUserInteractions(user)) {
                        return String.format("%s can't be deleted.", username);
                    } else {
                        Host host = (Host) user;
                        List<Podcast> podcasts = host.getPodcasts();
                        for (Podcast podcast : podcasts) {
                            podcast.getEpisodes().clear();
                        }
                        host.getPodcasts().clear();
                    }
            } else if (user.getType().equals("user")) {
                if (hasUserInteractions(user)) {
                    return String.format("%s can't be deleted.", username);
                } else {
                    List<Playlist> playlists = user.getPlaylists();
                    for (Playlist playlist : playlists) {
                        playlist.getSongs().clear();
                        for (User user1 : Admin.getUsers()) {
                            List<Playlist> userFollowedPlaylists = user1.getFollowedPlaylists();
                            userFollowedPlaylists.removeIf(s ->
                                    s.getName().equals(playlist.getName()));
                        }
                    }
                    for (Playlist playlist : user.getFollowedPlaylists()) {
                        playlist.decreaseFollowers();
                    }
                    user.getPlaylists().clear();
                }
            }
            Admin.getUsers().remove(user);
            return String.format("%s was successfully deleted.", username);
        }

    /**
     * Has user interactions
     * verifica daca userul pe care vreau sa l sterg are interactiune
     * cu alt user din lista de useri, adica daca exista vreun user
     * care asculta ceva ce i apartine userului curent
     *
     * @param currentUser
     * @return
     */
    private static boolean hasUserInteractions(final User currentUser) {
            if (currentUser.getType().equals("artist")) {
                Artist artist = (Artist) currentUser;
                for (User otherUser : Admin.getUsers()) {
                    var otherUserSource = otherUser.getPlayer().getSource();
                    if (!otherUser.getUsername().equals(currentUser.getUsername())
                            && otherUserSource != null) {
                        boolean typeNotSong = otherUserSource.getType().equals(
                                Enums.PlayerSourceType.PODCAST) || otherUserSource.getType().equals(
                                Enums.PlayerSourceType.PLAYLIST);
                        if (otherUserSource.getType().equals(Enums.PlayerSourceType.PLAYLIST)) {
                            if (otherUserSource.getAudioCollection() instanceof Playlist) {
                            Playlist playlist = (Playlist) otherUserSource.getAudioCollection();
                            ArrayList<Song> songsFromPlaylist = playlist.getSongs();

                                for (Song song : songsFromPlaylist) {
                                    if (song.matchesArtist(currentUser.getUsername())) {
                                        return true;
                                    }
                                }
                            }
                        }
                        if (typeNotSong && otherUserSource.getAudioCollection() != null) {
                            if (otherUserSource.getAudioCollection().getOwner().equals(
                                    currentUser.getUsername())) {
                                return true;
                            }
                        } else if (otherUserSource.getAudioFile() != null) {
                            for (Album album : artist.getAlbums()) {
                                for (SongInput song : album.getSongs()) {
                                    if (otherUserSource.getAudioFile().getName().
                                            equals(song.getName())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (currentUser.getType().equals("host")) {
                Host host = (Host) currentUser;
                for (User otherUser : Admin.getUsers()) {
                    var otherUserSource = otherUser.getPlayer().getSource();
                    if (!otherUser.getUsername().equals(currentUser.getUsername())
                            && otherUserSource != null) {
                        boolean typeNotPodcast = otherUserSource.getType().equals(
                                Enums.PlayerSourceType.LIBRARY) || otherUserSource.getType().equals(
                                        Enums.PlayerSourceType.PLAYLIST);
                        if (typeNotPodcast && otherUserSource.getAudioCollection() != null) {
                            if (otherUserSource.getAudioCollection().getOwner().equals(
                                    currentUser.getUsername())) {
                                return true;
                            }
                        } else if (otherUserSource.getAudioFile() != null) {
                            for (Podcast podcast : host.getPodcasts()) {
                                for (Episode episode : podcast.getEpisodes()) {
                                    if (otherUserSource.getAudioFile().getName().
                                            equals(episode.getName())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (User otherUser : Admin.getUsers()) {
                    var otherUserSource = otherUser.getPlayer().getSource();
                    if (!otherUser.getUsername().equals(currentUser.getUsername())
                            && otherUserSource != null) {
                        boolean typeNotPlaylist = otherUserSource.getType().equals(
                                Enums.PlayerSourceType.LIBRARY) || otherUserSource.getType().equals(
                                        Enums.PlayerSourceType.PODCAST);
                        if (typeNotPlaylist && otherUserSource.getAudioCollection() != null) {
                            if (otherUserSource.getAudioCollection().getOwner().equals(
                                    currentUser.getUsername())) {
                                return true;
                            }
                        } else if (otherUserSource.getAudioFile() != null) {
                            for (Playlist playlist : currentUser.getPlaylists()) {
                                for (Song song : playlist.getSongs()) {
                                    if (otherUserSource.getAudioFile().getName().
                                            equals(song.getName())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

    /**
     * Change page
     *
     * @param username
     * @param nextPage
     * @return
     */
    public static String changePage(final String username,
                                        final String nextPage) {
            User user = Admin.getUser(username);
            if (isValidPage(nextPage)) {
                user.setCurrentPage(nextPage);
                return String.format("%s accessed %s successfully.", username, nextPage);
            } else {
                return  String.format("%s is trying to access a non-existent page.", username);
            }
        }

    /**
     * Is Valid Page
     * verific daca pagina pe care vreau sa ma mut in change page este valida, fiind
     * doar doua cazuri posibile
     *
     * @param nextPage
     * @return
     */
    private static boolean isValidPage(final String nextPage) {
            return nextPage.equals("Home") || nextPage.equals("LikedContent");
        }

    /**
     * Is Song In Playlist
     * verific daca vreun user are in playlist vreun cantec ce apartine
     * albumului pe care vreau sa l sterg
     *
     * @param currentUser
     * @param albumName
     * @return
     */
    public static boolean isSongInPlaylist(final User currentUser, final String albumName) {
            Artist artist = (Artist) currentUser;
            List<Song> songsFromAlbum = new ArrayList<>();
            Album currentAlbum = null;
            for (Album album : artist.getAlbums()) {
                if (album.getName().equals(albumName)) {
                    currentAlbum = album;
                }
            }
            assert currentAlbum != null;
            for (SongInput songFromAlbum : currentAlbum.getSongs()) {
                Song song = new Song(songFromAlbum.getName(), songFromAlbum.getDuration(),
                        songFromAlbum.getAlbum(), songFromAlbum.getTags(),
                        songFromAlbum.getLyrics(), songFromAlbum.getGenre(),
                        songFromAlbum.getReleaseYear(), songFromAlbum.getArtist());
                songsFromAlbum.add(song);
            }
            for (User otherUser : Admin.getUsers()) {
                for (Playlist playlist : otherUser.getPlaylists()) {
                    for (Song song : playlist.getSongs()) {
                        for (Song songFromAlbum : songsFromAlbum) {
                            if (song.getName().equals(songFromAlbum.getName())) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

    /**
     * Remove Album
     *
     * @param username
     * @param albumName
     * @return
     */
    public static String removeAlbum(final String username, final String albumName) {
            User user = Admin.getUser(username);
            if (user == null) {
                return String.format("The username %s doesn't exist.", username);
            }
            if (!(user.getType().equals("artist"))) {
                return String.format("%s is not an artist.", username);
            }
            Artist artist = (Artist) user;
            if (!artist.hasAlbum(albumName)) {
                return String.format("%s doesn't have an album with the given name.", username);
            }
            if (hasUserInteractions(artist) || isSongInPlaylist(user, albumName)) {
                return String.format("%s can't delete this album.", username);
            }
            for (Album album : Admin.getAlbums()) {
                if (album.getName().equals(albumName)) {
                    album.getSongs().clear();
                }
                artist.getAlbums().remove(album);
            }
            return String.format("%s deleted the album successfully.", username);
        }
    }

