package com.wpw.mybatisbatchoperationxml.mapper;

import com.wpw.mybatisbatchoperationxml.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wpw
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 批量插入用户信息
     *
     * @param userList 用户信息
     * @return 是否插入成功
     */
    int batchInsert(List<User> userList);

    /**
     * 根据查询条件进行数据的查询
     *
     * @param userName 用户名称
     * @param passWord 用户密码
     * @param idList   用户id集合信息
     * @return 用户列表信息
     */
    List<User> batchList(String userName, String passWord, List<Integer> idList);

    /**
     * 根据用户名和密码进行用户信息的查询
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return 用户列表信息
     */
    List<User> listPagination(String userName, String passWord);

    /**
     * 根据数组进行用户列表数据的查询
     *
     * @param ids 数组
     * @return 用户数据信息
     */
    List<User> batchListBaseArray(Integer[] ids);

    /**
     * 查询符合条件的用户信息
     *
     * @param ids map参数信息
     * @return 用户列表信息
     */
    List<User> batchListBaseMap(Object ids);

    /**
     * 获取用户列表数据
     * @return 用户列表数据
     */
    List<User> batchListBaseEmptyArgument();
}
