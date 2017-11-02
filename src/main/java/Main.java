import com.opencsv.bean.CsvToBeanBuilder;
import pefim.AlgoPEFIM;

import java.io.*;
import java.util.List;


public class Main {
    public static void main(String args[]){
        String CSVinputfile = args[0];
        File outputDirectory = new File(args[1]);
        TransactionDatabaseGenerator transactionDatabaseGenerator = new TransactionDatabaseGenerator();
        TransactionDatasetUtilityGenerator transactionDatasetUtilityGenerator = new TransactionDatasetUtilityGenerator();
        AlgoPEFIM algo = new AlgoPEFIM();
        try {
            if (!outputDirectory.exists()) {
                System.out.println("creating directory: " + outputDirectory.getName());
                boolean result = false;

                try{
                    outputDirectory.mkdir();
                    result = true;
                }
                catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("DIR created");
                }
            }
            List<DataSetScenarioConfig> beans = new CsvToBeanBuilder(new FileReader(CSVinputfile))
                    .withType(DataSetScenarioConfig.class).withIgnoreLeadingWhiteSpace(true).build().parse();
            int c = 1;
            String file = "";
            for(DataSetScenarioConfig cfg:beans){
                ByteArrayOutputStream baos = transactionDatabaseGenerator.generateDatabase(cfg.numberOfTransactions,cfg.maxDistincItems,cfg.maxItemCountPerTransaction);
                file=args[1]+"/db"+c+++".txt";
                transactionDatasetUtilityGenerator.convert(baos,,cfg.maximumQuantity,cfg.multiplicativeFactor);
                algo.runAlgorithm(cfg.theta1, file, "", true, Integer.MAX_VALUE, true);

            }

            System.out.println(beans.get(0).numberOfTransactions);
            //File actualFile = new File();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
