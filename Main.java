//Gary Machorro
//CS 241
import java.util.Scanner;
public class Main {
    public static void main(String[] args){

        String[] given; //create arrays that will be used to store given information
        int[] compare=new int[30];
        int[] givenInts;
        int flag=0;
        SearchTree tree=new SearchTree();
        boolean noDupes=false;//used for the input validation

        while(noDupes==false)//loop to get no duplicates
        {
            System.out.println("Please enter values: ");
            Scanner scan=new Scanner(System.in);

            String line=scan.nextLine();
            given=line.split(" ");//we scan the numbers and split them, then parse and put into an array
            givenInts=new int[given.length];//create int array same size as the string array
            for (int i=0;i<given.length;i++)
            {
                givenInts[i]=Integer.parseInt(given[i]);
            }
            for(int i=0;i<givenInts.length;i++)
            {
                compare[i]=givenInts[i];//make second array to compare given values later
            }

            for (int i = 0; i < givenInts.length; i++)
            {
                for (int j = i + 1; j < givenInts.length; j++)
                {
                    if (givenInts[i] == givenInts[j])
                        flag=1; //will find a duplicate and set the flag to 1
                }
            }

            if(flag==0)//flag means there is not duplicates
            {
                noDupes=true;
                for (int i=0;i<givenInts.length;i++)//iterate the given ints into the tree
                    tree.add(givenInts[i]);//add the accepted values into the tree
            }
            else
            {
                System.out.print("No duplicates! ");
                flag=0;// resets the flag marker
            }
        }

        System.out.print("Pre-order: ");
        tree.preorderTraverse();
        System.out.println("");
        System.out.print("In-order: ");
        tree.inorderTraverse();
        System.out.println("");
        System.out.print("Post-order: ");
        tree.postorderTraverse();
        System.out.println("");
        System.out.println("Main Menu");
        System.out.println("A: Add a value");
        System.out.println("R: Remove a value");
        System.out.println("E: Exit");

        boolean exit=false;
        while(exit==false)//make a loop to keep asking users what they want to do.
        {
            System.out.print("What command would you like to run? ");
            Scanner scan2=new Scanner(System.in);
            String response=scan2.next();
            if (response.equalsIgnoreCase("A"))
            {
                int flag2=0;//set a flag for duplicates
                System.out.println("");
                System.out.print("Please enter a value to add: ");
                Scanner addnum=new Scanner(System.in);
                String stringAdded=addnum.next();
                int addedInt=Integer.parseInt(stringAdded);//parse the given number
                for (int i=0;i<compare.length;i++)
                {
                    if (addedInt==compare[i])
                        flag2=compare[i];//check for any duplicats

                }
                if (flag2==0)//there was no error
                {
                    tree.add(addedInt);
                    System.out.print("In-order: ");
                    tree.inorderTraverse();
                    System.out.println("");
                }
                else
                {
                    System.out.println(flag2+" already exists. No duplicates allowed.");
                }


            }
            else if (response.equalsIgnoreCase("R"))
            {
                int flag3=0;//create a third flag for this case to check if the number exists
                System.out.println("");
                System.out.print("Please enter a value to Remove: ");
                Scanner removenum=new Scanner(System.in);
                String stringremove=removenum.nextLine();
                int removedInt=Integer.parseInt(stringremove);//we parse the given information
                for (int i=0;i<compare.length;i++)
                {
                    if (removedInt==compare[i])
                        flag3=1;//means there is no problem

                }
                if (flag3==1)//good to go and remove the given number and print the traversal
                {
                    tree.remove(removedInt);
                    System.out.print("In-order: ");
                    tree.inorderTraverse();
                    System.out.println("");
                }
                else
                    System.out.println(removedInt+" does not exist.");
            }
            else if (response.equalsIgnoreCase("E"))//we exit the program
            {
                System.out.println("EXIT!");
                exit=true;
            }
        }





    }

}
