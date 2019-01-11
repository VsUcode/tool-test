package com.keyboardman.tool.zhangjinsen.dao;

import com.keyboardman.tool.zhangjinsen.model.UserZJS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ZjsLoginDao {


    String TABLE_NAME = " zjs_user ";
    String INSERT_FIELDS = " username, password, phone, power, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username} and status=#{status}"})
    UserZJS selectByName(@Param("username") String username, @Param("status") int status) ;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{username},#{password},#{phone},#{power},#{status})"})
    int addUser(UserZJS user);
}
