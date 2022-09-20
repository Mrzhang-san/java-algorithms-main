package cn.bugstack.algorithms.data.linked;

/**
 *
 *
 * @author zhangsan
 * @date 2022/8/24
 */
public interface List<E> {

    boolean add(E e);
    boolean addFirst(E e);
    boolean addLast(E e);
    boolean remove(Object o);
    E get(int index);
    void printLinkList();

}
