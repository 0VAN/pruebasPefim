package com.utils;

import com.opencsv.bean.CsvBindByName;

public class DataSetScenarioConfig {
    @CsvBindByName(column = "numberOfTransactions")
    public
    int numberOfTransactions;
    @CsvBindByName(column = "maxDinstincItems")
    public
    int maxDistincItems;
    @CsvBindByName(column = "maxItemCountPerTransaction")
    public
    int maxItemCountPerTransaction;
    @CsvBindByName(column = "maximumQuantity")
    public
    int maximumQuantity;
    @CsvBindByName(column = "multiplicativeFactor")
    public
    int multiplicativeFactor;
    @CsvBindByName(column = "theta1")
    public
    double theta1;
    @CsvBindByName(column = "theta2")
    public
    double theta2;
    @CsvBindByName(column = "theta3")
    public
    double theta3;
}
