import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String[] given;//used to hold inputed values
        int randomSize=1000;//used for the collections to shuffle the numbers
        int[] compare=new int[30];
        int[] givenInts;
        int flag=0;//used for input validation
        MaxHeap heapReg=new MaxHeap(10);
        boolean noDupes=false;//used for the input validation

        System.out.println("press 1 for random integers or press 2 to add in your numbers");
        Scanner scanChoice=new Scanner(System.in);
        int choice = scanChoice.nextInt();
        if(choice==2) {//if the user chooses to insert their numbers

            while (noDupes == false)//loop to get no duplicates
            {
                System.out.println("Please enter values: ");
                Scanner scan = new Scanner(System.in);

                String line = scan.nextLine();
                given = line.split(" ");//we scan the numbers and split them, then parse and put into an array
                givenInts = new int[given.length];//create int array same size as the string array
                for (int i = 0; i < given.length; i++) {
                    givenInts[i] = Integer.parseInt(given[i]);
                }
                for (int i = 0; i < givenInts.length; i++) {
                    compare[i] = givenInts[i];//make second array to compare given values later
                }

                for (int i = 0; i < givenInts.length; i++) {
                    for (int j = i + 1; j < givenInts.length; j++) {
                        if (givenInts[i] == givenInts[j])
                            flag = 1; //will find a duplicate and set the flag to 1
                    }
                }

                if (flag == 0)//flag means there is not duplicates
                {
                    Integer[] givenValues = new Integer[givenInts.length];//create a object array to pass to the constructor
                    noDupes = true;
                    for (int i = 0; i < givenInts.length; i++)//iterate the given ints into the tree
                    {
                        heapReg.add(givenInts[i]);//add the accepted values into the tree
                        givenValues[i] = Integer.valueOf(givenInts[i]);//copy values
                    }
                    MaxHeap heapSmart = new MaxHeap(givenValues);//pass object array to the constructor.
                    System.out.println("Printing Smart Heap");
                    heapSmart.printheap();
                    System.out.println("");
                    System.out.print("Smart Swaps: ");
                    System.out.println(heapSmart.getSwaps());

                    System.out.println("");
                    System.out.println("Printing Regular Heap");
                    heapReg.printheap();
                    System.out.println("");
                    System.out.print("Regular Swaps: ");
                    System.out.println(heapReg.getSwaps());


                } else {
                    System.out.print("No duplicates! ");
                    flag = 0;// resets the flag marker
                }
            }
        }

        else//if the number chooses to have random numbers in the heap
        {
            Integer[] arr = new Integer[randomSize];//create the array of 1000 numbers
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            Collections.shuffle(Arrays.asList(arr));//shuffle the numbers
            Integer[] smartRandomValues = new Integer[100];//create an Integer array to pass into smart array
            int[] randomValues=new int[100];//create array to hold first 100 ints
            for (int i = 0; i < randomValues.length; i++)//get the first 100 numbers from the shuffled collection
            {
                randomValues[i]=arr[i];
            }
            for (int i = 0; i < randomValues.length; i++)//iterate the given ints into the heap
            {
                heapReg.add(randomValues[i]);//add to heap
                smartRandomValues[i] = Integer.valueOf(randomValues[i]);//copy values into Integer[] array
            }

            MaxHeap heapSmart = new MaxHeap(smartRandomValues);//pass object array to the constructor.
            System.out.println("Printing Smart Heap");//print the items
            heapSmart.printheap();
            System.out.println("");
            System.out.print("Smart Swaps: ");
            System.out.println(heapSmart.getSwaps());

            System.out.println("");
            System.out.println("Printing Regular Heap");
            heapReg.printheap();
            System.out.println("");
            System.out.print("Regular Swaps: ");
            System.out.println(heapReg.getSwaps());

        }



    }
}
