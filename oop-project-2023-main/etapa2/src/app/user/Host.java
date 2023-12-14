package app.user;

import app.audio.Collections.Podcast;

import java.util.ArrayList;
import java.util.List;

public class Host extends User implements Page {
    private String printCurrentPage;
    private List<Announcement> announcements;
    private List<Podcast> podcasts;

    /**
     * Has podcast
     *
     * @param podcastName
     * @return
     */
    public boolean hasPodcast(final String podcastName) {
        return getPodcasts().stream().anyMatch(
                podcast -> podcast.getName().equals(podcastName));
    }

    /**
     * Has announcement
     *
     * @param name
     * @return
     */
    public boolean hasAnnouncement(final String name) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Has duplicate announcement
     * verific daca un host are un anunt de 2 ori
     * @param name
     * @return
     */
    public boolean hasDuplicateAnnouncement(final String name) {
        for (Announcement announcement : this.getAnnouncements()) {
            if (announcement.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove announcement
     *
     * @param announcements
     * @param announcementToRemove
     */
    public static void removeAnAnnouncement(final List<Announcement> announcements,
                                            final Announcement announcementToRemove) {
        announcements.remove(announcementToRemove);
    }

    /**
     * Get print current page
     *
     * @return
     */
    @Override
    public String getPrintCurrentPage() {
        return printCurrentPage;
    }

    /**
     * Set print current page
     *
     * @param printCurrentPage
     */
    @Override
    public void setPrintCurrentPage(final String printCurrentPage) {
        this.printCurrentPage = printCurrentPage;
    }

    /**
     * Get announcements
     *
     * @return
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Set announcements
     *
     * @param announcements
     */
    public void setAnnouncements(final List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Get podcasts
     *
     * @return
     */
    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     * Set podcasts
     *
     * @param podcasts
     */
    public void setPodcasts(final List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * Constructor
     *
     * @param username
     * @param age
     * @param city
     * @param type
     */
    public Host(final String username, final int age,
                final String city, final String type) {
        super(username, age, city, type);
        this.announcements = new ArrayList<>();
        this.podcasts = new ArrayList<>();
    }

    /**
     * Has duplicate podcast
     * verific daca un host are un podcast duplicat
     * @param podcastName
     * @return
     */
    public boolean hasDuplicatePodcast(final String podcastName) {
        for (Podcast podcast : this.getPodcasts()) {
            if (podcast.getName().equals(podcastName)) {
                return true;
            }
        }
        return false;
    }
}
