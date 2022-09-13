package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

/**
 * Project name(项目名称)：java并发编程_StampedLock
 * Package(包名): mao.t1
 * Class(类名): Test4
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/13
 * Time(创建时间)： 13:06
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test4
{

    /**
     * 锁
     */
    private static final StampedLock stampedLock = new StampedLock();

    private static final Logger log = LoggerFactory.getLogger(Test4.class);

    public static void main(String[] args)
    {
        m1();
    }

    private static void m1()
    {
        log.debug("尝试获取写锁");
        long writeLock = stampedLock.writeLock();
        try
        {
            log.debug("获取写锁成功");
            try
            {
                Thread.sleep(200);
                m2();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        finally
        {
            log.debug("释放写锁");
            stampedLock.unlockWrite(writeLock);
        }
    }

    private static void m2()
    {
        log.debug("尝试获取写锁");
        long writeLock = stampedLock.writeLock();
        try
        {
            log.debug("获取写锁成功");
            try
            {
                Thread.sleep(200);
                m3();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        finally
        {
            log.debug("释放写锁");
            stampedLock.unlockWrite(writeLock);
        }
    }

    private static void m3()
    {
        log.debug("尝试获取写锁");
        long writeLock = stampedLock.writeLock();
        try
        {
            log.debug("获取写锁成功");
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        finally
        {
            log.debug("释放写锁");
            stampedLock.unlockWrite(writeLock);
        }
    }
}
