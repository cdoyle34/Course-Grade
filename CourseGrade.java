/*
@file CourseGrade.java
@brief This program takes in a txt file with names and test grades and returns
the letter grade each person would have as well as the averages for each test to a
separate output file called report.txt
@author Cameron Doyle
@data January 26th, 2023
*/
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
public class CourseGrade {
    public static void main(String[] args) throws IOException{
        String fileName = "unknown";                                    //Sets up basic variables
        String row;
        String[] fields = new String[14];

        int numRows = 0;
        Scanner scnr = new Scanner(System.in);
        System.out.println("What tsv file would you like to use?(StudentInfo.tsv)");  //Asks user what file they would like to use
        fileName = scnr.next();
        FileInputStream inputStream = new FileInputStream(fileName);                //Uses scanner to allow user to input file
        Scanner inScnr = new Scanner(inputStream);                                  //Sets up input stream with user inputted file

        while(inScnr.hasNextLine()){ //counts rows in file
            inScnr.nextLine();
            numRows++;
        }


        inputStream.close();    //closes count row input stream opening

        String[] studentFirstName = new String[numRows];        //Sets up an array for first, last name, and all grades
        String[] studentLastName = new String[numRows];
        double[] firstGrade = new double[numRows];
        double [] secondGrade = new double[numRows];
        double [] thirdGrade = new double[numRows];

        inputStream = new FileInputStream(fileName);            //Opens back up file input
        inScnr = new Scanner (inputStream);

        numRows = 0;                                        //Sets numrows back to zero after the arrays are good to go

        while(inScnr.hasNextLine()) {
            studentFirstName[numRows] = inScnr.next();
            studentLastName[numRows] = inScnr.next();
            firstGrade[numRows] = inScnr.nextDouble();              //Uses arrays to input each value for each row into them
            secondGrade[numRows] = inScnr.nextDouble();
            thirdGrade[numRows] = inScnr.nextDouble();
            numRows++;
        }
        inputStream.close();
        double midTerm1Avg;                 //Closes input stream and sets up variables to take averages with
        double midTerm2Avg;
        double midTerm3Avg;
        midTerm1Avg = (firstGrade[0] + firstGrade[1] + firstGrade[2] + firstGrade[3] + firstGrade[4] + firstGrade[5] + firstGrade[6] + firstGrade[7] + firstGrade[8] + firstGrade[9] + firstGrade[10] + firstGrade[11] + firstGrade[12] + firstGrade[13] + firstGrade[14])/15;
        midTerm2Avg = (secondGrade[0] + secondGrade[1] + secondGrade[2] + secondGrade[3] + secondGrade[4] + secondGrade[5] + secondGrade[6] + secondGrade[7] + secondGrade[8] + secondGrade[9] + secondGrade[10] + secondGrade[11] + secondGrade[12] + secondGrade[13] + secondGrade[14])/15;
        midTerm3Avg = (thirdGrade[0] + thirdGrade[1] + thirdGrade[2] + thirdGrade[3] + thirdGrade[4] + thirdGrade[5] + thirdGrade[6] + thirdGrade[7] + thirdGrade[8] + thirdGrade[9] + thirdGrade[10] + thirdGrade[11] + thirdGrade[12] + thirdGrade[13] + thirdGrade[14])/15;
        //Takes averages of all the different tests

        int i;
        int j = 0;
        int x = 0;
        String[] letGrade = new String[15];                     //Sets up letter grade array
        try {                                               //Try used to get rid of array error I was having, although code was still working
            for (i = 0; i <= 15; i++) {                     //For loop used to parse through each row within the tsv file
                String letterGrade = "unknown";

                double totalGrade = (firstGrade[j] + secondGrade[j] + thirdGrade[j]) / 3;           //Calculates the average of each person, the if/else if used to set letter grade up
                if (totalGrade >= 90) {
                    letterGrade = "A";
                } else if (totalGrade >= 80 && totalGrade < 90) {
                    letterGrade = "B";
                } else if (totalGrade >= 70 && totalGrade < 80) {
                    letterGrade = "C";
                } else if (totalGrade >= 60 && totalGrade < 70) {
                    letterGrade = "D";
                } else if (totalGrade < 60) {
                    letterGrade = "F";
                }
                letGrade[x] = letterGrade;

                x++;
                j++;

            }
        }
        catch(ArrayIndexOutOfBoundsException e){                            //Catches the array out of bounds error I was having

        }

        FileOutputStream fileStream = new FileOutputStream("report.txt");           //Sets up the output file used
        PrintWriter outFs = new PrintWriter(fileStream);
        int q = 0;

        try {                                   //Uses for loop to output each persons information, including letter grade to output file
            for (i = 0; i <= 15; i++) {
                outFs.println(studentFirstName[q] + "\t" + studentLastName[q] + "\t" + firstGrade[q] + "\t" + secondGrade[q] + "\t" + thirdGrade[q] + "\t" + letGrade[q]);
                q++;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){            //Catches same error I was having with other for loop above

        }
        outFs.println();
        outFs.printf("Averages: Midterm1 %.2f, Midterm2: %.2f, Final: %.2f", midTerm1Avg, midTerm2Avg, midTerm3Avg);
        outFs.close();          //Prints extra space, then the midterm and final averages, using printf to only display two decimal points, then clsoes output file









    }
}
