package net.soumoney.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by jiangxiaojie on 2017/4/6.
 */
public class JedisConnection {
    private String host = "localhost";
    private int port = 6379;
    private String password = "";
    private int database = 0;
    private Jedis jedis;
    public JedisConnection(String host, int port, String password, int database){
        this.host = host;
        this.password = password;
        this.port = port;
        this.database = database;
        this.jedis = new Jedis(this.host, this.port);
        this.auth();
        jedis.select(this.database);
    }

    private void auth(){
        if(this.password != null && !"".equals(this.password)){
            jedis.auth(this.password);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public Jedis getJedis() {
        return jedis;
    }
}
