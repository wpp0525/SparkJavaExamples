package concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ClhSpinLock implements Lock {

    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<Node>(new Node());

    public ClhSpinLock() {
        this.node = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return new Node();
            }
        };

        this.prev = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return null;
            }
        };
    }

    /**
     * 1.初始状态 tail指向一个node(head)节点
     * +------+
     * | head | <---- tail
     * +------+
     *
     * 2.lock-thread加入等待队列: tail指向新的Node，同时Prev指向tail之前指向的节点
     * +----------+
     * | Thread-A |
     * | := Node  | <---- tail
     * | := Prev  | -----> +------+
     * +----------+        | head |
     *                     +------+
     *
     *             +----------+            +----------+
     *             | Thread-B |            | Thread-A |
     * tail ---->  | := Node  |     -->    | := Node  |
     *             | := Prev  | ----|      | := Prev  | ----->  +------+
     *             +----------+            +----------+         | head |
     *                                                          +------+
     * 3.寻找当前node的prev-node然后开始自旋
     *
     */
    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node pred = this.tail.getAndSet(node);
        this.prev.set(pred);
        // 自旋
        while (pred.locked);
    }

    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    private static class Node {
        private volatile boolean locked;
    }
}