package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

/**
 * Project name(项目名称)：java并发编程_StampedLock
 * Package(包名): mao.t1
 * Class(类名): Test6
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/13
 * Time(创建时间)： 13:19
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test6
{
    /**
     * 锁
     */
    private static final StampedLock stampedLock = new StampedLock();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test6.class);

    /**
     * main方法
     *
     * @param args 参数
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws InterruptedException
    {
        m1();
    }

    /**
     * m1
     *
     * @throws InterruptedException 中断异常
     */
    private static void m1() throws InterruptedException
    {
        long read = stampedLock.tryOptimisticRead();
        Thread.sleep(200);
        m2();
        //验证
        if (stampedLock.validate(read))
        {
            log.debug("直接返回数据，" + read);
        }
        //验证失败
        //锁升级
        log.debug("锁升级为读锁，尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
            Thread.sleep(200);
            m2();
        }
        finally
        {
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }

    /**
     * m2
     *
     * @throws InterruptedException 中断异常
     */
    private static void m2() throws InterruptedException
    {
        long read = stampedLock.tryOptimisticRead();
        Thread.sleep(200);
        m3();
        //验证
        if (stampedLock.validate(read))
        {
            log.debug("直接返回数据，" + read);
        }
        //验证失败
        //锁升级
        log.debug("锁升级为读锁，尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
            Thread.sleep(200);
            m3();
        }
        finally
        {
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }

    /**
     * m3
     *
     * @throws InterruptedException 中断异常
     */
    private static void m3() throws InterruptedException
    {
        long read = stampedLock.tryOptimisticRead();
        Thread.sleep(200);

        //验证
        if (stampedLock.validate(read))
        {
            log.debug("直接返回数据，" + read);
        }
        //验证失败
        //锁升级
        log.debug("锁升级为读锁，尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
            Thread.sleep(200);

        }
        finally
        {
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }
}
