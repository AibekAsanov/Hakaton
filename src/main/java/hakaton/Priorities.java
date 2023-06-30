package hakaton;

public enum Priorities {
    Low("Низкий"), Middle("Средний"), High("Высокий");

    private String text;

    private Priorities(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
