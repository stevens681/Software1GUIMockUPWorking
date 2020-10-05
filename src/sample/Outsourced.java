package sample;

/**
 * The outsourced part
 * @author Fernando Rosa
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * This is the in house part
     * @param id The part id
     * @param name The part name
     * @param price The part price
     * @param stock The part stock level
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     * @param companyName The part company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        this.companyName = companyName;
    }

    /**
     * @return The company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName The company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;

    }
}