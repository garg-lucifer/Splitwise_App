package com.namangarg.splitwiseapp;

public class InputData {
    private String basicData;
    private int i;
    private int j;
    private int money;

    public InputData(String basicData, int i, int j, int money){
        this.basicData = basicData;
        this.i = i;
        this.j = j;
        this.money = money;
    }

    public String getBasicData() {
        return basicData;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
