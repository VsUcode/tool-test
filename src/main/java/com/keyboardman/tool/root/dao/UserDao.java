package com.keyboardman.tool.root.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    String TABLE_NAME = " user_root ";
    String INSERT_FIELDS = " username, password, power, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update " + TABLE_NAME + " set status=#{status} where id=#{id}"})
    int deleteUser(@Param("id") int id, @Param("status") int status);

    @Update({"update " + TABLE_NAME + " set password=#{password} where id=#{id}"})
    int updatePassword(@Param("id") int id, @Param("password") String password);
}
