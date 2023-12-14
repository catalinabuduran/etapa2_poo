package app.user;

public class Announcement {
    private String description;
    private String name;

    /**
     * Constructor
     *
     * @param description
     * @param name
     */
    public Announcement(final String description, final String name) {
        this.description = description;
        this.name = name;
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
     * Set description;
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
}
