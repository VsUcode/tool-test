package com.keyboardman.tool.zhaofeng.dao;

import com.keyboardman.tool.zhaofeng.model.PrebookZF;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PrebookDao {

    String TABLE_NAME = " zf_prebook ";
    String INSERT_FIELDS = " username, type, starttime, endtime, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{username},#{type},#{starttime},#{endtime},#{status})"})
    int insertBook(PrebookZF entity);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username} and status=#{status}"})
    PrebookZF selectByUsername(@Param("username") String username, @Param("status") int status);

    @Update({"update "+ TABLE_NAME + " set status=#{status} where id=#{id}"})
    int deleteByid(@Param("id") int id, @Param("status") int status);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where type=#{type} and status=#{status} and starttime>#{now}"})
    List<PrebookZF> selectBookList(@Param("type") String type, @Param("status") int status, @Param("now") Date now);
}
