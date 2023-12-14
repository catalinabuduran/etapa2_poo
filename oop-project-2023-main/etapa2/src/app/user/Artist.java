package app.user;

import app.audio.Collections.Album;
import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Artist extends User implements Page {
    private List<Album> albums;
    private List<Event> events;
    private List<Merch> merchs;
    private String printCurrentPage;

    /**
     * Get print current page
     *
     */
    @Override
    public String getPrintCurrentPage() {
        return printCurrentPage;
    }

    /**
     * Set print current page
     *
     */
    @Override
    public void setPrintCurrentPage(final String printCurrentPage) {
        this.printCurrentPage = printCurrentPage;
    }

    /**
     * Get events
     *
     * @return
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Get merch
     *
     * @return
     */
    public List<Merch> getMerch() {
        return merchs;
    }


    /**
     * Get albums
     *
     * @return
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * Constructor
     *
     * @param username
     * @param age
     * @param city
     * @param type
     */
    public Artist(final String username, final int age,
                  final String city, final String type) {
        super(username, age, city, type);
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merchs = new ArrayList<>();
    }

    /**
     * Remove event
     *
     * @param events
     * @param eventToRemove
     */
    public static void removeAnEvent(final List<Event> events,
                                            final Event eventToRemove) {
        events.remove(eventToRemove);
    }

    /**
     * Has duplicate songs
     *
     * @param songs
     * @return
     */
    public static boolean hasDuplicateSongs(final List<SongInput> songs) {
        Set<String> songNames = new HashSet<>();
        for (SongInput song : songs) {
            if (!songNames.add(song.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Has duplicate event
     *
     * @param name
     * @return
     */
    public boolean hasDuplicateEvent(final String name) {
        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Has duplicate merch
     *
     * @param merchName
     * @return
     */
    public boolean hasDuplicateMerch(final String merchName) {

        for (Merch merch : merchs) {
            if (merch.getName().equalsIgnoreCase(merchName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add event
     *
     * @param event
     */
    public void addEvent(final Event event) {
        this.events.add(event);
    }

    /**
     * Add merch
     *
     * @param merch
     */
    public void addMerch(final Merch merch) {
        this.merchs.add(merch);
    }

    /**
     * Add album
     *
     * @param album
     */
    public void addAlbum(final Album album) {
        this.albums.add(album);
    }

    /**
     * Has album
     *
     * @param albumName
     * @return
     */
    public boolean hasAlbum(final String albumName) {
        return albums.stream().anyMatch(album -> album.getName().equals(albumName));
    }
}
