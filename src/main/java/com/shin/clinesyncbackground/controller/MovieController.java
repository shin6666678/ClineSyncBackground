package com.shin.clinesyncbackground.controller;

import com.shin.clinesyncbackground.entity.Movie;
import com.shin.clinesyncbackground.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@io.swagger.v3.oas.annotations.tags.Tag(name = "电影管理", description = "电影信息的增删改查接口")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    /**
     * 创建电影
     * @param name 电影名称
     * @param rating 电影评分
     * @param image 图片文件
     * @return 创建的电影对象
     * @throws IOException 文件读取异常
     */
    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "创建电影", description = "上传电影信息和图片")
    public ResponseEntity<Movie> createMovie(
            @RequestParam String name,
            @RequestParam BigDecimal rating,
            @RequestParam("image") MultipartFile image) throws IOException {
        
        Movie movie = new Movie();
        movie.setName(name);
        movie.setRating(rating);
        movie.setImage(image.getBytes());
        
        Movie savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }
    
    /**
     * 根据ID获取电影
     * @param id 电影ID
     * @return 电影对象
     */
    @GetMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "根据ID获取电影", description = "根据电影ID获取电影详细信息")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取电影信息")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "电影不存在")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * 获取所有电影
     * @return 电影列表
     */
    @GetMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "获取所有电影", description = "获取系统中所有的电影列表")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    
    /**
     * 获取电影图片
     * @param id 电影ID
     * @return 图片数据
     */
    @GetMapping("/{id}/image")
    @io.swagger.v3.oas.annotations.Operation(summary = "获取电影图片", description = "根据电影ID获取电影图片数据")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功获取图片")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "图片不存在")
    public ResponseEntity<byte[]> getMovieImage(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null && movie.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(movie.getImage().length);
            return new ResponseEntity<>(movie.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * 更新电影
     * @param id 电影ID
     * @param name 电影名称
     * @param rating 电影评分
     * @param image 图片文件
     * @return 更新后的电影对象
     * @throws IOException 文件读取异常
     */
    @PutMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "更新电影", description = "根据电影ID更新电影信息")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "更新成功")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "电影不存在")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam BigDecimal rating,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        movie.setName(name);
        movie.setRating(rating);
        if (image != null && !image.isEmpty()) {
            movie.setImage(image.getBytes());
        }
        
        boolean updated = movieService.updateMovie(movie);
        if (updated) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 删除电影
     * @param id 电影ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "删除电影", description = "根据电影ID删除电影")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "删除成功")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "电影不存在")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}