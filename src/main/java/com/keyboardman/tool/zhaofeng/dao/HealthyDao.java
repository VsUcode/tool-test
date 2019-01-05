package com.keyboardman.tool.zhaofeng.dao;

import com.keyboardman.tool.zhaofeng.model.HealthZF;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HealthyDao {

    String TABLE_NAME = " zf_health ";
    String INSERT_FIELDS = " username, inherited_disease, operation, pestilence, apriority, health, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{username},#{inheritedDisease},#{operation},#{pestilence},#{apriority},#{healthy},#{status})"})
    int insertHealth(HealthZF entity);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username} and status=#{status}"})
    HealthZF selectByUsername(@Param("username") String username, @Param("status") int status);


    @Update({"update " + TABLE_NAME + " set status=#{status} where id=#{id}"})
    int deleteByid(@Param("id") int id, @Param("status") int status);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    HealthZF selectById(String id);
}
