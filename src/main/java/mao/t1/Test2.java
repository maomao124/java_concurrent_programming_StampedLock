package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_StampedLock
 * Package(包名): mao.t1
 * Class(类名): Test2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/13
 * Time(创建时间)： 11:22
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test2
{
    private static final Logger log = LoggerFactory.getLogger(Test2.class);

    public static void main(String[] args) throws InterruptedException
    {
        Test t = new Test(10);


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int read = t.read(500);
                log.debug("结果：" + read);
            }
        }, "read").start();

        Thread.sleep(600);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                t.write(20, 500);
            }
        }, "write").start();

    }
}
