package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

/**
 * Project name(项目名称)：java并发编程_StampedLock
 * Package(包名): mao.t1
 * Class(类名): Test5
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/13
 * Time(创建时间)： 13:15
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test5
{
    /**
     * 锁
     */
    private static final StampedLock stampedLock = new StampedLock();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test5.class);

    public static void main(String[] args)
    {
        m1();
    }

    private static void m1()
    {
        log.debug("尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
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
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }

    private static void m2()
    {
        log.debug("尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
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
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }

    private static void m3()
    {
        log.debug("尝试获取读锁");
        long readLock = stampedLock.readLock();
        try
        {
            log.debug("读锁获取成功，" + readLock);
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
            log.debug("释放读锁");
            stampedLock.unlockRead(readLock);
        }
    }
}
