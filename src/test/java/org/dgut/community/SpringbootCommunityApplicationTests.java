package org.dgut.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
class SpringbootCommunityApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("获取时间" + new Date());

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai:" + sdf.format(d));
    }

}
