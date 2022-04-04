create database cms


-- ----------------------------
-- Table structure for cms_help
-- ----------------------------
DROP TABLE IF EXISTS `cms_help`;
CREATE TABLE `cms_help`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `category_id` bigint(20) DEFAULT NULL,
    `icon`        varchar(500) DEFAULT NULL,
    `title`       varchar(100) DEFAULT NULL,
    `show_status` int(1) DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `read_count`  int(1) DEFAULT NULL,
    `content`     text,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助表';

-- ----------------------------
-- Records of cms_help
-- ----------------------------

-- ----------------------------
-- Table structure for cms_help_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_help_category`;
CREATE TABLE `cms_help_category`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL,
    `icon`        varchar(500) DEFAULT NULL COMMENT '分类图标',
    `help_count`  int(11) DEFAULT NULL COMMENT '专题数量',
    `show_status` int(2) DEFAULT NULL,
    `sort`        int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助分类表';

-- ----------------------------
-- Records of cms_help_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_member_report
-- ----------------------------
DROP TABLE IF EXISTS `cms_member_report`;
CREATE TABLE `cms_member_report`
(
    `id`                 bigint(20) DEFAULT NULL,
    `report_type`        int(1) DEFAULT NULL COMMENT '举报类型：0->商品评价；1->话题内容；2->用户评论',
    `report_member_name` varchar(100) DEFAULT NULL COMMENT '举报人',
    `create_time`        datetime     DEFAULT NULL,
    `report_object`      varchar(100) DEFAULT NULL,
    `report_status`      int(1) DEFAULT NULL COMMENT '举报状态：0->未处理；1->已处理',
    `handle_status`      int(1) DEFAULT NULL COMMENT '处理结果：0->无效；1->有效；2->恶意',
    `note`               varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户举报表';

-- ----------------------------
-- Records of cms_member_report
-- ----------------------------

-- ----------------------------
-- Table structure for cms_prefrence_area
-- ----------------------------
DROP TABLE IF EXISTS `cms_prefrence_area`;
CREATE TABLE `cms_prefrence_area`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL,
    `sub_title`   varchar(255) DEFAULT NULL,
    `pic`         varbinary(500) DEFAULT NULL COMMENT '展示图片',
    `sort`        int(11) DEFAULT NULL,
    `show_status` int(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='优选专区';

-- ----------------------------
-- Records of cms_prefrence_area
-- ----------------------------
INSERT INTO `cms_prefrence_area`
VALUES ('1', '让音质更出众', '音质不打折 完美现场感', null, null, '1');
INSERT INTO `cms_prefrence_area`
VALUES ('2', '让音质更出众22', '让音质更出众22', null, null, null);
INSERT INTO `cms_prefrence_area`
VALUES ('3', '让音质更出众33', null, null, null, null);
INSERT INTO `cms_prefrence_area`
VALUES ('4', '让音质更出众44', null, null, null, null);

-- ----------------------------
-- Table structure for cms_prefrence_area_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `cms_prefrence_area_product_relation`;
CREATE TABLE `cms_prefrence_area_product_relation`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `prefrence_area_id` bigint(20) DEFAULT NULL,
    `product_id`        bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='优选专区和产品关系表';

-- ----------------------------
-- Records of cms_prefrence_area_product_relation
-- ----------------------------
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('1', '1', '12');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('2', '1', '13');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('3', '1', '14');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('4', '1', '18');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('5', '1', '7');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('6', '2', '7');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('7', '1', '22');
INSERT INTO `cms_prefrence_area_product_relation`
VALUES ('24', '1', '23');

-- ----------------------------
-- Table structure for cms_subject
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject`;
CREATE TABLE `cms_subject`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `category_id`      bigint(20) DEFAULT NULL,
    `title`            varchar(100)  DEFAULT NULL,
    `pic`              varchar(500)  DEFAULT NULL COMMENT '专题主图',
    `product_count`    int(11) DEFAULT NULL COMMENT '关联产品数量',
    `recommend_status` int(1) DEFAULT NULL,
    `create_time`      datetime      DEFAULT NULL,
    `collect_count`    int(11) DEFAULT NULL,
    `read_count`       int(11) DEFAULT NULL,
    `comment_count`    int(11) DEFAULT NULL,
    `album_pics`       varchar(1000) DEFAULT NULL COMMENT '画册图片用逗号分割',
    `description`      varchar(1000) DEFAULT NULL,
    `show_status`      int(1) DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
    `content`          text,
    `forward_count`    int(11) DEFAULT NULL COMMENT '转发数',
    `category_name`    varchar(200)  DEFAULT NULL COMMENT '专题分类名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='专题表';

-- ----------------------------
-- Records of cms_subject
-- ----------------------------
INSERT INTO `cms_subject`
VALUES ('1', '1', 'polo衬衫的也时尚', null, null, null, '2018-11-11 13:26:55', null, null, null, null, null, null, null, null,
        '服装专题');
INSERT INTO `cms_subject`
VALUES ('2', '2', '大牌手机低价秒', null, null, null, '2018-11-12 13:27:00', null, null, null, null, null, null, null, null,
        '手机专题');
INSERT INTO `cms_subject`
VALUES ('3', '2', '晓龙845新品上市', null, null, null, '2018-11-13 13:27:05', null, null, null, null, null, null, null, null,
        '手机专题');
INSERT INTO `cms_subject`
VALUES ('4', '1', '夏天应该穿什么', null, null, null, '2018-11-01 13:27:09', null, null, null, null, null, null, null, null,
        '服装专题');
INSERT INTO `cms_subject`
VALUES ('5', '1', '夏季精选', null, null, null, '2018-11-06 13:27:18', null, null, null, null, null, null, null, null,
        '服装专题');
INSERT INTO `cms_subject`
VALUES ('6', '2', '品牌手机降价', null, null, null, '2018-11-07 13:27:21', null, null, null, null, null, null, null, null,
        '手机专题');

-- ----------------------------
-- Table structure for cms_subject_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject_category`;
CREATE TABLE `cms_subject_category`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `name`          varchar(100) DEFAULT NULL,
    `icon`          varchar(500) DEFAULT NULL COMMENT '分类图标',
    `subject_count` int(11) DEFAULT NULL COMMENT '专题数量',
    `show_status`   int(2) DEFAULT NULL,
    `sort`          int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='专题分类表';

-- ----------------------------
-- Records of cms_subject_category
-- ----------------------------
INSERT INTO `cms_subject_category`
VALUES ('1', '服装专题', null, null, null, null);
INSERT INTO `cms_subject_category`
VALUES ('2', '手机专题', null, null, null, null);

-- ----------------------------
-- Table structure for cms_subject_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject_comment`;
CREATE TABLE `cms_subject_comment`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `subject_id`       bigint(20) DEFAULT NULL,
    `member_nick_name` varchar(255)  DEFAULT NULL,
    `member_icon`      varchar(255)  DEFAULT NULL,
    `content`          varchar(1000) DEFAULT NULL,
    `create_time`      datetime      DEFAULT NULL,
    `show_status`      int(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题评论表';