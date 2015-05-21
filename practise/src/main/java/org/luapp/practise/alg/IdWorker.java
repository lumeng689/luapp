package org.luapp.practise.alg;

import java.util.Date;
import java.util.Random;

/**
 * Snowflake是twitter开源的一款独立的适用于分布式环境的ID生成算法。
 * 生成的ID是64Bits整型数，同时满足高性能（>10Kids/s），低延迟（<2ms）和高可用。
 * 1. 42位的时间序列（精确到毫秒，42位的长度可以使用139.5年）
 * 2. 10位的机器标识（10位的长度最多支持部署1024个节点，支持多机房的分布式，需要使用zookeeper）
 * 3. 12位的计数顺序号（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号） 最高位是符号位，始终为0。
 * <p/>
 * 对twitter而言这样的ID生成方案满足：
 * <p/>
 * 1.每秒能够生成足够的ID数。
 * 2.生成的ID按照时间大致有序。
 * <p/>
 * Created by lum on 2015/5/21.
 */
public class IdWorker {
    //自定义的开始纪元 Thu Nov 04 09:42:54 CST 2010  后面时间戳都会减去本值
    private final static long twepoch = 1288834974657L;

    private Random rand = new Random();
    //机器标识位数
    private long workerIdBits = 5L;
    //数据中心标识位数
    private long datacenterIdBits = 5L;
    //机器ID最大值
    private long maxWorkerId = -1L ^ (-1L << workerIdBits); //2^5-1
    //数据中心ID最大值
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //2^5-1
    //毫秒内自增位 4096 也就是说每个节点1ms最多产生4096个id
    private long sequenceBits = 12L;
    //机器ID偏左移12位
    private long workerIdShift = sequenceBits;// 12
    //数据中心ID左移17位
    private long datacenterIdShift = sequenceBits + workerIdBits; // 12 + 5 = 17
    //时间毫秒左移22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;// 12 + 5 + 5 =22
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;
    private long datacenterId;
    private long workerId;
    private long sequence = 0L;

    public IdWorker(final long datacenterId, final long workerId) {
        super();
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId can't be greater than %d or less than 0", maxWorkerId));
        }

        this.datacenterId = datacenterId;

        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();

        // lastTimestamp中记录着上一次产生id的时间戳
        if (timestamp < this.lastTimestamp) {
            //小于，机器时间出问题了
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //相等，递增sequence id
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            //递增过程中sequence为0了，表明sequence 值用尽了(4096)，等待下一个ms的到来。
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                this.sequence = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        //记录此次产生id的时间戳
        this.lastTimestamp = timestamp;
        // 通过shift组装返回id
        long nextId = ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdBits) |
                (workerId << workerIdShift) |
                sequence;

        return nextId;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        IdWorker worker = new IdWorker(2l, 3l);
        System.out.println(Long.toBinaryString(worker.nextId()));

        System.out.println(new Date(twepoch));
    }
}
