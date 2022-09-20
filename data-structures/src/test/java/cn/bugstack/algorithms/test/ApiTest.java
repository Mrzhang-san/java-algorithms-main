package cn.bugstack.algorithms.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;

public class ApiTest {

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String signature = getSha256Str("3edc$RFV", String.valueOf(timestamp), "secadmin");
        System.out.println("signature -> "+ signature);
    }

    /**
     * 获取signature
     * @param password 密码
     * @param timestamp 时间戳
     * @param userName 用户名
     * @return
     */
    public static String getSha256Str(String password, String timestamp, String userName) {
        String encodeStr = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            String k = byte2Hex(messageDigest.digest());

            // 时间戳 + 用户名 + 密码 二次加密
            String str = timestamp + userName + k;
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            String baseStr = byte2Hex(messageDigest.digest());
            byte[] encode = Base64.getEncoder().encode(baseStr.getBytes());
            encodeStr = new String(encode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * sha256加密 将byte转为16进制
     *
     * @param bytes 字节码
     * @return 加密后的字符串
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }




    @Test
    public void test_linked_list() {
        LinkedList<String> list = new LinkedList<>();
        //list.addFirst("x");
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.toString());
        String s = list.get(5);
        System.out.println(s);


    }

    @Test
    public void test_stack() {
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Deque<String> deque = new LinkedList<>();
        deque.push("1");
        deque.push("2");
        deque.push("3");

        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());

        Deque<Integer> stackArray = new ArrayDeque<>();
        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);

        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());


    }

    @Test
    public void test_array_list() {
        List<String> list = new ArrayList<>();
        list.add("");

        list.get(1);
    }

    @Test
    public void test_array() {
        String[][] x = new String[2][2];
        x[0][0] = "kdkkdll";
        x[0][1] = "dd";
        x[1][0] = "pppp";
        x[1][1] = "你好";

        System.out.println(Integer.toHexString(x[0][0].hashCode()));
        System.out.println(Integer.toHexString(x[0][1].hashCode()));
        System.out.println(Integer.toHexString(x[1][0].hashCode()));
        System.out.println(Integer.toHexString(x[1][1].hashCode()));
    }

    @Test
    public void test_system_arraycopy() {
        int[] i = new int[3];
        i[0] = 0;
        i[1] = 1;
        i[2] = 2;

        int[] j = new int[3];

        System.arraycopy(i, 0, j, 0, 3);

        System.out.println(Arrays.toString(j));
    }

    @Test
    public void test_deque() {
        Deque<String> deque = new LinkedList<>();
        deque.offer("");
        deque.push("");
        deque.pop();
        deque.peek();
        String peek = deque.peek();

        deque = new ArrayDeque<>();
        deque.push("");

        deque = new LinkedBlockingDeque<>();
        deque.push("");

        deque = new ConcurrentLinkedDeque<>();
        deque.push("");

        Queue delayQueue = new DelayQueue<>();
        delayQueue.add(null);
    }

    @Test
    public void test_queue() throws InterruptedException {
        Queue<Job> queue = new DelayQueue<>();
        queue.add(new Job("1号", 1000L));
        queue.add(new Job("3号", 3000L));
        queue.add(new Job("4号", 4000L));
        queue.add(new Job("5号", 5000L));
        queue.add(new Job("2号", 2000L));

        while (true) {
            Job poll = queue.poll();
            if (null == poll) {
                Thread.sleep(10);
                continue;
            }
            System.out.println(poll.getName());
        }
    }

    class Job implements Delayed {

        private String name;
        private Long begin;
        private Long delayTime;

        public Job(String name, Long delayTime) {
            this.name = name;
            this.begin = System.currentTimeMillis();
            this.delayTime = delayTime;//延时时长
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(begin + delayTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Delayed o) {
            Job job = (Job) o;
            return (int) (this.getDelay(TimeUnit.MICROSECONDS) - job.getDelay(TimeUnit.MICROSECONDS));
        }
    }

}