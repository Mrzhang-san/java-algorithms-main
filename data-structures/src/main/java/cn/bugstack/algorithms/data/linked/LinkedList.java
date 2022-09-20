package cn.bugstack.algorithms.data.linked;

/**
 * 链表
 *
 * @author zhangsan
 * @date 2022/8/24
 */
public class LinkedList<E> implements List<E> {
    // 元素个数
    transient int size = 0;
    // 头节点
    transient Node<E> first;
    // 尾节点
    transient Node<E> last;

    public LinkedList() {
    }

    /**
     * 头插节点
     *
     * @param e
     */
    void linkFirst(E e) {
        // 把头节点先保存到f
        final Node<E> f = first;
        // 创建一个节点 头节点为null，下一个节点为之前第一个节点
        final Node<E> newNode = new Node<>(null, e, f);
        // 新创建的置为头节点
        first = newNode;
        // 如果头节点为null 说明没有头节点，新创建的节点为头节点
        if (f == null)
            last = newNode;
        else
            f.prev = newNode; // 新的节点为之前的头节点的前一个节点
        size++;
    }

    /**
     * 尾部插头节点
     *
     * @param e
     */
    void linkLast(E e) {
        // 保存尾节点
        final Node<E> l = last;
        // 创建一个节点，l为前一个节点
        final Node<E> newNode = new Node<>(l, e, null);
        // 新的节点设置为尾节点
        last = newNode;
        // 上一个节点为null 头节点为刚创建的节点
        if (l == null) {
            first = newNode;
        } else {
            // 之前的下一个节点为新创建的节点
            l.next = newNode;
        }
        size++;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 移除一个节点
     *
     * @param o 元素
     * @return
     */
    @Override
    public boolean remove(Object o) {

        if (o == null) {
            // 循环列表
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 取消链接
     *
     * @param x 节点
     * @return
     */
    E unlink(Node<E> x) {
        // 链表中的元素  // a -> b -> c  假设x=b
        final E element = x.item;
        // 后一个节点 next=c
        final Node<E> next = x.next;
        // 前一个节点 a
        final Node<E> prev = x.prev;
        // 前一个节点为空
        if (prev == null) {
            // 下一个节点为第一个节点
            first = next;
        } else {
            //a->c
            prev.next = next;
            // 断开a和b
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            // c 指向a
            next.prev = prev;
            //断开b和c
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }


    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public void printLinkList() {
        if (this.size == 0) {
            System.out.println("链表为空");
        } else {
            Node<E> temp = first;
            System.out.print("目前的列表，头节点：" + first.item + " 尾节点：" + last.item + " 整体：");
            while (temp != null) {
                System.out.print(temp.item + "，");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    /**
     * 获取对应下标的节点
     * 索引超过链表个数返回最后一个节点
     * @param index 索引
     * @return
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    // 一个节点
    private static class Node<E> {
        // 元素
        E item;
        // 下一个节点
        Node<E> next;
        // 上一个节点
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

    }

}
