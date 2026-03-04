package com.itpubpil.datastructure.bst;


import com.itpubpil.datastructure.queue.LinkedListQueue;
import com.itpubpil.datastructure.queue.Queue;
import com.itpubpil.datastructure.statck.LinkedListStack;
import com.itpubpil.datastructure.statck.Stack;
import org.w3c.dom.Node;

/**
 * @author wangjingbiao
 * createTime: 2021/6/21 22:05
 * desc:
 * 二分搜索树
 */
public class BinarySearchTree<E extends Comparable<E>> {
    /**
     * 节点内部类
     */
    private class Node {
        public E e;
        // 左节点、右节点
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 新增元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo((node.e)) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 查询元素
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo((node.e)) > 0) {
            return contains(node.right, e);
        } else {
            return contains(node.left, e);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历非递归实现
     */
    public void noRecursionPreOrder() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new LinkedListStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // 先入右节点，再入左节点，栈的特点是后入先出
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            System.out.println(node.e);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        midOrder(root);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    private void midOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        System.out.println(node.e);
        midOrder(node.right);
    }

    /**
     * 层序遍历（广度优先遍历），常用于搜索
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedListQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.deQueue();
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
            System.out.println(node.e);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 寻找树最小值
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除树最小值
     */
    public E removeMin() {
        E minimumValue = minimum();
        Node min = removeMin(root);
        if(minimumValue.compareTo(root.e) == 0){
            root = min;
        }
        return minimumValue;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除树最大值
     */
    public E removeMax() {
        E maximumValue = maximum();
        Node max = removeMax(root);
        if(maximumValue.compareTo(root.e) == 0){
            root = max;
        }
        return maximumValue;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除任意节点
     */
    public void remove(E e) {
        Node node = remove(root, e);
        if (root.e.compareTo(e) == 0) {
            root = node;
        }
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除结点左右子树都不为空，找比当前节点大的最小值，替换当前节点
            Node replaceNode = minimum(node.right);
            replaceNode.right = removeMin(node.right);
            replaceNode.left = node.left;

            node.left = node.right = null;
            return replaceNode;
        }

    }

    /**
     * 寻找树最大值
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    @Override
    public String toString() {
        // 前序遍历打印树
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
