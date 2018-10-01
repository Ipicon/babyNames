import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalB =0;
        int totalG =0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            int births = Integer.parseInt(record.get(2));
            totalBirths += births;
            if(record.get(1).equals("M")){
                totalBoys += births;
                totalB++;
            }
            else{
                totalGirls += births;
                totalG++;
            }
        }
        System.out.println("Total births: " + totalBirths +
                           "\nTotal boys: " + totalBoys + " " + totalB +
                           "\nTotal girls: " + totalGirls + " " + totalG);
    }
    public int getRank(int year, String name, String gender){
        //String file = ("testing/yob" + year + "short.csv");
        String file = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(file);
        int rankBoys = 0;
        int rankGirls = 0;
        int rank = -1;
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(gender.equals("M") && record.get(1).equals("M")) {
                rankBoys++;
                if(name.equals(record.get(0)))
                    rank = rankBoys;
            }
            else if(gender.equals("F") && record.get(1).equals("F")){
                rankGirls++;
                if(name.equals(record.get(0)))
                    rank = rankGirls;
            }
        }
        return rank;
    }
    public String getName(int year, int rank, String gender) {
        //String file = ("testing/yob" + year + "short.csv");
        String file = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(file);
        String name = "NO NAME";
        int rankBoys = 0;
        int rankGirls = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(gender.equals("M") && record.get(1).equals("M")) {
                rankBoys++;
                if(rankBoys == rank)
                   name = record.get(0); 
            }
            else if(gender.equals("F") && record.get(1).equals("F")){
                rankGirls++;
                if(rankGirls == rank)
                    name = record.get(0); 
            }
        }
        return name;
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        System.out.println(name + " born in " + year + 
                           " would be " + newName + " if she/he was born is " + newYear);
    }
    public int yearOfHighestRank(String name,String gender) {
        DirectoryResource dr = new DirectoryResource();
        int yearInt = 0;
        int highestRank = 0;
        int highestYear = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int yearO = (f.getName()).indexOf("1");
            int yearS = (f.getName()).indexOf("2");
            if(yearO < yearS){
                if(yearO != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearO,end);
                    yearInt = Integer.parseInt(year);
                }
                else if(yearS != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearS,end);
                    yearInt = Integer.parseInt(year);
                }
            }
            if(yearO > yearS){
                if(yearS != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearS,end);
                    yearInt = Integer.parseInt(year);
                }
                else if(yearO != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearO,end);
                    yearInt = Integer.parseInt(year);
                }
            }
            int rank = getRank(yearInt,name,gender);
            if(rank == -1)
                yearInt = -1;
            if(highestRank == 0 && rank != -1){
                highestRank = rank;
                highestYear = yearInt;
            }
            else if( highestRank > rank && rank != -1){
                highestRank = rank;
                highestYear = yearInt;
            }
        }
        return highestYear;
    }
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int yearInt = 0;
        double rankCounter = 0.0;
        double counter = 0.0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int yearO = (f.getName()).indexOf("1");
            int yearS = (f.getName()).indexOf("2");
            if(yearO < yearS){
                if(yearO != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearO,end);
                    yearInt = Integer.parseInt(year);
                }
                else if(yearS != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearS,end);
                    yearInt = Integer.parseInt(year);
                }
            }
            if(yearO > yearS){
                if(yearS != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearS,end);
                    yearInt = Integer.parseInt(year);
                }
                else if(yearO != -1) {
                    //int end = (f.getName()).indexOf("s");
                    int end = (f.getName()).indexOf(".csv");
                    String year = (f.getName()).substring(yearO,end);
                    yearInt = Integer.parseInt(year);
                }
            }
            int rank = getRank(yearInt,name,gender);
            if(rank != -1) {
                rankCounter += rank ;
                counter++;
            }
        }
        if(counter == 0)
            return -1.0;
        double avg = rankCounter/counter + 0.0;
        return avg;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String file = ("us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(file);
        int kids = 0;
        int rankCounter = 0;
        int rank = getRank(year,name,gender);
        for(CSVRecord record : fr.getCSVParser(false)) {
            if(gender.equals(record.get(1))) {
                rankCounter++;
                if(rankCounter >= rank) 
                    break;
                int higherKids = Integer.parseInt(record.get(2));
                kids += higherKids;
            }
        }
        return kids;
    }
    public void test(){
        //System.out.println("The rank is: " + getRank(1971,"Frank","M"));
        //System.out.println("The name is: " + getName(1982,450,"M"));
        //whatIsNameInYear("Owen",1974,2014,"M");
        //System.out.println("Year: " + yearOfHighestRank("Genevieve","F"));
        //System.out.println("Average: " + getAverageRank("Robert" , "M"));
        //System.out.println("Kids: " + getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
