package cookproject;

public class Chef {
    private String name;
    private String expertise;
    private int currentWorkload;
    private boolean isAvailable;

    public Chef(String name, String expertise, int currentWorkload, boolean isAvailable) {
        this.name = name;
        this.expertise = expertise;
        this.currentWorkload = currentWorkload;
        this.isAvailable = isAvailable;
    }

    public Chef(String name, String expertise, int currentWorkload) {
        this.name = name;
        this.expertise = expertise;
        this.currentWorkload = currentWorkload;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void assignTask() {
        this.currentWorkload++;
    }

    public boolean hasLowWorkload() {
        return currentWorkload < 5;
    }


    public void receiveNotification(String message) {
    System.out.println("Notification to " + name + ": " + message);
}

public void receiveReminder(String reminderMessage) {
    System.out.println("Reminder to " + name + ": " + reminderMessage);
}

}
