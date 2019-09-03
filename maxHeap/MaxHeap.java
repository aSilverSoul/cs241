
import  java.util.Arrays;
public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {
    private  T[] heap;
    private int lastIndex;
    private int swaps;
    private boolean initialized=false;
    private static final int DEFAULT=500;
    private static final int MAX=10000;

    public MaxHeap()//default constructor
    {
        this(DEFAULT);
    }
    public MaxHeap(int initialSize)//constructor that allows to choose size
    {
        if(initialSize<DEFAULT)
            initialSize=DEFAULT;
        T[] tempHeap=(T[])new Comparable[initialSize+1];
        heap=tempHeap;
        lastIndex=0;
        initialized=true;

    }
    public MaxHeap(T[] entries)//constructor that allows the use of the smart heap
    {
        this(entries.length);
        for(int index=0;index<entries.length;index++) {//use the array given and go through it
            heap[index + 1] = entries[index];//put the entries in the class array
            lastIndex++;
        }

        for (int root=lastIndex/2;root>0;root--)//go through and reheap
            reHeap(root);
    }

    public void add(T newEntry) {
        if(initialized==true)
        {
            int newIndex=lastIndex+1;//increase the last index
            int parent=newIndex/2;//parent is the index/2
            while ((parent>0)&& newEntry.compareTo(heap[parent])>0)//if the new entry is greater than the parent
            {
                heap[newIndex]=heap[parent];//swap the new with the parent
                newIndex=parent;
                parent=newIndex/2;
                swaps++;
            }
            heap[newIndex]=newEntry;
            lastIndex++;

        }
        else
            System.out.println("not initialized");

    }

    public T removeMax() {
        T root=null;
        if(initialized==true)
        {
           root=heap[1];//get the top and swap with the last one then reheap
           heap[1]=heap[lastIndex];
           lastIndex--;
           reHeap(1);
        }
        else
            System.out.println("not initialized");
        return root;
    }

    public T getMax() {
        T root=null;
        if(initialized==true)
        {
            if (!isEmpty())//get the root as its the greatest
                root=heap[1];
        }
        else
            System.out.println("not initialized");

        return root;
    }

    public boolean isEmpty() {
        return lastIndex<1;
    }

    public int getSize() {
        return 0;
    }

    public void clear() {
        if(initialized==true)//if it exists go through and set everything to null
        {
            while(lastIndex>-1)
            {
                heap[lastIndex]=null;
                lastIndex--;
            }
            lastIndex=0;
        }
        else
            System.out.println("not initialized");

    }
    private void reHeap(int root)
    {
        boolean done=false;
        T orphan=heap[root];
        int leftChild=2*root;

        while (!done&&(leftChild<=lastIndex))
        {
            int biggerChild=leftChild;//left child is bigger
            int rightChild=leftChild+1;//index of the right
            if((rightChild<=lastIndex)&&heap[rightChild].compareTo(heap[biggerChild])>0)//if the right child is greater set it so
            {
                biggerChild=rightChild;
            }
            if(orphan.compareTo(heap[biggerChild])<0)//the left is greater
            {
                heap[root]=heap[biggerChild];//swap root with the left child
                swaps++;
                root=biggerChild;//set the bigger child as the root now
                leftChild=2*root;
            }
            else
                done=true;
        }
        heap[root]=orphan;
    }
    public void printheap()//prints the heap
    {

        for (int i=1;i<heap.length;i++)
        {
            if(heap[i]!=null)
            System.out.print(heap[i]+" ");//ignores the null values
        }
    }
    public int getSwaps()
    {
        return swaps;
    }
}
