package com.keyboardman.tool.zhaofeng.model;

public class ShopZF {

    private int id;
    private String medicineName;
    private String medicinePrice;
    private String medicineCount;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(String medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public String getMedicineCount() {
        return medicineCount;
    }

    public void setMedicineCount(String medicineCount) {
        this.medicineCount = medicineCount;
    }
}
