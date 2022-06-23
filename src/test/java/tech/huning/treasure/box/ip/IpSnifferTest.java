package tech.huning.treasure.box.ip;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP探测器测试
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class IpSnifferTest {

    private static final Logger logger = LoggerFactory.getLogger(IpSnifferTest.class);

    @Test
    public void test() {
        IpSniffer.getHostIpList().forEach(ip -> logger.info("ip:{}", ip));
    }

}
