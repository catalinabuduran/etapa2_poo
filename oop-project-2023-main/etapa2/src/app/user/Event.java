package app.user;

public class Event {
    /**
     * get date
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

    private String name;
    private String description;
    private String date;

    /**
     * Constructor
     *
     * @param name
     * @param description
     * @param date
     */
    public Event(final String name, final String description,
                 final String date) {
        this.name = name;
        this.description = description;
        this.date = date;
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

}
