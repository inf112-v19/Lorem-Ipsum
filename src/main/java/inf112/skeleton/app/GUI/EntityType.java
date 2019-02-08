package inf112.skeleton.app.GUI;

public enum EntityType {

    PLAYER("player", 14, 32, 40);

    private String id;
    private int width, height;
    private int weight;

    EntityType(String id, int width, int height, int weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
