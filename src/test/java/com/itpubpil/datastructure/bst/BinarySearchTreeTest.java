package com.itpubpil.datastructure.bst;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangjingbiao
 * createTime: 2021/6/21 22:42
 * desc:
 * 测试二分搜索树
 */
public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @Before
    public void initBst(){
        bst = new BinarySearchTree<>();
        int[] nums = {5,3,4,8,4,2};
        Arrays.stream(nums).forEach((num)-> bst.add(num));
    }

    @Test
    public void bstAdd(){
        System.out.println(bst);
        bst.add(10);
        System.out.println(bst);
    }

    @Test
    public void  bstContains(){
        Assert.assertTrue(bst.contains(8));
        Assert.assertFalse(bst.contains(100));
    }

    @Test
    public void bstOrder(){
        System.out.println("preOrder:");
        bst.preOrder();
        System.out.println("NoRecursionPreOrder:");
        bst.noRecursionPreOrder();
        System.out.println("midOrder:");
        bst.midOrder();
        System.out.println("postOrder:");
        bst.postOrder();
        System.out.println("levelOrder:");
        bst.levelOrder();
    }

    @Test
    public void removeNode(){
        System.out.println(bst);
        bst.remove(5);
        System.out.println(bst);
        bst.removeMax();
        System.out.println(bst);
        bst.removeMin();
        System.out.println(bst);
    }
}