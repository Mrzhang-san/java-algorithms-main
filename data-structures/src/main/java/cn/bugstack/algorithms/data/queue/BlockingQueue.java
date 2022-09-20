package cn.bugstack.algorithms.data.queue;

/**
 * 阻塞队列
 *
 * @author zhangsan
 * @date 2022/8/24
 */
public interface BlockingQueue<E> extends Queue<E> {

    boolean add(E e);

    boolean offer(E e);

}
