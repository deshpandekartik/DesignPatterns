package studentCoursesBackup.util;
import studentCoursesBackup.myTree.Node;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Code borrowed from geeksforgeeks website
 * https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
 */

public class TreeBuilder
{
    private Node root;
    private ArrayList<TreeBuilder> treelist = new ArrayList<TreeBuilder>();

    /**
     * Constructor empty
     */
    public TreeBuilder(String backuptree)
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        root = null;
    }

    /**
     * Constructot
     */
    public TreeBuilder()
    {
        MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        root = null;
        treelist.add(this);
        treelist.add(new TreeBuilder(""));    // first backup tree
        treelist.add(new TreeBuilder(""));    // second backup tree
    }

    /**
     * @return Tree lsit , orignal and two  backup trees
     */
    public ArrayList<TreeBuilder> gettreelist()
    {
        return treelist;
    }

    /**
     * This method mainly calls deleteRec()
     * @param key
     */
    private void deleteNodeKey(int key)
    {
        root = this.deleteNodeRec(root, key);
    }

    /**
     * A recursive function to delete a node in BST
     * @param root
     * @param key
     * @return
     */
    private Node deleteNodeRec(Node root, int key)
    {
        if (root == null)  return root;

        if (key < root.getbNumber() )
            root.setLeft(this.deleteNodeRec(root.getLeft(), key));
        else if (key > root.getbNumber())
            root.setRight(this.deleteNodeRec(root.getRight(), key));

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.setbNumber(this.minValue(root.getRight()));
            root.setAllCourses(this.minValueCourse(root.getRight()));

            // Delete the inorder successor
            root.setRight(this.deleteNodeRec(root.getRight(), root.getbNumber()));
        }

        return root;
    }

    /**
     * Which part to traverse to
     * @param root
     * @return
     */
    private int minValue(Node root)
    {
        int minv = root.getbNumber();
        while (root.getLeft() != null)
        {
            minv = root.getLeft().getbNumber();
            root = root.getLeft();
        }
        return minv;
    }

    /**
     * Which part to traverse to
     * @param root
     * @return
     */
    private ArrayList<String> minValueCourse(Node root)
    {
        int minv = root.getbNumber();
        ArrayList<String> temp = root.getAllCourses();
        while (root.getLeft() != null)
        {
            temp = root.getLeft().getAllCourses();
            minv = root.getLeft().getbNumber();
            root = root.getLeft();
        }
        return temp;
    }

    /**
     * This method mainly calls insertRec()
     * @param incoming
     */
    public void insert(Node incoming)
    {
        int key = incoming.getbNumber();
        String a_course = incoming.getAdded_course();
        root = this.insertRec(root, incoming);
    }

    /**
     * A recursive function to insert a new key in BST
     * @param root
     * @param key
     * @return
     */
    private Node insertRec(Node root, Node incoming)
    {
        int key = incoming.getbNumber();
        String new_course = incoming.getAdded_course();

        /* If the tree is empty, return a new node */
        if (root == null)
        {
            root = incoming;
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.getbNumber())
            root.setLeft(this.insertRec(root.getLeft(), incoming));
        else if (key > root.getbNumber())
            root.setRight(this.insertRec(root.getRight(), incoming));

        /* return the (unchanged) node pointer */
        return root;
    }

    /**
     * A recursive function to search a node with specified  key
     * @param key
     * @return
     */
    public Node SearchNode(int key)
    {
        Node temp = root;
        while ( temp != null )
        {
            if ( temp.getbNumber() == key )
            {
                // found the node
                return temp;
            }
            else if ( temp.getbNumber() > key )
            {
                temp = temp.getLeft();
            }
            else if ( temp.getbNumber() < key )
            {
                temp = temp.getRight();
            }
        }
        return null;
    }

    /**
     * Delete a particular course for a node
     * @param key
     * @return
     */
    public void deleteCourse(int key, String a_course)
    {
        Node searchedNode = this.SearchNode(key);
        if ( searchedNode != null )
        {
            if ( searchedNode.getAllCourses().contains(a_course))
            {
                searchedNode.getAllCourses().remove(a_course);
            }

            // if the node has no more courses , remove it from tree
            if ( searchedNode.getAllCourses().isEmpty())
            {
                // Update : Don't remove node,
                //this.deleteNodeKey(key);
            }
        }
    }
    /**
     * This method mainly calls InorderRec()
     */
    public void printNodes(Results result)
    {
        this.printNodesRec(root, result);
    }

    /**
     * A utility function to do inorder traversal of BST
     * @param root
     */
    public void printNodesRec(Node root, Results result)
    {
        if (root != null)
        {
            printNodesRec(root.getLeft(), result);
            result.storeNewResult(root.getbNumber() + " : " + String.join(", ",root.getAllCourses()));
            MyLogger.writeMessage(root.getbNumber() + " : " + String.join(", ",root.getAllCourses()) ,MyLogger.DebugLevel.DEBUG);
            printNodesRec(root.getRight(), result);
        }
    }
};