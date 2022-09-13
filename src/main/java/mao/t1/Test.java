package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

/**
 * Project name(项目名称)：java并发编程_StampedLock
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/13
 * Time(创建时间)： 11:00
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 数据
     */
    private int data;

    /**
     * StampedLock
     */
    private final StampedLock stampedLock = new StampedLock();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    /**
     * Instantiates a new Test.
     *
     * @param data the data
     */
    public Test(int data)
    {
        this.data = data;
    }

    /**
     * 睡眠
     *
     * @param time 时间
     */
    private static void sleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 读
     *
     * @param sleepTime 睡眠时间
     * @return int
     */
    public int read(int sleepTime)
    {
        long read = stampedLock.tryOptimisticRead();
        sleep(sleepTime);
        //验证
        if (stampedLock.validate(read))
        {
            log.debug("直接返回数据，" + read);
            return data;
        }
        //验证失败
        //锁升级
        log.debug("锁升级为读锁，尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
            sleep(sleepTime);
            return data;
        }
        finally
        {
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }


    /**
     * 写
     *
     * @param newData   新数据
     * @param sleepTime 睡眠时间
     */
    public void write(int newData, int sleepTime)
    {
        //获取写锁
        log.debug("尝试获取写锁");
        long writeLock = stampedLock.writeLock();
        try
        {
            log.debug("获取写锁成功");
            sleep(sleepTime);
            this.data = newData;
        }
        finally
        {
            log.debug("释放写锁");
            stampedLock.unlockWrite(writeLock);
        }
    }


    /**
     * main方法
     *
     * @param args 参数
     */
    public static void main(String[] args)
    {
        Test t = new Test(10);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int read = t.read(30);
                log.debug("结果：" + read);
            }
        }, "read1").start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int read = t.read(30);
                log.debug("结果：" + read);
            }
        }, "read2").start();
    }
}
