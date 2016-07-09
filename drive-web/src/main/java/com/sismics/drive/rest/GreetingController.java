package com.sismics.drive.rest;

import com.sismics.drive.exception.ClientException;
import com.sun.deploy.util.SessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @Transactional(rollbackFor = ClientException.class)
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) throws ClientException {
        JsonArrayBuilder json = Json.createArrayBuilder();

        jdbcTemplate.query("select * from BOOKINGS", (RowMapper<String>) (rs, rowNum) -> {
            return rs.getString("FIRST_NAME");
        }).forEach(s -> json.add(s));

        if (jdbcTemplate == null) {
            throw new ClientException("Dummy error");
        }

        return Json.createObjectBuilder()
                .add("id", counter.incrementAndGet())
                .add("content", json)
                .build()
                .toString();
    }
}