import redis.clients.jedis.Jedis;

/**
 * Created by jiangxiaojie on 2017/4/6.
 */
public class Test {
    public static void main(String[] args){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.publish("PACKET", "hello");
        jedis.close();
    }
}
