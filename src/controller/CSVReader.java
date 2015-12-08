//package controller;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * Created by Tobias on 07/12/15.
// */
//public class CSVReader {
//
//
//    public class reader {
//        private Scanner sc;
//        ArrayList<String> tokens = new ArrayList<String>();
//
//        // 1st Step: open the file
//        public void openFile() {
//            try {
//                sc = new Scanner(new File("rates.txt"));
//            } catch (Exception e) {
//                System.out.println("ERROR");
//            }
//        }
//
//        // 2nd Step: read lines from the file and store in ArrayList
//        public void readFile() {
//            while (sc.hasNext()) {
//                currencies.add(new Currency(sc.next(), Double.parseDouble(sc.next())));
//            }
//            sc.close();
//        }
//
//        public void printOut() {
//            for (int i = 0; i < currencies.size(); i++) {
//                System.out.print(currencies.get(i).getName() + " ");
//                System.out.println(currencies.get(i).getRate());
//            }
//        }
//
//        public int sizeOf() {
//            return currencies.size();
//        }
//
//        public String getName(int i) {
//            return currencies.get(i).getName();
//        }
//
//        public double getRate(int i) {
//            return currencies.get(i).getRate();
//        }
//
//    }
//
//
//}
