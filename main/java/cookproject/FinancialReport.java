package cookproject;

public class FinancialReport {
    private String type;
    private String data;

    public FinancialReport(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }
}
