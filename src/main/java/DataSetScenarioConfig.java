import com.opencsv.bean.CsvBindByName;

public class DataSetScenarioConfig {
    @CsvBindByName(column = "numberOfTransactions")
    int numberOfTransactions;
    @CsvBindByName(column = "maxDinstincItems")
    int maxDistincItems;
    @CsvBindByName(column = "maxItemCountPerTransaction")
    int maxItemCountPerTransaction;
    @CsvBindByName(column = "maximumQuantity")
    int maximumQuantity;
    @CsvBindByName(column = "multiplicativeFactor")
    int multiplicativeFactor;
    @CsvBindByName(column = "theta1")
    float theta1;
    @CsvBindByName(column = "theta2")
    float theta2;
    @CsvBindByName(column = "theta3")
    float theta3;
}
