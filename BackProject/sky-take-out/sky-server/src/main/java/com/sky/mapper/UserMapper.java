package com.sky.mapper;

import com.sky.entity.Orders;
import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     *
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid();

    /**
     * 插入数据
     * @param user
     */
    void insert(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User getById(Long id);

    /**
     * 根据动态条件统计用户数据
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
