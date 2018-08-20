package wordCount.treesForStrings;

public class Node {
	
		
	private String word;
	private int occurenceCount;
	private Node leftChild, rightChild;
		
	

	/**
	 * @return
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return
	 */
	public int getOccurenceCount() {
		return occurenceCount;
	}

	/**
	 * @param occurenceCount
	 */
	public void setOccurenceCount(int occurenceCount) {
		this.occurenceCount = occurenceCount;
	}

	/**
	 * @return
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild
	 * return null
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild
	 * return null
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

}
