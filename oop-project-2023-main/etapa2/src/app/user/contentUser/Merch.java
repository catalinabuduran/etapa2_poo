package app.user.contentUser;

public class Merch {
    private String name;
    private String description;
    private double price;

    /**
     * Constructor
     *
     * @param name
     * @param description
     * @param price
     */
    public Merch(final String name, final String description,
                 final double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Get price
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set price
     *
     * @param price
     */
    public void setPrice(final int price) {
        this.price = price;
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
