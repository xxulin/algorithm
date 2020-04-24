package algorithm;

public class BinaryTree {

	private static volatile Node baseRoot;

	public static void main(String[] args) {

		int[] a = {8, 15, 32, 16, 1, 4, 8, 19, 23, 22, 24, 25};
		for (int i : a) {
			insert(i);
		}
		System.out.println(getHigh(baseRoot));

	}
	
	/**
	 * 求树的高度
	 * 递归公式：getHigh(tree) = Max(getHigh(left) + getHigh(right)) + 1
	 * @param node
	 * @return
	 */
	public static int getHigh(Node node) {
		
		if(node == null) {
			return -1;
		}
		
		int left = getHigh(node.left);
		int right = getHigh(node.right);
		
		if(left > right) {
			return left + 1;
		} else {
			return right + 1;
		}
		
	}
	
	public static void delete(int data) {
		
		//找到要删除的节点及其父节点
		Node f = null;
		Node dNode = baseRoot;
		while(dNode != null && dNode.data != data) {
			
			f = dNode;
			if(data > dNode.data) {
				dNode = dNode.right;
			} else {
				dNode = dNode.left;
			}
			
		}
		
		//未找到
		if(dNode == null || (dNode == baseRoot && data != dNode.data)) {
			return;
		}
		
		//若两个子节点都不为null，将右子树中最小的节点与将删除的节点调换，并将最小节点标记为要删除的节点
		if(dNode.left != null && dNode.right != null) {
			
			Node min = dNode.right;
			Node minF = dNode;
			while(min.left != null) {
				minF = min;
				min = min.left;
			}
			
			dNode.data = min.data;
			dNode = min;
			f = minF;
			
		}
		
		//此处要删除的节点一定是叶子节点或仅有一个子节点
		Node c = null;
		if(dNode.left != null) {
			c = dNode.left;
		} else if(dNode.right != null) {
			c = dNode.right;
		}
		
		if(f == null) {
			baseRoot = c;
		} else if (f.left == dNode) {
			f.left = c;
		} else if(f.right == dNode) {
			f.right = c;
		}
		
	}
	
	public static Node findMax() {
		
		Node node = baseRoot;
		while(node != null) {
			node = node.right;
		}
		return node;
		
	}
	
	public static Node findMin() {
		Node node = baseRoot;
		while(node != null) {
			node = node.left;
		}
		return node;
	}
	
	/**
	 * 中序遍历：先打印左子树，再打印本身，最后打印右子树
	 */
	public static void inorderTraversal(Node root) {
		
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
		
	}
	
	public static void insert(int data) {

		Node newNode = new Node(data);

		if (baseRoot == null) {
			baseRoot = newNode;
			return;
		}

		Node root = baseRoot;
		while (root != null) {

			if (data > root.data) {

				if (root.right == null) {
					root.right = newNode;
					return;
				}
				root = root.right;

			} else if (data < root.data) {

				if (root.left == null) {
					root.left = newNode;
					return;
				}
				root = root.left;

			} else {
				return;
			}

		}

	}

	public static Node find(int data) {
		return find(baseRoot, data);
	}

	private static Node find(Node root, int data) {

		if (root == null) {
			return null;
		}
		if (root.data == data) {
			return root;
		}

		return data > root.data ? find(root.right, data) : find(root.left, data);

	}

	static class Node {

		int data;
		Node left;
		Node right;

		public Node() {
		};

		public Node(int data) {
			this.data = data;
		}

	}

}
