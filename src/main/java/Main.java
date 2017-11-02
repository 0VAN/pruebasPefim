import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;


public class Main {
    public static void main(String args[]){
        String CSVinputfile = args[0];
        File outputDirectory = new File(args[1]);
        TransactionDatabaseGenerator transactionDatabaseGenerator = new TransactionDatabaseGenerator();
        TransactionDatasetUtilityGenerator transactionDatasetUtilityGenerator = new TransactionDatasetUtilityGenerator();

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
            for(DataSetScenarioConfig cfg:beans){
                ByteArrayOutputStream baos = transactionDatabaseGenerator.generateDatabase(cfg.numberOfTransactions,cfg.maxDistincItems,cfg.maxItemCountPerTransaction);
                transactionDatasetUtilityGenerator.convert(baos,args[1]+"/db"+c+++".txt",cfg.maximumQuantity,cfg.multiplicativeFactor);
            }

            System.out.println(beans.get(0).numberOfTransactions);
            //File actualFile = new File();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
