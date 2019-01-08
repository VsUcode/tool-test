package com.keyboardman.tool.zhaofeng.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ZfUserDao {

    String TABLE_NAME = " zf_user ";
    String INSERT_FIELDS = " username, password, phone, power, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update " + TABLE_NAME + " set status=#{status} where id=#{id}"})
    int deleteUser(@Param("id") int id, @Param("status") int status);

    @Update({"update " + TABLE_NAME + " set password=#{password} where id=#{id}"})
    int updatePassword(@Param("id") int id, @Param("password") String password);
}
