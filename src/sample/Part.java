package sample;
/**
 * Supplied class Part.java
 * @author Fernando Rosa
 */
public class Part {

    //Variable Declaration
    private int id;
    private int stock;
    private int max;
    private int min;
    private double price;
    private String name;

    /**
     * This is a part and it's fields
     * @param id The part id
     * @param name The part name
     * @param price The part price
     * @param stock The part stock level
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.stock = stock;
        this.price = price;
        this.name = name;

    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
