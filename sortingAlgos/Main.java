import java.util.Arrays;
import java.util.Random;
public class Main {
    public static int[] numbers=new int[100000];//this will allow me to change the number of elements being sorted

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();//starts the counter in beginning of the program
        createArray();
        int low=0;
        int high=numbers.length-1;
        mergeSort(numbers,low,high);//here i can change the type of sorting used


        long endTime = System.currentTimeMillis();//gets the time at the end of the sorting
        System.out.println("Took "+(endTime - startTime) + " milliseconds");//prints the time out

    }
    public static int[] createArray()//this method creates and array with random integers
    {
        Random rand=new Random();
        //Generates 10 Random Numbers in the range 1 -20
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(10000000);//can change the bound or random ints
        }//end for loop
        return numbers;
    }
    public static void bubbleSort(int[] given)//this is the bubble sort
    {
        int temp;//temporary element
        for(int i=0;i<given.length-1;i++)//iterate it through
        {
            for (int j=0;j<given.length-(i+1);j++)//iterate again with one element to compare to another
            {
                if (given[j]>given[j+1]) {
                    temp = given[j];//swaps happen here when the value is smaller than the next
                    given[j] = given[j + 1];
                    given[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] given)//selection sort method
    {
        for(int i=0;i<given.length-1;i++)//go through unsorted array
        {
            int min=i;//find the minimun element in the unsorted array
            for (int j=i+1;j<given.length;j++)
            {
                if(given[j]<given[min])
                {
                    min=j;
                }
            }
            if(i!=min)//swap the minimum element in the array to sort
            {
                int swap=given[i];
                given[i]=given[min];
                given[min]=swap;
            }
        }
    }

    public static void insertionSort(int[] given)//method for insertion sort
    {
        int value;
        int j;
        for(int i=1;i<given.length;i++)//iterate through the array
        {
            value=given[i];
            j=i-1;
            while (j>=0&&given[j]>value)//compare the value with with element before it
            {
                given[j+1]=given[j];//when smaller then we set the next value equal to previous
                j=j-1;
            }
            given[j+1]=value;//set j+1 as new value for comparisons
        }
    }
    public static void mergeSort(int[] given,int low,int high)
    {
        int middle;
        if(low<high)
        {
            middle=(low+high)/2;//we get the middle of the array
            mergeSort(given,low,middle);//recursive calls
            mergeSort(given,middle+1,high);
            merge(given,low,middle,high);//this part gets the others and puts them together
        }
    }
    public static void merge(int[] given,int low,int middle,int high)
    {
        int aListEnd= middle;//the start of the first part of the list
        int bListStart = middle + 1;//second half of the list
        while ((low <= aListEnd) && (bListStart <= high))//if between low and high
        {
            if (given[low] < given[bListStart]) {//we compare
                low++;
            } else {
                int temp = given[bListStart];//we have a temp value
                for (int j = bListStart-1; j >= low; j--) {//we sort here
                    given[j+1] = given[j];
                }
                given[low] = temp;//set temp to low
                low++;//now we update our values
                aListEnd++;
                bListStart++;
            }
        }
    }

    public static void quickSort(int given[],int low,int high)
    {
        if(low<high)
        {
            int pivot=divide(given,low,high);//this is our pivot point we call the divide method
            quickSort(given,low,pivot-1);//this is hte recursive call with the pivot used as the high index
            quickSort(given,pivot+1,high);//low index used here
        }
    }

    public static int divide(int[] given,int low,int high)//divide method that returns teh pivot
    {
        int pivot=given[high];
        int i=low-1;
        for (int j=low;j<high;j++)
        {
            if(given[j]<=pivot)//the elements is less than pivot
            {
                i++;
                int temp=given[i];//we swap
                given[i]=given[j];
                given[j]=temp;
            }
        }
        int temp=given[i+1];//we then just end with another swap
        given[i+1]=given[high];
        given[high]=temp;
        return i+1;//return the index as the pivot
    }
}
