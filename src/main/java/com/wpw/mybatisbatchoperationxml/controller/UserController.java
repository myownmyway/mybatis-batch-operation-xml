package com.wpw.mybatisbatchoperationxml.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wpw.mybatisbatchoperationxml.entity.User;
import com.wpw.mybatisbatchoperationxml.mapper.UserMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wpw
 */
@RestController
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/batchInsert")
    public int batchInsert(@RequestBody JSONObject jsonObject) {
        List<User> userList = jsonObject.getJSONArray("userList").toJavaList(User.class);
        return userMapper.batchInsert(userList);
    }

    @PostMapping(value = "/batchList")
    public List<User> batchList(@RequestBody User user) {
        return userMapper.batchList(user.getUserName(), user.getPassWord(), user.getIdList());
    }

    @PostMapping(value = "/list-pagination")
    public List<User> listPagination(@RequestBody User user) {
        PageHelper.startPage(user.getPageNumber(), user.getPageSize());
        return userMapper.listPagination(user.getUserName(), user.getPassWord());
    }

    @PostMapping(value = "/listByArray")
    public List<User> batchListBaseArray(@RequestBody User user) {
        PageHelper.startPage(user.getPageNumber(), user.getPageSize());
        return userMapper.batchListBaseArray(user.getIds());
    }

    @PostMapping(value = "/listByMap")
    public List<User> batchListBaseMap(@RequestBody(required = false) Map<String, Integer[]> map) {
        List<User> userList = preCheck(map);
        if (!CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        HashMap<String, List<Integer>> hashMap = new HashMap<>(16, 0.75f);
        List<Integer> ids = Stream.of(map.get("ids")).collect(Collectors.toList());
        hashMap.put("ids", ids);
        return userMapper.batchListBaseMap(hashMap);
    }

    private List<User> preCheck(Map<String, Integer[]> map) {
        /**
         * 若获取的参数为null，则直接全表去查，默认第一页，pageNumber=1,pageSize=10
         */
        if (map == null) {
            PageHelper.startPage(1, 10);
            return userMapper.batchListBaseEmptyArgument();
        }
        return null;
    }
}
