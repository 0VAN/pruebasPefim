package com;

import com.efim.AlgoEFIM;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.pefim.AlgoPEFIM;
import com.utils.DataSetScenarioConfig;
import com.utils.TransactionDatabaseGenerator;
import com.utils.TransactionDatasetUtilityGenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String args[]){
        String CSVinputfile = args[0];
        File outputDirectory = new File(args[1]);
        TransactionDatabaseGenerator transactionDatabaseGenerator = new TransactionDatabaseGenerator();
        TransactionDatasetUtilityGenerator transactionDatasetUtilityGenerator = new TransactionDatasetUtilityGenerator();
        AlgoPEFIM pefim = new AlgoPEFIM();
        AlgoEFIM efim = new AlgoEFIM();

        try {
            if (!outputDirectory.exists()) {
                System.out.println("creating directory: " + outputDirectory.getName());
                boolean result = false;

                try{
                    outputDirectory.mkdir();
                    result = true;
                } catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("DIR created");
                }
            }
            List<DataSetScenarioConfig> beans = new CsvToBeanBuilder(new FileReader(CSVinputfile))
                    .withType(DataSetScenarioConfig.class).withIgnoreLeadingWhiteSpace(true).build().parse();
            CSVWriter writer = new CSVWriter(new FileWriter("resultados.csv"), ',');
            String file=args[1]+"/db.txt";

            List<String[]> output = new ArrayList<String[]>();
            for(int i = 0; i < 1 ; i++){
                int c = 1;
                for(DataSetScenarioConfig cfg:beans){
                    ByteArrayOutputStream baos = transactionDatabaseGenerator.generateDatabase(cfg.numberOfTransactions,cfg.maxDistincItems,cfg.maxItemCountPerTransaction);
                    transactionDatasetUtilityGenerator.convert(baos,file,cfg.maximumQuantity,cfg.multiplicativeFactor);

                    efim.runAlgorithm(cfg.theta1, file, null, true, Integer.MAX_VALUE, true);
                    output.add((c + ",com.efim," + efim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta1, file, null, true, Integer.MAX_VALUE, true, 0);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta1, file, null, true, Integer.MAX_VALUE, true, 1);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta1, file, null, true, Integer.MAX_VALUE, true, 2);
                    output.add((c++ + ",com.pefim," + pefim.returnStats()).split(","));

                    efim.runAlgorithm(cfg.theta2, file, null, true, Integer.MAX_VALUE, true);
                    output.add((c + ",com.efim," + efim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta2, file, null, true, Integer.MAX_VALUE, true, 0);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta2, file, null, true, Integer.MAX_VALUE, true, 1);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta2, file, null, true, Integer.MAX_VALUE, true, 2);
                    output.add((c++ + ",com.pefim," + pefim.returnStats()).split(","));

                    efim.runAlgorithm(cfg.theta3, file, null, true, Integer.MAX_VALUE, true);
                    output.add((c + ",com.efim," + efim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta3, file, null, true, Integer.MAX_VALUE, true, 0);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta3, file, null, true, Integer.MAX_VALUE, true, 1);
                    output.add((c + ",com.pefim," + pefim.returnStats()).split(","));
                    pefim.runAlgorithm(cfg.theta3, file, null, true, Integer.MAX_VALUE, true, 2);
                    output.add((c++ + ",com.pefim," + pefim.returnStats()).split(","));


                }
                writer.writeAll(output);
                output.clear();
            }
            writer.close();


            System.out.println(beans.get(0).numberOfTransactions);
            //File actualFile = new File();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
