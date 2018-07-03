package com.rain.learning.algorithm;

/**
 * 二叉树操作
 * 
 * @author rain
 *
 */
public class BinaryTreeSearch {

	private class Node {
		private String key;
		private String val;
		private Node left, right;
		private int N;

		public Node(String key, String val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}

	public String get(Node x, String key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x.val;
		}
	}

	public Node min(Node x) {
		if (x.left == null) {
			return x;
		}
		return min(x.left);
	}

	public Node floor(Node x, String key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	public Node select(Node x, int k) {
		if (x == null) {
			return null;
		}
		int t = size(x.left);
		if (t > k) {
			return select(x.left, k);
		} else if (t < k) {
			return select(x.right, k - t - 1);
		} else {
			return x;
		}
	}

	public int rank(String key, Node x) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return rank(key, x.left);
		} else if (cmp > 0) {
			return 1 + size(x.left) + rank(key, x.right);
		} else {
			return size(x.left);
		}
	}

	public Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Node delete(Node x, String key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Node put(Node x, String key, String val) {
		if (x == null) {
			return new Node(key, val, 1);
		}

		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;

		return x;
	}

	public void inorderTraversal(Node node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.print(" " + node.key + "=" + node.val);
		inorderTraversal(node.right);

	}

	public static void main(String[] args) {
		Node root = null;
		// String[] array = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
		// "K" };
		// String[] array = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M",
		// "P", "L", "E" };
		String[] array = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P" };
		BinaryTreeSearch bst = new BinaryTreeSearch();

		for (int i = 0; i < array.length; i++) {
			root = bst.put(root, array[i], String.valueOf(i));
		}
		bst.inorderTraversal(root);

		System.out.println("");
		// root = bst.put(root, "L", String.valueOf(100));

		root = bst.delete(root, "E");
		bst.inorderTraversal(root);

	}

}
