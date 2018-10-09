package com.ddl.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 普通加权轮询算法
 * (该算法有个缺陷，就是某些情况下生成的序列是不均匀的。)
 *     这背后的数学原理，自己思考了一下，总结如下：
 * <p>
 * 　　current_weight的值，其变化序列就是一个等差序列：max, max-gcd, max-2gcd, …, 0(max)，
 * 将current_weight从max变为0的过程，称为一个轮回。
 * <p>
 * 　　针对每个current_weight，该算法就是要把服务器数组从头到尾扫描一遍，将其中权重大于等于current_weight的所有服务器填充到结果序列中。
 * 扫描完一遍服务器数组之后，将current_weight变为下一个值，再一次从头到尾扫描服务器数组。
 * <p>
 * 　　在current_weight变化过程中，不管current_weight当前为何值，具有max权重的服务器每次肯定会被选中。
 * 因此，具有max权重的服务器会在序列中出现max/gcd次（等差序列中的项数）。
 * <p>
 * 　　更一般的，当current_weight变为x之后，权重为x的服务器，在current_weight接下来的变化过程中，
 * 每次都会被选中，因此，具有x权重的服务器，会在序列中出现x/gcd次。
 * 所以，每个服务器在结果序列中出现的次数，是与其权重成正比的，这就是符合加权轮询算法的要求了。
 */
public class WeightedRoundRobinScheduling {
    // 上一次选择的服务器
    private int currentIndex = -1;

    // 当前调度的权值
    private int currentWeight = 0;

    // 最大权重
    private int maxWeight = 0;

    //所有服务器权重的最大公约数
    private int gcdWeight = 0;

    //服务器数量
    private int serverCount = 0;

    //服务器集合
    private List<Server> serverList;

    /**
     * 返回最大公约数
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) {
        BigInteger b1 = new BigInteger(String.valueOf(a));
        BigInteger b2 = new BigInteger(String.valueOf(b));
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }


    /**
     * 返回所有服务器权重的最大公约数
     * @param serverList
     * @return
     */
    private static int getGCDForServers(List<Server> serverList) {
        int w = 0;
        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
            if (w == 0) {
                w = gcd(serverList.get(i).weight, serverList.get(i + 1).weight);
            } else {
                w = gcd(w, serverList.get(i + 1).weight);
            }
        }
        return w;
    }


    /**
     * 返回所有服务器中的最大权重
     * @param serverList
     * @return
     */
    public static int getMaxWeightForServers(List<Server> serverList) {
        int w = 0;
        for (int i = 0, len = serverList.size(); i < len - 1; i++) {
            if (w == 0) {
                w = Math.max(serverList.get(i).weight, serverList.get(i + 1).weight);
            } else {
                w = Math.max(w, serverList.get(i + 1).weight);
            }
        }
        return w;
    }

    /**
     * 算法流程：
     * 假设有一组服务器 S = {S0, S1, …, Sn-1}
     * 有相应的权重，变量currentIndex表示上次选择的服务器
     * 权值currentWeight初始化为0，currentIndex初始化为-1 ，当第一次的时候返回 权值取最大的那个服务器，
     * 通过权重的不断递减 寻找 适合的服务器返回，直到轮询结束，权值返回为0
     */
    public Server GetServer() {
        while (true) {
            currentIndex = (currentIndex + 1) % serverCount;
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if (currentWeight == 0) {
                        return null;
                    }
                }
            }
            if (serverList.get(currentIndex).weight >= currentWeight) {
                return serverList.get(currentIndex);
            }
        }
    }


    class Server {
        public String ip;

        public int weight;

        public Server(String ip, int weight) {
            super();
            this.ip = ip;
            this.weight = weight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }


    public void init() {
        Server s1 = new Server("192.168.0.100", 4);
        Server s2 = new Server("192.168.0.101", 5);
        Server s3 = new Server("192.168.0.102", 1);
        Server s4 = new Server("192.168.0.103", 2);
        /*Server s5 = new Server("192.168.0.104", 2);*/
        serverList = new ArrayList<Server>();
        serverList.add(s1);
        serverList.add(s2);
        serverList.add(s3);
       serverList.add(s4);
        /* serverList.add(s5);*/

        currentIndex = -1;
        currentWeight = 0;
        serverCount = serverList.size();
        maxWeight = getMaxWeightForServers(serverList);
        gcdWeight = getGCDForServers(serverList);
    }


    public static void main(String[] args) {
        WeightedRoundRobinScheduling obj = new WeightedRoundRobinScheduling();
        obj.init();

        Map<String, Integer> countResult = new HashMap<String, Integer>(100);

        for (int i = 0; i < 100; i++) {
            Server s = obj.GetServer();
            String log = "ip:" + s.ip + ";weight:" + s.weight;
            if (countResult.containsKey(log)) {
                countResult.put(log, countResult.get(log) + 1);
            } else {
                countResult.put(log, 1);
            }
            System.out.println(log);
        }

        for (Entry<String, Integer> map : countResult.entrySet()) {
            System.out.println("服务器 " + map.getKey() + " 请求次数： " + map.getValue());
        }
    }

}
