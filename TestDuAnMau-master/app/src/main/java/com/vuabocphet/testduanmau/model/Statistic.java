package com.vuabocphet.testduanmau.model;

public class Statistic  {


    private double staDay,staMonth,staYear;

    public double getStaDay() {
        return staDay;
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public void setStaDay(double staDay) {
//        this.staDay = staDay;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

    public double getStaMonth() {
        return staMonth;
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public void setStaMonth(double staMonth) {
//        this.staMonth = staMonth;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

    public double getStaYear() {
        return staYear;
    }

// --Commented out by Inspection START (10/13/2018 10:05 AM):
//    public void setStaYear(double staYear) {
//        this.staYear = staYear;
//    }
// --Commented out by Inspection STOP (10/13/2018 10:05 AM)

    public Statistic(double staDay, double staMonth, double staYear) {

        this.staDay = staDay;
        this.staMonth = staMonth;
        this.staYear = staYear;
    }
}
