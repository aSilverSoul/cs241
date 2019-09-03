//Gary Machorro
//CS 241
//april 22 2018

public class SearchTree<T extends Comparable<? super T>> implements SearchTreeInterface<T>  {
    private BinaryNode<T> rootNode;//create a root node we will use throughout our methods
    public SearchTree()
    {
        rootNode=null;//set root node to null for our default constructor
    }
    public SearchTree(T rootData)
    {
        rootNode=new BinaryNode<>(rootData);//create a node with the given data for root
    }

    public SearchTree(T rootData,SearchTree<T> leftTree,SearchTree<T> rightTree)
    {
        privateSetTree(rootData,leftTree,rightTree);//use the private method that sets the tree with three values
    }

    private void privateSetTree(T rootData,SearchTree<T> leftTree, SearchTree<T> rightTree)
    {
        rootNode=new BinaryNode<>(rootData);//new rootnode gets created with root data
        if(leftTree!=null)
            rootNode.setLeftChild(leftTree.rootNode);//sets the left tree and is referred by the root
        if (rightTree!=null)
            rootNode.setRightChild(rightTree.rootNode);//sets the right tree if there is one
    }

    public BinaryNode<T> getRootNode() {//returns the root node
        return rootNode;
    }
    public void setRootNode(BinaryNode<T> newRoot)//sets the root node
    {
        rootNode=newRoot;
    }


    /**
     * searches for a specific entry int this tree.
     *
     * @param entry An object to be found
     * @return True if the object was found in the tree.
     */
    public boolean contains(T entry) {
       if(findEntry(getRootNode(), entry)!=null)//calls findentry and if it finds something it returns true else false
           return true;
        return false;
    }

    /**
     * Retrieves a specific entry in this tree.
     *
     * @param entry An object to be found.
     * @return Either the object that was found or null if it does not exist.
     */
    public T getEntry(T entry) {//calls find entry as well but also returns the object

        return findEntry(getRootNode(), entry);
    }
    private T findEntry(BinaryNode<T> rootNode, T entry)
    {
        T result=null;//creates the result as null
        if (rootNode!=null)//if root node is not null,then it will go down the tree and call itself until its a leaf node
        {
            T rootEntry = rootNode.getData();
            if (entry.equals(rootEntry))//if it finds the node with the entry, it stops
                result=rootEntry;
            else if(entry.compareTo(rootEntry)<0)
                result = findEntry(rootNode.getLeftChild(),entry);//if it does not find the entry it compares, because left is less, we go  to left child recursively
            else
                result = findEntry(rootNode.getRightChild(),entry);//if it is larger, than the entry, it goes to the right and is called recursively
        }
        return result;
    }

    /**
     * Adds a new entry to this tree, if it does not match an existing
     * object in the tree. Otherwise, replaces the existing object with
     * the new entry.
     *
     * @param newEntry An object to be added to the tree.
     * @return Either null if newEntry was not in the tree already, or
     * the existing entry that matched the parameter newEntry and has been replaced in the tree.
     */
    public T add(T newEntry) {//adds by first checking if the tree is empty to add the new entry as the root
        T result=null;
        if(isEmpty())
            setRootNode(new BinaryNode<T>(newEntry));
        else {
            result = addEntry(getRootNode(), newEntry);//if its not empty it calls the private method addentry
        }
        return result;
    }
    private T addEntry(BinaryNode<T> rootNode, T newEntry)//addentry uses the root and the new entry to add to the tree
    {
        T result=null;
        int comparison=newEntry.compareTo(rootNode.getData());//we create a comparison to know where to move in tree

        if(comparison==0)//if the new entry and the root are equal, then we set the root data with the new data
        {
            result=rootNode.getData();
            rootNode.setData(newEntry);//set the data passed in with the root node
        }
        else if(comparison<0)//if the new entry is less than the root data
        {
            if(rootNode.hasLeftChild())
                result=addEntry(rootNode.getLeftChild(),newEntry);//go recursively into the left child if there is a left child
            else
                rootNode.setLeftChild(new BinaryNode<T>(newEntry));//no left child, then we set the left child
        }
        else
        {
            if(rootNode.hasRightChild())//check if there is a right child
                result=addEntry(rootNode.getRightChild(),newEntry);//we go recursively if there is a right child
            else
                rootNode.setRightChild(new BinaryNode<T>(newEntry));//no right child, we se the left child
        }
        return result;
    }

    /**
     * Removes a specific entry from this tree.
     *
     * @param entry An object to be removed.
     * @return Either the object thatwas found or null if it doesnt exist in the tree.
     */
    public T remove(T entry) {//removes a given entry
       BinaryNode<T> oldEntry=new BinaryNode<T>(null);//create a node with the old entry that is null
       BinaryNode<T> newRoot=removeEntry(getRootNode(),entry,oldEntry);//calls private method removeEntry and passes in root, given entry, and null old entry
       setRootNode(newRoot);//we set the root node of the given entry with a new root gathered by the line above

       return oldEntry.getData();//we return the data of node removed
    }

