package com.shin.clinesyncbackground.mapper;

import com.shin.clinesyncbackground.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MovieMapper {
    
    /**
     * 插入一部新电影
     * @param movie 电影对象
     * @return 受影响的行数
     */
    @Insert("INSERT INTO movie(name, rating, image, create_time, update_time) VALUES(#{name}, #{rating}, #{image}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Movie movie);
    
    /**
     * 根据ID查询电影
     * @param id 电影ID
     * @return 电影对象
     */
    @Select("SELECT id, name, rating, image, create_time, update_time FROM movie WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "rating", column = "rating"),
        @Result(property = "image", column = "image"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    Movie selectById(Long id);
    
    /**
     * 查询所有电影
     * @return 电影列表
     */
    @Select("SELECT id, name, rating, image, create_time, update_time FROM movie")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "rating", column = "rating"),
        @Result(property = "image", column = "image"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    List<Movie> selectAll();
    
    /**
     * 更新电影信息
     * @param movie 电影对象
     * @return 受影响的行数
     */
    @Update("UPDATE movie SET name = #{name}, rating = #{rating}, image = #{image}, update_time = #{updateTime} WHERE id = #{id}")
    int update(Movie movie);
    
    /**
     * 根据ID删除电影
     * @param id 电影ID
     * @return 受影响的行数
     */
    @Delete("DELETE FROM movie WHERE id = #{id}")
    int deleteById(Long id);
}