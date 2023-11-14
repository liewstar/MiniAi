package com.example.miniaibackend.mapper;

import com.example.miniaibackend.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author 29354
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-11-01 08:53:20
* @Entity com.example.miniaibackend.domain.Message
*/
public interface MessageMapper extends BaseMapper<Message> {

    @Select("SELECT hours.hour, COALESCE(counts.message_count, 0) AS message_count\n" +
            "FROM (\n" +
            "  SELECT 0 AS hour\n" +
            "  UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5\n" +
            "  UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10\n" +
            "  UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15\n" +
            "  UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20\n" +
            "  UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23\n" +
            ") AS hours\n" +
            "LEFT JOIN (\n" +
            "  SELECT HOUR(m.timestamp) AS hour, COUNT(*) AS message_count\n" +
            "  FROM message m\n" +
            "  WHERE DATE(m.timestamp) = #{date}\n" +
            "  GROUP BY HOUR(m.timestamp)\n" +
            ") AS counts ON hours.hour = counts.hour;")
    List<Map<String,String>> selectMessageByHourAndDay(@Param("date") String date);

}




