package fileio.input;

import java.util.ArrayList;

public final class CommandInput {
    private ArrayList<SongInput> songs;

    /**
     * Get episodes
     *
     * @return
     */
    public ArrayList<EpisodeInput> getEpisodes() {
        return episodes;
    }

    /**
     * Set episodes
     *
     * @param episodes
     */
    public void setEpisodes(final ArrayList<EpisodeInput> episodes) {
        this.episodes = episodes;
    }

    private ArrayList<EpisodeInput> episodes;
    private Integer releaseYear;
    private String nextPage;
    private Integer age;
    private String city;
    private String description;
    private String name;
    private String date;
    private Integer price;
    private String command;
    private String username;
    private Integer timestamp;
    private String type; // song / playlist / podcast
    private FiltersInput filters; // pentru search
    private Integer itemNumber; // pentru select
    private Integer repeatMode; // pentru repeat
    private Integer playlistId; // pentru add/remove song
    private String playlistName; // pentru create playlist
    private Integer seed; // pentru shuffle

    /**
     * Constructor
     *
     */
    public CommandInput() {
    }

    /**
     * Get type
     *
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
     * Get command
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * Set command
     * @param command
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * Get username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     *
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get timestamp
     *
     * @return
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp
     *
     * @param timestamp
     */
    public void setTimestamp(final Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get filters
     *
     * @return
     */
    public FiltersInput getFilters() {
        return filters;
    }

    /**
     * Set filters
     *
     * @param filters
     */
    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    /**
     * Get item number
     *
     * @return
     */
    public Integer getItemNumber() {
        return itemNumber;
    }

    /**
     * Set item number
     *
     * @param itemNumber
     */
    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Get repeat mode
     *
     * @return
     */
    public Integer getRepeatMode() {
        return repeatMode;
    }

    /**
     * Set repeat mode
     *
     * @param repeatMode
     */
    public void setRepeatMode(final Integer repeatMode) {
        this.repeatMode = repeatMode;
    }

    /**
     * Get playlist id
     *
     * @return
     */
    public Integer getPlaylistId() {
        return playlistId;
    }

    /**
     * Set playlist id
     *
     * @param playlistId
     */
    public void setPlaylistId(final Integer playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Get playlist name
     *
     * @return
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Set playlist name
     *
     * @param playlistName
     */
    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Get seed
     *
     * @return
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * Set seed
     *
     * @param seed
     */
    public void setSeed(final Integer seed) {
        this.seed = seed;
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "CommandInput{"
                + "command='" + command + '\''
                + ", username='" + username + '\''
                + ", timestamp=" + timestamp
                + ", type='" + type + '\''
                + ", filters=" + filters
                + ", itemNumber=" + itemNumber
                + ", repeatMode=" + repeatMode
                + ", playlistId=" + playlistId
                + ", playlistName='" + playlistName + '\''
                + ", seed=" + seed
                + '}';
    }

    /**
     * Get release year
     *
     * @return
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Set release year
     *
     * @param releaseYear
     */
    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Get next page
     *
     * @return
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Set next page
     *
     * @param nextPage
     */
    public void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Get age
     *
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Set age
     *
     * @param age
     */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /**
     * Get city
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     *
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
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
     * Get date
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Set date
     *
     * @param date
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Get price
     *
     * @return
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Set price
     *
     * @param price
     */
    public void setPrice(final Integer price) {
        this.price = price;
    }

    /**
     * Get songs
     *
     * @return
     */
    public ArrayList<SongInput> getSongs() {
        return songs;
    }

    /**
     * Set songs
     *
     * @param songs
     */
    public void setSongs(final ArrayList<SongInput> songs) {
        this.songs = songs;
    }
}
