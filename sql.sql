-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- 用户表
CREATE TABLE `blog_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `phone` varchar(255) NOT NULL COMMENT '电话号码',
  `registertime` varchar(255) NOT NULL COMMENT '注册时间',
  `power` int(1) NOT NULL COMMENT '用户权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 文章表
create table `blog_article`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `article_type` int(11) NOT NULL COMMENT '文章类型',
  `author` varchar(255) NOT NULL COMMENT '作者',
  `show_img` varchar(255) NOT NULL COMMENT '文章显示的时候的展示图片',
  `content` text NOT NULL COMMENT '文章内容',
  `article_describe` text NOT NULL COMMENT '文章描述',
  `upload_time` varchar(255) NOT NULL COMMENT '上传时间',
  `origin_img` varchar(255) NOT NULL COMMENT '图片原地址',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 图片表
create table `blog_img`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_path` varchar(255) NOT NULL COMMENT '图片路径',
  `content` text NOT NULL COMMENT '图片文字内容',
  `upload_time` varchar(255) NOT NULL COMMENT '上传时间',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 测试表
create table `blog_test`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 个性签名表
create table `blog_person_sign`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `sign_name` text NOT NULL COMMENT '个性签名',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 文章分类表
create table `blog_article_type`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(255) NOT NULL COMMENT '类型名',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 访问统计表
create table `blog_visit_count`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(255) NOT NULL COMMENT '访问ip',
  `referer` text NOT NULL COMMENT 'referer(前一个页面)',
  `visit_count` bigint(20) NOT NULL COMMENT '计数，目前只用到这个',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;