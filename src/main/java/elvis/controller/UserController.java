package elvis.controller;

import elvis.bo.User;
import elvis.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public ResponseEntity<List<User>> list() {
        List<User> list = userMapper.selectList(null);
        log.info(list.toString());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user) {
        log.info("input User:" + user);
        return userMapper.insert(user) > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }
}
