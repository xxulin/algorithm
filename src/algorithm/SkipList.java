package algorithm;

import java.util.Arrays;

/**
 * 跳表的一种实现方法。 跳表中存储的是正整数，并且存储的是不重复的。
 *
 * Author：ZHENG
 */
public class SkipList {

	private static final float SKIPLIST_P = 0.5f;
	private static final int MAX_LEVEL = 16;

	private static int levelCount = 1;

	private static Node head = new Node(); // 带头链表

	public static void main(String[] args) {

		//int[] a = { 1, 2, 5, 11, 9, 18, 3, 9, 10 };
		int[] a = { 5, 11 };
		for (int i : a) {
			insert(i);
		}
		for (int i = 0; i < a.length; i++) {
			delete(a[i]);
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println(find(a[i]));
		}

	}

	public static void insert(int data) {

		int level = randomLevel();

		Node newNode = new Node();
		newNode.data = data;
		newNode.maxLevel = level;

		Node[] preNodes = new Node[level];
		for (int i = 0; i < level; i++) {
			preNodes[i] = head;
		}

		// 为保证跳表有序性，找到将要插入的结点的每层level的前一个元素
		Node pre = head;
		for (int i = level - 1; i >= 0; i--) {

			while (pre.forward[i] != null && pre.forward[i].data < data) {
				pre = pre.forward[i];
			}
			preNodes[i] = pre;

		}

		for (int i = 0; i < level; i++) {

			// 将新结点每层level的后继指针，指向原前结点的后继指针
			newNode.forward[i] = preNodes[i].forward[i];
			// 将原前结点的后继指针，指向当前结点
			preNodes[i].forward[i] = newNode;

		}

		if (levelCount < level) {
			levelCount = level;
		}
		System.out.println("[insert] - levelCount: " + levelCount);

	}

	public static boolean find(int data) {

		Node temp = head;
		for (int i = levelCount - 1; i >= 0; i--) {

			while (temp.forward[i] != null && temp.forward[i].data < data) {
				temp = temp.forward[i];
			}
			if (temp.forward[i] != null && temp.forward[i].data == data) {
				return true;
			}

		}

		return false;

	}

	public static void delete(int data) {

		Node preNode = SkipList.head;
		for (int i = levelCount - 1; i >= 0; i--) {

			while (preNode.forward[i] != null && preNode.forward[i].data < data) {
				preNode = preNode.forward[i];
			}
			if (preNode.forward[i] != null && preNode.forward[i].data == data) {
				preNode.forward[i] = preNode.forward[i].forward[i];
			}

		}

		while (levelCount > 1 && head.forward[levelCount - 1] == null) {
			levelCount--;
		}

		System.out.println("[delete] - levelCount: " + levelCount);

	}

	private static int randomLevel() {

		int level = 1;
		while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
			level += 1;
		}
		return level;

	}

	/**
	 * @author 王争老师
	 */
//	public static void delete(int value) {
//		Node[] update = new Node[levelCount];
//		Node p = head;
//		for (int i = levelCount - 1; i >= 0; --i) {
//			while (p.forward[i] != null && p.forward[i].data < value) {
//				p = p.forward[i];
//			}
//			update[i] = p;
//		}
//
//		if (p.forward[0] != null && p.forward[0].data == value) {
//			for (int i = levelCount - 1; i >= 0; --i) {
//				if (update[i].forward[i] != null && update[i].forward[i].data == value) {
//					update[i].forward[i] = update[i].forward[i].forward[i];
//				}
//			}
//		}
//
//		while (levelCount > 1 && head.forward[levelCount - 1] == null) {
//			levelCount--;
//		}
//		
//		System.out.println("[delete] - levelCount: " + levelCount);
//		
//	}

	/**
	 * @author xull
	 *
	 */
	static class Node {

		private int data = -1;

		private Node[] forward = new Node[MAX_LEVEL];

		private int maxLevel = 0;

		@Override
		public String toString() {
			return "Node [data=" + data + ", forward=" + Arrays.toString(forward) + ", level=" + maxLevel + "]";
		}

	}

}