    public void inorderTraverse()
    {
        inorderTraverse(rootNode);//calls the recursive method below for in order traversal
    }
    private void inorderTraverse(BinaryNode<T> givenNode)//traverse left root right
    {
        if(givenNode!=null)//if there is a node then we traverse the tree
        {
            inorderTraverse(givenNode.getLeftChild());//we traverse the left child first
            System.out.print(givenNode.getData()+" ");//we then print the data
            inorderTraverse(givenNode.getRightChild());//we then traverse the right child

        }
    }

    public void preorderTraverse()
    {
        preorderTraverse(rootNode);//same as above call the private methods
    }
    private void preorderTraverse(BinaryNode<T> givenNode)//traverse root left right
    {
        if(givenNode!=null)
        {
            System.out.print(givenNode.getData()+" ");//process the root first
            preorderTraverse(givenNode.getLeftChild());//traverse the left
            preorderTraverse(givenNode.getRightChild());//traverse the right
        }
    }

    public void postorderTraverse()
    {
        postorderTraverse(rootNode);
    }
    private void postorderTraverse(BinaryNode<T> givenNode)//traverse left right root
    {
        if(givenNode!=null)
        {
            postorderTraverse(givenNode.getLeftChild());//process the left first
            postorderTraverse(givenNode.getRightChild());//then the right child
            System.out.print(givenNode.getData()+" ");//process the root
        }
    }

    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry,BinaryNode<T> oldEntry)
    {
        if(rootNode!=null)//as long as there is a root node we carry on with the method
        {
            T rootData=rootNode.getData();//get the data from the root
            int compare=entry.compareTo(rootData);//we then do a compareTo(will give us a int value)
            if(compare==0)//if the given entry is the same as the root we set the old entry with the root data
            {
              oldEntry.setData(rootData);
              rootNode=removeFromRoot(rootNode);//use another method that removes the node from the tree
            }
            else if(compare<0)//if the passed data is less than the root data we move to the left
            {
                BinaryNode<T> leftChild=rootNode.getLeftChild();//we  get the left child
                BinaryNode<T> subTreeRoot=removeEntry(leftChild,entry,oldEntry);//we move down the tree with recursion passing in leftchild as the root

                rootNode.setLeftChild(subTreeRoot);//what we find from the recursion we set is as the left child of root node
            }
            else
            {
                BinaryNode<T> rightChild=rootNode.getRightChild();//get the right child
                rootNode.setRightChild(removeEntry(rightChild,entry,oldEntry));//set the right child of root node with result of recursion passing in right child from above
            }

        }
        return rootNode;
    }


    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)//removes the entry in a given root of a subtree
    {
        if(rootNode.hasLeftChild()&& rootNode.hasRightChild())//when the root has two children
        {
            BinaryNode<T> leftSubtreeRoot=rootNode.getLeftChild();//find the rightmost entry in left subtree
            BinaryNode<T> largestNode=findLargest(leftSubtreeRoot);//use findlargest to do so

            rootNode.setData(largestNode.getData());//replace the entry in root

            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));//remove rightmost node in left subtree (the swap and delete)
        }
        else if(rootNode.hasRightChild())//there is one child
            rootNode=rootNode.getRightChild();//we get right
        else
            rootNode=rootNode.getLeftChild();//we get left
        return rootNode;
    }

    private BinaryNode<T> findLargest(BinaryNode<T> rootNode)//we find the largest node
    {
        if(rootNode.hasRightChild())//we go to the right child and then to the most right
            rootNode=findLargest(rootNode.getRightChild());
        return rootNode;
    }
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasRightChild())//go to the rightmost child or set the root to left child
        {
            BinaryNode<T> rChild=rootNode.getRightChild();//pass the right child for use in the next line
            BinaryNode<T> root=removeLargest(rChild);//recursively pass in the right child and set it as root for next recursion
            rootNode.setRightChild(root);//sets found root as right child of the root node.
        }
        else
            rootNode=rootNode.getLeftChild();//set root as left child
        return rootNode;//return the largest node found
    }

    public T getRootData() {
        return null;                  //none of these methods are used in the program
    }

    public int getHeight() {
        return 0;
    }

    public int getNumberOfNodes() {
        return 0;
    }

    public boolean isEmpty() {
        if(rootNode==null)
            return true;
        return false;
    }

    public void clear() {

    }

    private class BinaryNode<T>
    {
        private T data;
        private BinaryNode<T> leftChild;
        private BinaryNode<T> rightChild;

        public BinaryNode()
        {
            this(null);
        }

        public BinaryNode(T data)
        {
            this(data,null,null);//basic node implementation with rootdata and two children
        }
        public BinaryNode(T data, BinaryNode<T> newLeftChild,
                           BinaryNode<T> newRightChild) {
            this.data = data;
            leftChild = newLeftChild;
            rightChild=newRightChild;
        }

        public T getData() {
            return data;
        }

        public BinaryNode<T> getLeftChild() {
            return leftChild;
        }

        public BinaryNode<T> getRightChild() {
            return rightChild;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeftChild(BinaryNode<T> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(BinaryNode<T> rightChild) {
            this.rightChild = rightChild;
        }

        public boolean hasLeftChild()
        {
            return leftChild !=null;
        }

        public boolean hasRightChild()
        {
            return rightChild !=null;
        }

        public boolean isLeaf()
        {
            return (leftChild==null)&&(rightChild==null);
        }
    }
}

