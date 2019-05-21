package categories.java.a1enum;

public enum BaseData {
    STATUS_ID_KEY1(1), STATUS_ID_KEY2(2), STATUS_ID_KEY3(3);
    private int id;

    private BaseData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
