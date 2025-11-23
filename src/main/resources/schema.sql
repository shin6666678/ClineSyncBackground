-- 创建电影表
CREATE DATABASE IF NOT EXISTS cline_sync CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE cline_sync;

DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '电影ID',
    name VARCHAR(255) NOT NULL COMMENT '电影名称',
    rating DECIMAL(3,1) DEFAULT 0.0 COMMENT '电影评分',
    image MEDIUMBLOB COMMENT '电影图片（二进制数据）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_name (name),
    INDEX idx_rating (rating),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电影表';