package cn.bugstack.algorithms.data.queue;

/**
 * @description 双端队列接口
 */
public interface Deque<E> extends Queue<E>{

    void addFirst(E e);

    void addLast(E e);

}
