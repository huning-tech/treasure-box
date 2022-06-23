package tech.huning.treasure.box.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IP工具类
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class IpSniffer {

    private static final Logger logger = LoggerFactory.getLogger(IpSniffer.class);

    /**
     * 获取主机IP地址
     * @return 本地IP地址
     */
    public static String getHostIp() {
        return getHostIp(null);
    }

    /**
     * 根据网卡名获取主机IP地址
     * @return 本地IP地址
     */
    public static String getHostIp(String interfaceName) {
        String ip = "";
        interfaceName = interfaceName.trim();
        try {
            List<String> ipList = getHostAddress(interfaceName);
            ip = (!ipList.isEmpty()) ? ipList.get(0) : "";
        } catch (Exception e) {
            logger.warn("sniffer ip failure", e);
        }
        return ip;
    }

    /**
     * 获取主机IP地址列表
     * @return 本地IP地址列表
     */
    public static List<String> getHostIpList() {
        List<String> ipList = new ArrayList<>();
        try {
            ipList = getHostAddress(null);
        } catch (Exception e) {
            logger.warn("sniffer ip failure", e);
        }
        return ipList;
    }

    /**
     * 根据网卡名获取主机IP地址列表
     * @return 本地IP地址列表
     */
    public static List<String> getHostIpList(String interfaceName) {
        List<String> ipList = new ArrayList<>();
        try {
            ipList = getHostAddress(interfaceName);
        } catch (Exception e) {
            logger.warn("sniffer ip failure", e);
        }
        return ipList;
    }

    /**
     * 获取已激活网卡的IP地址
     *
     * @param interfaceName 可指定网卡名称,null则获取全部
     * @return IP地址列表
     */
    private static List<String> getHostAddress(String interfaceName) throws SocketException {
        List<String> ipList = new ArrayList<>(5);
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> allAddress = ni.getInetAddresses();
            while (allAddress.hasMoreElements()) {
                InetAddress address = allAddress.nextElement();
                if (address.isLoopbackAddress()) {
                    // skip the loopback addr
                    continue;
                }
                if (address instanceof Inet6Address) {
                    // skip the IPv6 addr
                    continue;
                }
                String hostAddress = address.getHostAddress();
                System.out.println(ni.getDisplayName());
                if (null == interfaceName) {
                    ipList.add(hostAddress);
                } else if (interfaceName.equals(ni.getDisplayName())) {
                    ipList.add(hostAddress);
                }
            }
        }
        return ipList;
    }
}
