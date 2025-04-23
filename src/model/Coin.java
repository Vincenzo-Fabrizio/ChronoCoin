package model;

public class Coin {
    private String ID;
    private String name;
    private int year;
    private String material;
    private double weight;
    private double diameter;
    private double height;
    private double price;
    private OptionConservation conservationObverse;
    private OptionConservation conservationReverse;
    private NumismaticRarity degree;
    private String note;
    private String photoPathObverse;
    private String photoPathReverse;
    
    public Coin() {
    }

    public Coin(String name, int year, String material, double weight, double diameter, double height, double price,
            OptionConservation conservationObverse, OptionConservation conservationReverse, NumismaticRarity degree,
            String note, String photoPathObverse, String photoPathReverse) {
        this.name = name;
        this.year = year;
        this.material = material;
        this.weight = weight;
        this.diameter = diameter;
        this.height = height;
        this.price = price;
        this.conservationObverse = conservationObverse;
        this.conservationReverse = conservationReverse;
        this.degree = degree;
        this.note = note;
        this.photoPathObverse = photoPathObverse;
        this.photoPathReverse = photoPathReverse;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OptionConservation getConservationObverse() {
        return conservationObverse;
    }

    public void setConservationObverse(OptionConservation conservationObverse) {
        this.conservationObverse = conservationObverse;
    }

    public OptionConservation getConservationReverse() {
        return conservationReverse;
    }

    public void setConservationReverse(OptionConservation conservationReverse) {
        this.conservationReverse = conservationReverse;
    }

    public NumismaticRarity getDegree() {
        return degree;
    }

    public void setDegree(NumismaticRarity degree) {
        this.degree = degree;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhotoPathObverse() {
        return photoPathObverse;
    }

    public void setPhotoPathObverse(String photoPathObverse) {
        this.photoPathObverse = photoPathObverse;
    }

    public String getPhotoPathReverse() {
        return photoPathReverse;
    }

    public void setPhotoPathReverse(String photoPathReverse) {
        this.photoPathReverse = photoPathReverse;
    }

}
