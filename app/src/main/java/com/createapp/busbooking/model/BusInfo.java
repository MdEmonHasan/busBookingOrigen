package com.createapp.busbooking.model;

import java.util.List;

public class BusInfo {
    String busID, busName, busStartLocation, busDestination, busImage,busPrice,numberOfSetInLeftSide,numberOfSetInRightSide;


    public BusInfo(String busID, String busName, String busStartLocation, String busDestination, String busImage, String busPrice, String numberOfSetInLeftSide, String numberOfSetInRightSide) {
        this.busID = busID;
        this.busName = busName;
        this.busStartLocation = busStartLocation;
        this.busDestination = busDestination;
        this.busImage = busImage;
        this.busPrice = busPrice;
        this.numberOfSetInLeftSide = numberOfSetInLeftSide;
        this.numberOfSetInRightSide = numberOfSetInRightSide;
    }

    public BusInfo() {
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusStartLocation() {
        return busStartLocation;
    }

    public void setBusStartLocation(String busStartLocation) {
        this.busStartLocation = busStartLocation;
    }

    public String getBusDestination() {
        return busDestination;
    }

    public void setBusDestination(String busDestination) {
        this.busDestination = busDestination;
    }

    public String getBusImage() {
        return busImage;
    }

    public void setBusImage(String busImage) {
        this.busImage = busImage;
    }

    public String getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(String busPrice) {
        this.busPrice = busPrice;
    }

    public String getNumberOfSetInLeftSide() {
        return numberOfSetInLeftSide;
    }

    public void setNumberOfSetInLeftSide(String numberOfSetInLeftSide) {
        this.numberOfSetInLeftSide = numberOfSetInLeftSide;
    }

    public String getNumberOfSetInRightSide() {
        return numberOfSetInRightSide;
    }

    public void setNumberOfSetInRightSide(String numberOfSetInRightSide) {
        this.numberOfSetInRightSide = numberOfSetInRightSide;
    }
}
