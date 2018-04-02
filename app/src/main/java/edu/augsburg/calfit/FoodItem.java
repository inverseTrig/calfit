package edu.augsburg.calfit;

public class FoodItem {
    String name;
    int image;
    double calorie;
    double sugar;
    double fat;

    public FoodItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String generateDescription() {
        return calorie + " kcal, " + sugar + "g sugar, " + fat + "g fat";
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

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }
}
