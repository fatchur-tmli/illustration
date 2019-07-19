package co.id.tmli.illustration.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender parse(boolean b) {
        return b ? MALE : FEMALE;
    }

    public boolean isMale() {
        return this.equals(MALE);
    }

    public boolean isFemale() {
        return this.equals(FEMALE);
    }

}
