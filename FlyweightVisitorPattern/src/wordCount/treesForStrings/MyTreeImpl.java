package wordCount.treesForStrings;

import wordCount.treeOperations.treeInsertOperations.Visitor;
import wordCount.util.MyLogger;
import wordCount.util.MyLogger.DebugLevel;

public class MyTreeImpl {
	

	private int no_words = 0, no_distinct_words = 0, no_characters = 0;
	
	private String inputFile;
	
	private Node rootNode;
	
	
	/**
	 * @param inputFileParam
	 */
	public MyTreeImpl(String inputFileParam) {		
		this.inputFile = inputFileParam;
		MyLogger.writeMessage("Initialized the BST tree constructor \n", DebugLevel.CONSTRUCTOR);
	}

	
	/**
	 * @param visitor
	 * @param resultsObj
	 * @return void
	 */
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
	}
	
	
	/**
	 * @param parentNode
	 * @param newNode
	 * @return
	 */
	public Node insertRecursive(Node parentNode, Node newNode) {
		
		if(parentNode == null) {
			parentNode = newNode;
			return parentNode;
		}		
		if(newNode.getWord().compareTo(parentNode.getWord()) > 0) {
			parentNode.setRightChild(insertRecursive(parentNode.getRightChild(), newNode));
		}else if(newNode.getWord().compareTo(parentNode.getWord()) < 0) {
			parentNode.setLeftChild(insertRecursive(parentNode.getLeftChild(), newNode));			
		}
		return parentNode;
	}
	
	
	/**
	 * @param newNode
	 * @return
	 */
	public Node insertNode(Node newNode) {
		// TODO Auto-generated method stub
		rootNode = insertRecursive(rootNode, newNode);
		return rootNode;
	}


    /**
     * @param parentNode
     * @param word
     * @return
     */
	public Node searchNodeRec(Node parentNode, String word) {
		// TODO Auto-generated method stub
		if(parentNode == null) {
			return null;
		}
		if(parentNode.getWord().equals(word)) {
			return parentNode;
		}else if(word.compareTo(parentNode.getWord()) > 0) {
			parentNode = searchNodeRec(parentNode.getRightChild(), word);
		}else if(word.compareTo(parentNode.getWord()) < 0) {
			parentNode = searchNodeRec(parentNode.getLeftChild(), word);			
		}
		return parentNode;
	}

	
	/**
	 * @param bnum
	 * @return
	 */
	public Node searchNode(String word) {			
		return searchNodeRec(rootNode, word);
	}


    /**
     * @param root
     */
    public void CountOPerations(Node root) {
        if (root != null) {
            CountOPerations(root.getLeftChild());

            no_characters = no_characters + root.getOccurenceCount() * root.getWord().length() ;
            no_distinct_words = no_distinct_words + 1;
            no_words = no_words + root.getOccurenceCount();
            
            CountOPerations(root.getRightChild());
        }
    }
    
	
	/**
     * Count no of operations
	 */
	public void CountOPs() {

		CountOPerations(rootNode);
	}


    /**
     * get chars
     * @return
     */
    public int getNo_characters() {
        return no_characters;
    }

    /**
     * get distinct words
     * @return
     */
    public int getNo_distinct_words() {
        return no_distinct_words;
    }

    /**
     * get words count
     * @return
     */
    public int getNo_words() {
        return no_words;
    }

    /**
     * @return
     */
    public String getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
	}
}
