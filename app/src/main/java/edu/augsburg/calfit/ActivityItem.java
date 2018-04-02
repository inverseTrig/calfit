package edu.augsburg.calfit;

public class ActivityItem {
    String name;
    String description;
    int image;
    double calorie;

    public ActivityItem(String name, int image, String description, double calorie) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.calorie = calorie;
    }

    public String toString() {
        return calorie + " cal";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
