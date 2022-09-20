package cn.bugstack.algorithms.data.queue;

/**
 * @description 单端队列接口
 * Java泛型中的标记符含义：
 * E - Element （元素,在集合中使用）
 * T - Type （Java类）
 * K - Key （键）
 * V - Value （值）
 * N - Number（数值类型）
 * ? - 表示不确定的Java类型
 */
public interface Queue<E> {

    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();

}
