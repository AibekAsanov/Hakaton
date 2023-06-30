package hakaton;

public enum Statuses {
    InProgress("Выполняется"), Planned("Запланировано"), Done("Выполнено");

    private String text;

    private Statuses(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
