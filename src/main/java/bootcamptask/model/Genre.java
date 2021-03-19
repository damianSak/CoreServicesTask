package bootcamptask.model;

public enum Genre {
    Rock("Rock"),
    Alternative("Alternative"),
    R_AND_B("R&B");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}



