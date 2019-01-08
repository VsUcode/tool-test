package com.keyboardman.tool.zhaofeng.dao;

import com.keyboardman.tool.zhaofeng.model.UserZF;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZfLoginDao {

    String TABLE_NAME = " zf_user ";
    String INSERT_FIELDS = " username, password, phone, power, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username} and status=#{status}"})
    UserZF selectByName(@Param("username") String username, @Param("status") int status) ;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
        ") values (#{username},#{password},#{phone},#{power},#{status})"})
    int addUser(UserZF user);
}
