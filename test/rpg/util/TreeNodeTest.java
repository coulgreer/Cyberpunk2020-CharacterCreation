package rpg.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class TreeNodeTest {
	
	/*
	 *      --- Tree Diagram ---
	 * 
	 *         ____ Root ____
	 *        /      |       \
	 *       /       |        \
	 *  Parent1   Parent2   Parent3
	 *           /       \        \
	 *          /         \        \
	 *       Child2A   Child2B   Child3A
	 *                    |
	 *                    |
	 *                 Child2BA
	 * 
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testChildNotAppendedIfNameForNodeIsAlreadyInUse() {
		TreeNode<Object> childNode = new TreeNode<Object>("A Leaf", null);
		
		TreeNode<Object> parent1Node = new TreeNode<Object>("A Branch", null);
		parent1Node.appendChild(childNode);
		
		TreeNode<Object> parent2Node = new TreeNode<Object>("A Leaf", null);

		TreeNode<Object> rootNode = new TreeNode<Object>("Root Node", null);
		rootNode.appendChild(parent1Node);
		rootNode.appendChild(parent2Node);
	}
	
	@Test
	public void testDataIsReturnedIfDataIsAppendedToRoot() {
		String nodeName = "Child Node";
		
		Object mockData = mock(Object.class);
		
		TreeNode<Object> root = new TreeNode<Object>("Root Node", null);
		root.appendChild(nodeName, mockData);
		
		assertEquals(mockData, root.getChild(nodeName).getData());
	}
	
	@Test
	public void testNodeIsReturnedIfNodeIsAppendedToRoot() {
		TreeNode<Object> childNode = new TreeNode<Object>("Child Node", null);
		
		TreeNode<Object> root = new TreeNode<Object>("Root Node", null);
		root.appendChild(childNode);
		
		assertEquals(childNode, root.getChild(childNode.getName()));
	}

	@Test
	public void testAllNodesCanBeAccessedIfNodesAreContainedInRootTree() {
		
		TreeNode<Object> parent1 = new TreeNode<Object>("Parent1 Node", null);
		
		TreeNode<Object> child2A = new TreeNode<Object>("Child2A Node", null);

		TreeNode<Object> child2BA = new TreeNode<Object>("Child2AB Node", null);
		
		TreeNode<Object> child2B = new TreeNode<Object>("Child2B Node", null);
		child2B.appendChild(child2BA);
		
		TreeNode<Object> parent2 = new TreeNode<Object>("Parent2 Node", null);
		parent2.appendChild(child2A);
		parent2.appendChild(child2B);

		TreeNode<Object> child3A = new TreeNode<Object>("Child3A Node", null);

		TreeNode<Object> parent3 = new TreeNode<Object>("Parent3 Node", null);
		parent3.appendChild(child3A);

		TreeNode<Object> root = new TreeNode<Object>("Root Node", null);
		root.appendChild(parent1);
		root.appendChild(parent2);
		root.appendChild(parent3);

		assertEquals(parent1, root.getChild(parent1.getName()));
		assertEquals(parent2, root.getChild(parent2.getName()));
		assertEquals(child2A, root.getChild(child2A.getName()));
		assertEquals(child2B, root.getChild(child2B.getName()));
		assertEquals(child2BA, root.getChild(child2BA.getName()));
		assertEquals(parent3, root.getChild(parent3.getName()));
		assertEquals(child3A, root.getChild(child3A.getName()));
	}
	
	@Test
	public void testRootDoesNotContainPNodeOrChildrenOfPNodeIfPNodeHasBeenRemovedFromRoot() {
		TreeNode<Object> parent1 = new TreeNode<Object>("Parent1 Node", null);
		
		TreeNode<Object> parent2 = new TreeNode<Object>("Parent2 Node", null);

		TreeNode<Object> child3A = new TreeNode<Object>("Child3A Node", null);

		TreeNode<Object> parent3 = new TreeNode<Object>("Parent3 Node", null);
		parent3.appendChild(child3A);

		TreeNode<Object> root = new TreeNode<Object>("Root Node", null);
		root.appendChild(parent1);
		root.appendChild(parent2);
		root.appendChild(parent3);
		
		root.removeChild(parent3.getName());
		
		assertEquals(null, root.getChild(parent3.getName()));
		assertEquals(null, root.getChild(child3A.getName()));
		assertEquals(null, parent3.getParent());
	}
	
	@Test
	public void testGCNodeCanAccessRootIfGCNodeIsThreeLevelsDeep() {
		TreeNode<Object> grandchildNode = new TreeNode<Object>("Grandchild Node", null);
		
		TreeNode<Object> childNode = new TreeNode<Object>("Child Node", null);
		childNode.appendChild(grandchildNode);
		
		TreeNode<Object> parentNode = new TreeNode<Object>("Parent Node", null);
		parentNode.appendChild(childNode);

		TreeNode<Object> rootNode = new TreeNode<Object>("Root Node", null);
		rootNode.appendChild(parentNode);

		assertEquals(rootNode, grandchildNode.getRoot());
	}

}
