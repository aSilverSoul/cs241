import java.util.Iterator;

public interface SearchTreeInterface<T> extends TreeInterface<T>
{
    /** searches for a specific entry int this tree.
     * @param entry An object to be found
     * @return True if the object was found in the tree.
     */
    public boolean contains(T entry);

    /** Retrieves a specific entry in this tree.
     * @param entry An object to be found.
     * @return Either the object that was found or null if it doesnt exist.
     */
    public T getEntry(T entry);

    /** Adds a new entry to this tree, if it does not match an existing
     * object in the tree. Otherwise, replaces the existing object with
     * the new entry.
     * @param newEntry An object to be added to the tree.
     * @return Either null if newEntry was not in the tree already, or
     * the existing entry that matched the parameter newEntry and has been replaced int he tree.
     */
    public T add(T newEntry);

    /** Removes a specific entry from this tree.
     * @param entry An object to be removed.
     * @return Either the object thatwas found or null if it doesnt exist in the tree.
     */
    public T remove(T entry);

}
