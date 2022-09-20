package cn.bugstack.algorithms.data.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @description 延迟队列
 */
public class DelayQueue<E extends Delayed> implements BlockingQueue<E> {

    // 可重入锁
    private final transient ReentrantLock lock = new ReentrantLock();
    // 优先队列
    private final PriorityQueue<E> q = new PriorityQueue<>();
    // 信号量
    private final Condition available = lock.newCondition();

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.offer(e);
            if (q.peek() == e) {
                available.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E first = q.peek();
            if (first == null || first.getDelay(NANOSECONDS) > 0) {
                return null;
            } else {
                return q.poll();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.peek();
        } finally {
            lock.unlock();
        }
    }

}
