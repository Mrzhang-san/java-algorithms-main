package cn.bugstack.algorithms.data.queue;

import java.util.concurrent.TimeUnit;

/**
 *
 * 延迟队列接口
 * @author zhangsan
 * @date 2022/8/24
 */
public interface Delayed extends Comparable<Delayed>{

    long getDelay(TimeUnit unit);

}
