package com.shin.clinesyncbackground.service;

import com.shin.clinesyncbackground.entity.Movie;
import com.shin.clinesyncbackground.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    
    @Autowired
    private MovieMapper movieMapper;
    
    /**
     * 保存电影
     * @param movie 电影对象
     * @return 保存后的电影对象
     */
    public Movie saveMovie(Movie movie) {
        movieMapper.insert(movie);
        return movie;
    }
    
    /**
     * 根据ID获取电影
     * @param id 电影ID
     * @return 电影对象
     */
    public Movie getMovieById(Long id) {
        return movieMapper.selectById(id);
    }
    
    /**
     * 获取所有电影
     * @return 电影列表
     */
    public List<Movie> getAllMovies() {
        return movieMapper.selectAll();
    }
    
    /**
     * 更新电影
     * @param movie 电影对象
     * @return 更新是否成功
     */
    public boolean updateMovie(Movie movie) {
        return movieMapper.update(movie) > 0;
    }
    
    /**
     * 删除电影
     * @param id 电影ID
     * @return 删除是否成功
     */
    public boolean deleteMovie(Long id) {
        return movieMapper.deleteById(id) > 0;
    }
}