package com.keyboardman.tool.root.dao;

import com.keyboardman.tool.root.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao {

    String TABLE_NAME = " user_root ";
    String INSERT_FIELDS = " username, password, power, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username} and status=#{status}"})
    User selectByName(@Param("username") String username, @Param("status") int status) ;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
        ") values (#{username},#{password},#{power},#{status})"})
    int addUser(User user);
}
