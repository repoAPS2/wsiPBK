package apilipen.potterybarnkids.www.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BaseUtils {

   // public  static  final String dataSource = System.getProperty("csv"); // "src/test/java/resourses/testData/testData1.csv"

  public static final  String fileName = "testData1";

   public static String  filePath = "src/test/java/resourses/testData/"  + fileName + ".csv";

    public    static String[] [] readInputDataTestID  (String testID)throws IOException {  // , String filePath
        String [][] test = null;
        int row = 0;
        String cvsLine = "";
        String[] a = null;
        ArrayList<String[]> al = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        System.out.println("readInputDataTestID for  parsing CSV  is running");
        while ((cvsLine = br.readLine()) != null ) {
            a = cvsLine.split(",");
            if (a[0].equalsIgnoreCase(testID)) {
                System.out.println("first field is:  "   +   a[0]);
                test = new String [1][a.length];
                for (int n=0; n<a.length; n++) {
                    test[0][n] = a[n];
                }
            }
        }
        br.close();
        System.out.println("Done parsing CSV");
        return test;
    }




}
