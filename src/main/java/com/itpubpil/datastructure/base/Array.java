package com.itpubpil.datastructure.base;

/**
 * @author wangjingbiao
 * createTime: 2019/7/11 21:54
 * desc:
 * 二次封装java中的数组，可动态增长，可增删改查数据
 */
public class Array<E> {
    /**
     * java原始数组
     */
    private E[] data;
    /**
     * size 用于记录当前数组中元素数量
     */
    private int size;
    /**
     * 默认长度
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造函数，传入数组的容量构造Array
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 默认构造函数，长度设置为10
     */
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取数组中的元素个数
     *
     * @return 数组中当前元素数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量大小
     *
     * @return 数组中当前的容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return false:true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 尾部增加一个元素
     *
     * @param e element
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 头部增加一个元素
     *
     * @param e element
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 指定下标添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("addLast Failed,Required index >= 0 and index <= currentSize");
        }
        // 如果添加元素时检测到满了，则动态扩展为原来长度的两倍
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index location
     * @return element
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get Failed,Required index >= 0 and index <= currentSize");
        }
        return data[index];
    }

    /**
     * 获取尾部元素
     *
     * @return element
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 获取头部元素
     *
     * @return element
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 修改指定位置的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get Failed,Required index >= 0 and index <= currentSize");
        }
        data[index] = e;
    }

    /**
     * 判断是否包含某元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index
     * @return 返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove Failed,Required index >= 0 and index <= currentSize");
        }
        E removedElement = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        // 移除元素时，如果数组中元素数量减少到一定程度，则缩小数组长度为原来的1/2,避免复杂度震荡
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return removedElement;
    }

    /**
     * 删除头元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除尾元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除某个元素
     *
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 动态扩展容量
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");

        return res.toString();
    }
}
