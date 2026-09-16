package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController2 {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Autowired
   private StringRedisTemplate redis;

   @GetMapping("/")
   public String hello() {
       return "<body bgcolor=yellow> changed!! </body>";
   }

    //table생성
    //member(id, pw)
    //select id from member;
    @GetMapping("/mysql2")
    public String dbTest2() {
        try {

            String sql = "SELECT id FROM member";
            List<String> ids = jdbcTemplate.queryForList(sql, String.class);
            return "member ids : " + ids;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
        }
    }

   @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "<body bgcolor=red> Redis SET OK. key=key, value=100 </body>";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "<body bgcolor=red>Redis GET OK. key=key >> " + value + "</body>";

        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }
}