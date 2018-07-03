package com.rain.learning.algorithm;

/**
 * 红黑树操作
 *  
 * @author rain
 *
 */
public class RedBlackBST {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		String key;
		String val;
		Node left, right;
		int N;
		boolean color;

		Node(String key, String val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}

	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == RED;
	}

	public int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}

	public Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	public Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	public void flipColors(Node h) {
		h.color = RED;
		if (h.left != null) {
			h.left.color = BLACK;
		}
		if (h.right != null) {
			h.right.color = BLACK;
		}

	}

	public Node put(Node h, String key, String val) {
		if (h == null) {
			return new Node(key, val, 1, RED);
		}
		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			h.left = put(h.left, key, val);
		} else if (cmp > 0) {
			h.right = put(h.right, key, val);
		} else {
			h.val = val;
		}

		// 将任意含有红色右链接的3-结点（或临时的4-结点）向左旋转
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		// 会将临时的4-结点中两条连续红链接中的上层链接向右旋转
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		// 会进行颜色转换并将红链接在树中向上传递
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	public void inorderTraversal(Node node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.print(" " + node.key + "=" + node.val);
		inorderTraversal(node.right);

	}

	private Node moveRedLeft(Node h) {
		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}

	public void deleteMin(Node root) {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMinR(root);

		// if (!isEmpty()) {
		// root.color = BLACK;
		// }

	}

	private Node deleteMinR(Node h) {
		if (h.left == null) {
			return null;
		}
		if (!isRed(h.left) && !isRed(h.left.left)) {
			h = moveRedLeft(h);
		}
		h.left = deleteMinR(h.left);
		return balance(h);
	}

	private Node balance(Node h) {
		if (isRed(h.right)) {
			h = rotateLeft(h);
		}
		// 将任意含有红色右链接的3-结点（或临时的4-结点）向左旋转
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		// 会将临时的4-结点中两条连续红链接中的上层链接向右旋转
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		// 会进行颜色转换并将红链接在树中向上传递
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}

	private Node moveRedRight(Node h) {
		flipColors(h);
		if ((h.left != null) && (h.left.left != null)) {
			if (!isRed(h.left.left)) {
				h = rotateRight(h);
			}
		}
		return h;
	}

	public void deleteMax(Node root) {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMaxR(root);
		// if (!isEmpty()) {
		// root.color = BLACK;
		// }

	}

	private Node deleteMaxR(Node h) {
		if (isRed(h.left)) {
			h = rotateRight(h);
		}
		if (h.right == null) {
			return null;
		}

		if (!isRed(h.right) && !isRed(h.right.left)) {
			h = moveRedRight(h);
		}
		h.right = deleteMaxR(h.right);
		return balance(h);
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

	public void delete(Node root, String key) {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteR(root, key);
	}

	private Node deleteR(Node h, String key) {
		if (key.compareTo(h.key) < 0) {
			if (!isRed(h.left) && !isRed(h.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = deleteR(h.left, key);
		} else {
			if (isRed(h.left)) {
				h = rotateRight(h);
			}
			if (key.compareTo(h.key) == 0 && (h.right == null)) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if (key.compareTo(h.key) == 0) {
				h.val = get(h.right, min(h.right).key);
				h.key = min(h.right).key;
				h.right = deleteMinR(h.right);
			} else {
				h.right = deleteR(h.right, key);
			}
		}
		return balance(h);
	}

	public static void main(String[] args) {
		Node root = null;
		String[] array = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P" };
		RedBlackBST bst = new RedBlackBST();

		for (int i = 0; i < array.length; i++) {
			root = bst.put(root, array[i], String.valueOf(i));
			root.color = BLACK;
		}
		bst.inorderTraversal(root);

		System.out.println("");
		// bst.deleteMin(root);
		// bst.deleteMax(root);
		bst.delete(root, "C");

		bst.inorderTraversal(root);

	}

}
