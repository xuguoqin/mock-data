package com.haizhi.mock.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xuguoqin
 * @date 2019/7/16 4:41 PM
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/testJdbc")
    public List<Map<String, Object>> testJdbc() {
        String sql = "select * from tb3";
        return jdbcTemplate.queryForList(sql);
    }

    @RequestMapping
    public String test() {
        return "ok";
    }

    @RequestMapping("exception")
    public void exception() {
        int i = 1 / 0;
    }
}
