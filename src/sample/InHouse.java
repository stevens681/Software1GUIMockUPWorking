package sample;

/**
 * The in house part
 * @author Fernando Rosa
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * This is the in house part
     * @param id The part id
     * @param name The part name
     * @param price The part price
     * @param stock The part stock level
     * @param min The part minimum stock level
     * @param max The part maximum stock level
     * @param machineId The part machine id
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);

        this.machineId = machineId;
    }

    /**
     * @return The machine id
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId to machine id to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
