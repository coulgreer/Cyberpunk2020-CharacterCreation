package rpg.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A data structure that wraps up data of <code>T</code> type as a node, and
 * allows chaining of nodes that are of the same type to create a tree.<br />
 * <br />
 * Used answers from StackOverflow to design this tree<br />
 * Links:<br />
 * <a href= "https://stackoverflow.com/a/40622616/9108377">Tree implementation
 * in Java (root, parents and children)</a><br />
 * <a href="https://stackoverflow.com/a/8617828/9108377">Recursive search for a
 * node in non-binary tree</a><br />
 * 
 * @author Coul Greer
 *
 * @param <T> the data stored in each node
 */
public class TreeNode<T> {
	private String nodeName;
	private T data;
	private TreeNode<T> parent;
	private Map<String, TreeNode<T>> children;

	/**
	 * Constructs a <code>Node</code> with a name and data. Sets the parent to null
	 * by default
	 * 
	 * @param name the name for this node. Used to obtain this node from nodes
	 *             higher in the tree structure.
	 * @param data the value to be associated with the name
	 */
	public TreeNode(String name, T data) {
		setName(name);
		this.data = data;
		this.parent = null;
		this.children = new HashMap<String, TreeNode<T>>();
	}

	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The field 'name' cannot be null.");
		} else {
			this.nodeName = name;
		}
	}

	/**
	 * Adds data to this node after wrapping the data in a node with the given name.
	 * 
	 * @param name the tag for the node that will be created
	 * @param data the data to keep inside the generated node
	 * @see rpg.util.TreeNode#appendChild(TreeNode)
	 */
	public void appendChild(String name, T data) {
		TreeNode<T> node = new TreeNode<T>(name, data);
		appendChild(node);
	}

	/**
	 * Adds another node to this node.
	 * 
	 * @param child the node to add to this node
	 */
	public void appendChild(TreeNode<T> child) {
		String nodeName = child.getName();

		TreeNode<T> root = getRoot();

		if (root.getNode(nodeName) != null || child.getChild(nodeName) != null) {
			throw new IllegalArgumentException("The node name '" + nodeName + "' has already been taken.");
		} else {
			child.setParent(this);
			children.put(nodeName, child);
		}
	}

	/**
	 * Deletes a child from this. The removed child gets returned as a result.
	 * 
	 * @param name the tag of the target node to remove
	 * @return the child that got removed from this node
	 */
	public TreeNode<T> removeChild(String name) {
		if (children.get(name).getParent() == this) {
			children.get(name).setParent(null);
		}

		return children.remove(name);
	}

	/**
	 * Returns the collection of immediate children.
	 * 
	 * @return the collection of direct children
	 */
	public Map<String, TreeNode<T>> getChildren() {
		return children;
	}

	/**
	 * Returns any node with the given tag. This includes this node as a potentially
	 * returned object.
	 * 
	 * @param name the tag used to identify the desired node for return
	 * @return a node with the given tag name
	 * @see rpg.util.TreeNode#getChild(String)
	 */
	public TreeNode<T> getNode(String name) {
		if (nodeName.equals(name)) {
			return this;
		} else {
			return getChild(name);
		}
	}

	/**
	 * Returns any child node of this node with the given tag.
	 * 
	 * @param name the tag used to identify the desired node for return
	 * @return a node with the given tag name
	 */
	public TreeNode<T> getChild(String name) {
		TreeNode<T> resultNode = null;
		Iterator<TreeNode<T>> iterator = children.values().iterator();

		while (iterator.hasNext() && resultNode == null) {
			TreeNode<T> tempNode = iterator.next();
			resultNode = tempNode.getNode(name);
		}

		return resultNode;
	}

	/**
	 * Returns this nodes identification tag.
	 * 
	 * @return this nodes id tag
	 */
	public String getName() {
		return nodeName;
	}

	/**
	 * Returns the object stored in this node.
	 * 
	 * @return the object stored in this node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Returns the immediate parent to this node.
	 * 
	 * @return the immediate parent
	 */
	public TreeNode<T> getParent() {
		return parent;
	}

	/**
	 * Changes the immediate node to be above this node in a hierarchy.
	 * 
	 * @param parent the desired node to be above this node in a hierarchy
	 */
	public void setParent(TreeNode<T> parent) {
		if (this.parent == null) {
			this.parent = parent;
		} else {
			TreeNode<T> oldParent = this.parent;
			this.parent = parent;
			oldParent.removeChild(nodeName);
		}
	}

	/**
	 * Returns the highest node in this nodes hierarchy. The returned node has no
	 * parent.
	 * 
	 * @return the node highest in the hierarchy
	 */
	public TreeNode<T> getRoot() {
		if (parent == null) {
			return this;
		} else {
			return parent.getRoot();
		}
	}

}
