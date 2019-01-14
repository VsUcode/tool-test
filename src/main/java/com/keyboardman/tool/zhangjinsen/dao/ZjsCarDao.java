package com.keyboardman.tool.zhangjinsen.dao;

import com.keyboardman.tool.zhangjinsen.model.CarZJS;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ZjsCarDao {

    String TABLE_NAME = " zjs_car ";
    String INSERT_FIELDS = " booker, booker_phone, car_user, user_phone, starttime, endtime, start_site, end_site, status, remark, spyj, bookid, feedback ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    String SELECT_CHECK_FIELDS = " id, booker, booker_phone, starttime, endtime, status";
    String SELECT_BOOKID_FIELDS = " id, car_user, user_phone, starttime, endtime, status ";
    String SELECT_HANDLE_FIELDS = " id, car_user, user_phone, starttime, endtime, status";


    @Select({"select ", SELECT_CHECK_FIELDS, " from ", TABLE_NAME, " where status in (0,1) and starttime between #{starttime} and #{endtime} order by starttime"})
    List<CarZJS> selectCheckList(@Param("starttime") Date starttime, @Param("endtime") Date endtime);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{booker},#{bookerPhone},#{carUser},#{userPhone},#{starttime},#{endtime},#{startSite},#{endSite},#{status},#{remark},#{spyj},#{bookid},#{feedback})"})
    int addBook(CarZJS car);

    @Select({"select ", SELECT_BOOKID_FIELDS, " from ", TABLE_NAME, " where bookid=#{bookid}"})
    CarZJS selectCarByBookid(String bookid);

    @Update({"update " + TABLE_NAME + " set feedback=#{feedback} where bookid=#{bookid}"})
    int addBookFeedback(@Param("bookid") String bookid, @Param("feedback") String feedback);

    @Select({"select ", SELECT_HANDLE_FIELDS, " from ", TABLE_NAME, " where status=#{status} and starttime>= #{starttime} order by starttime"})
    List<CarZJS> selectHandleList(@Param("starttime") Date starttime, @Param("status") int status);

    @Select({"select ", SELECT_HANDLE_FIELDS, " from ", TABLE_NAME, " where status=#{status}  order by starttime desc"})
    List<CarZJS> selectSuccessList(int status);

    @Select({"select ", SELECT_HANDLE_FIELDS, " from ", TABLE_NAME, " where booker=#{username} and starttime>= #{starttime}  order by starttime desc"})
    List<CarZJS> selectPersonalList(@Param("username") String username, @Param("starttime") Date starttime);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    CarZJS selectCarById(int id);

    @Update({"update " + TABLE_NAME + " set status=#{status},spyj=#{spyj} where id=#{id}"})
    int handleCar(@Param("id") int id, @Param("status") int status, @Param("spyj") String spyj);

    @Update({"update " + TABLE_NAME + " set status=#{status} where id=#{id}"})
    int deleteCar(@Param("id") int id, @Param("status") int status);

    /**
     * 处理跨天的情况
     * @param starttime
     * @return
     */
    @Select({"select ", SELECT_CHECK_FIELDS, " from ", TABLE_NAME, " where status in (0,1) and #{starttime} between starttime and endtime order by starttime"})
    List<CarZJS> selectCheckList1(Date starttime);
}
