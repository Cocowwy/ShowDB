create database oms


-- ----------------------------
-- Table structure for oms_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_cart_item`;
CREATE TABLE `oms_cart_item`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `product_id`          bigint(20) DEFAULT NULL,
    `product_sku_id`      bigint(20) DEFAULT NULL,
    `member_id`           bigint(20) DEFAULT NULL,
    `quantity`            int(11) DEFAULT NULL COMMENT '购买数量',
    `price`               decimal(10, 2) DEFAULT NULL COMMENT '添加到购物车的价格',
    `product_pic`         varchar(1000)  DEFAULT NULL COMMENT '商品主图',
    `product_name`        varchar(500)   DEFAULT NULL COMMENT '商品名称',
    `product_sub_title`   varchar(500)   DEFAULT NULL COMMENT '商品副标题（卖点）',
    `product_sku_code`    varchar(200)   DEFAULT NULL COMMENT '商品sku条码',
    `member_nickname`     varchar(500)   DEFAULT NULL COMMENT '会员昵称',
    `create_date`         datetime       DEFAULT NULL COMMENT '创建时间',
    `modify_date`         datetime       DEFAULT NULL COMMENT '修改时间',
    `delete_status`       int(1) DEFAULT '0' COMMENT '是否删除',
    `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类',
    `product_brand`       varchar(200)   DEFAULT NULL,
    `product_sn`          varchar(200)   DEFAULT NULL,
    `product_attr`        varchar(500)   DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of oms_cart_item
-- ----------------------------
INSERT INTO `oms_cart_item`
VALUES ('12', '26', '90', '1', '1', '3788.00', null, '华为 HUAWEI P20',
        'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待', '201806070026001', 'windir', '2018-08-27 16:53:44', null,
        '1', '19', null, null, null);
INSERT INTO `oms_cart_item`
VALUES ('13', '27', '98', '1', '3', '2699.00', null, '小米8', '骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购',
        '201808270027001', 'windir', '2018-08-27 17:11:53', null, '1', '19', null, null, null);
INSERT INTO `oms_cart_item`
VALUES ('14', '28', '102', '1', '1', '649.00', null, '红米5A', '8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购',
        '201808270028001', 'windir', '2018-08-27 17:18:02', null, '1', '19', null, null, null);
INSERT INTO `oms_cart_item`
VALUES ('15', '28', '103', '1', '1', '699.00', null, '红米5A', '8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购',
        '201808270028001', 'windir', '2018-08-28 10:22:45', null, '1', '19', null, null, null);
INSERT INTO `oms_cart_item`
VALUES ('16', '29', '106', '1', '1', '5499.00', null, 'Apple iPhone 8 Plus',
        '【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。', '201808270029001',
        'windir', '2018-08-28 10:50:50', null, '1', '19', null, null, null);
INSERT INTO `oms_cart_item`
VALUES ('19', '36', '163', '1', '3', '100.00',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', '202002210036001', 'windir', '2020-02-25 15:51:59',
        null, '1', '29', 'NIKE', '6799345',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"秋季\"}]');
INSERT INTO `oms_cart_item`
VALUES ('20', '36', '164', '1', '2', '120.00',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', '202002210036001', 'windir', '2020-02-25 15:54:23',
        null, '1', '29', 'NIKE', '6799345',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"夏季\"}]');
INSERT INTO `oms_cart_item`
VALUES ('21', '36', '164', '1', '2', '120.00',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', '202002210036001', 'windir', '2020-02-25 16:49:53',
        null, '1', '29', 'NIKE', '6799345',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"夏季\"}]');

-- ----------------------------
-- Table structure for oms_company_address
-- ----------------------------
DROP TABLE IF EXISTS `oms_company_address`;
CREATE TABLE `oms_company_address`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `address_name`   varchar(200) DEFAULT NULL COMMENT '地址名称',
    `send_status`    int(1) DEFAULT NULL COMMENT '默认发货地址：0->否；1->是',
    `receive_status` int(1) DEFAULT NULL COMMENT '是否默认收货地址：0->否；1->是',
    `name`           varchar(64)  DEFAULT NULL COMMENT '收发货人姓名',
    `phone`          varchar(64)  DEFAULT NULL COMMENT '收货人电话',
    `province`       varchar(64)  DEFAULT NULL COMMENT '省/直辖市',
    `city`           varchar(64)  DEFAULT NULL COMMENT '市',
    `region`         varchar(64)  DEFAULT NULL COMMENT '区',
    `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公司收发货地址表';

-- ----------------------------
-- Records of oms_company_address
-- ----------------------------
INSERT INTO `oms_company_address`
VALUES ('1', '深圳发货点', '1', '1', '大梨', '18000000000', '广东省', '深圳市', '南山区', '科兴科学园');
INSERT INTO `oms_company_address`
VALUES ('2', '北京发货点', '0', '0', '大梨', '18000000000', '北京市', null, '南山区', '科兴科学园');
INSERT INTO `oms_company_address`
VALUES ('3', '南京发货点', '0', '0', '大梨', '18000000000', '江苏省', '南京市', '南山区', '科兴科学园');

-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`
(
    `id`                      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `member_id`               bigint(20) NOT NULL,
    `coupon_id`               bigint(20) DEFAULT NULL,
    `order_sn`                varchar(64)    DEFAULT NULL COMMENT '订单编号',
    `create_time`             datetime       DEFAULT NULL COMMENT '提交时间',
    `member_username`         varchar(64)    DEFAULT NULL COMMENT '用户帐号',
    `total_amount`            decimal(10, 2) DEFAULT NULL COMMENT '订单总金额',
    `pay_amount`              decimal(10, 2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
    `freight_amount`          decimal(10, 2) DEFAULT NULL COMMENT '运费金额',
    `promotion_amount`        decimal(10, 2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
    `integration_amount`      decimal(10, 2) DEFAULT NULL COMMENT '积分抵扣金额',
    `coupon_amount`           decimal(10, 2) DEFAULT NULL COMMENT '优惠券抵扣金额',
    `discount_amount`         decimal(10, 2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
    `pay_type`                int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
    `source_type`             int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
    `status`                  int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    `order_type`              int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
    `delivery_company`        varchar(64)    DEFAULT NULL COMMENT '物流公司(配送方式)',
    `delivery_sn`             varchar(64)    DEFAULT NULL COMMENT '物流单号',
    `auto_confirm_day`        int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
    `integration`             int(11) DEFAULT NULL COMMENT '可以获得的积分',
    `growth`                  int(11) DEFAULT NULL COMMENT '可以活动的成长值',
    `promotion_info`          varchar(100)   DEFAULT NULL COMMENT '活动信息',
    `bill_type`               int(1) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
    `bill_header`             varchar(200)   DEFAULT NULL COMMENT '发票抬头',
    `bill_content`            varchar(200)   DEFAULT NULL COMMENT '发票内容',
    `bill_receiver_phone`     varchar(32)    DEFAULT NULL COMMENT '收票人电话',
    `bill_receiver_email`     varchar(64)    DEFAULT NULL COMMENT '收票人邮箱',
    `receiver_name`           varchar(100) NOT NULL COMMENT '收货人姓名',
    `receiver_phone`          varchar(32)  NOT NULL COMMENT '收货人电话',
    `receiver_post_code`      varchar(32)    DEFAULT NULL COMMENT '收货人邮编',
    `receiver_province`       varchar(32)    DEFAULT NULL COMMENT '省份/直辖市',
    `receiver_city`           varchar(32)    DEFAULT NULL COMMENT '城市',
    `receiver_region`         varchar(32)    DEFAULT NULL COMMENT '区',
    `receiver_detail_address` varchar(200)   DEFAULT NULL COMMENT '详细地址',
    `note`                    varchar(500)   DEFAULT NULL COMMENT '订单备注',
    `confirm_status`          int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
    `delete_status`           int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
    `use_integration`         int(11) DEFAULT NULL COMMENT '下单时使用的积分',
    `payment_time`            datetime       DEFAULT NULL COMMENT '支付时间',
    `delivery_time`           datetime       DEFAULT NULL COMMENT '发货时间',
    `receive_time`            datetime       DEFAULT NULL COMMENT '确认收货时间',
    `comment_time`            datetime       DEFAULT NULL COMMENT '评价时间',
    `modify_time`             datetime       DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of oms_order
-- ----------------------------
INSERT INTO `oms_order`
VALUES ('12', '1', '2', '201809150101000001', '2018-09-15 12:24:27', 'test', '18732.00', '16377.75', '20.00', '2344.25',
        '0.00', '10.00', '10.00', '0', '1', '4', '0', '', '', '15', '13284', '13284',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '江苏省', '常州市', '天宁区', '东晓街道', '111', '0', '0', null, null, null, null, null,
        '2019-11-09 16:50:28');
INSERT INTO `oms_order`
VALUES ('13', '1', '2', '201809150102000002', '2018-09-15 14:24:29', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '1', '1', '1', '0', '', '', '15', '13284', '13284',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', '1000', '2018-10-11 14:04:19', null, null,
        null, null);
INSERT INTO `oms_order`
VALUES ('14', '1', '2', '201809130101000001', '2018-09-13 16:57:40', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '2', '0', '顺丰快递', '201707196398345', '15', '13284', '13284',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, '2018-10-13 13:44:04',
        '2018-10-16 13:43:41', null, null, null);
INSERT INTO `oms_order`
VALUES ('15', '1', '2', '201809130102000002', '2018-09-13 17:03:00', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '1', '1', '3', '0', '顺丰快递', '201707196398346', '15', '13284', '13284',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '1', '0', null, '2018-10-13 13:44:54',
        '2018-10-16 13:45:01', '2018-10-18 14:05:31', null, null);
INSERT INTO `oms_order`
VALUES ('16', '1', '2', '201809140101000001', '2018-09-14 16:16:16', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '4', '0', null, null, '15', '13284', '13284',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('17', '1', '2', '201809150101000003', '2018-09-15 12:24:27', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '0', '1', '4', '0', '顺丰快递', '201707196398345', '15', null, null,
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, '2018-10-12 14:01:28', null,
        null, null);
INSERT INTO `oms_order`
VALUES ('18', '1', '2', '201809150102000004', '2018-09-15 14:24:29', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '1', '1', '1', '0', '圆通快递', 'xx', '15', null, null,
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', '1000', null, '2018-10-16 14:42:17', null,
        null, null);
INSERT INTO `oms_order`
VALUES ('19', '1', '2', '201809130101000003', '2018-09-13 16:57:40', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '2', '0', null, null, '15', null, null,
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('20', '1', '2', '201809130102000004', '2018-09-13 17:03:00', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '1', '1', '3', '0', null, null, '15', null, null,
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('21', '1', '2', '201809140101000002', '2018-09-14 16:16:16', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '4', '0', null, null, '15', '18682', '18682',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '1', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('22', '1', '2', '201809150101000005', '2018-09-15 12:24:27', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '0', '1', '4', '0', '顺丰快递', '201707196398345', '15', '0', '0',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, '2018-10-12 14:01:28', null,
        null, null);
INSERT INTO `oms_order`
VALUES ('23', '1', '2', '201809150102000006', '2018-09-15 14:24:29', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '1', '1', '1', '0', '顺丰快递', 'xxx', '15', '0', '0',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', '1000', null, '2018-10-16 14:41:28', null,
        null, null);
INSERT INTO `oms_order`
VALUES ('24', '1', '2', '201809130101000005', '2018-09-13 16:57:40', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '2', '0', null, null, '15', '18682', '18682',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '0', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('25', '1', '2', '201809130102000006', '2018-09-13 17:03:00', 'test', '18732.00', '16377.75', '10.00', '2344.25',
        '0.00', '10.00', '5.00', '1', '1', '4', '0', null, null, '15', '18682', '18682',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null,
        '大梨22', '18033441849', '518000', '北京市', '北京城区', '东城区', '东城街道', 'xxx', '0', '0', null, null, null, null, null,
        '2018-10-30 15:08:31');
INSERT INTO `oms_order`
VALUES ('26', '1', '2', '201809140101000003', '2018-09-14 16:16:16', 'test', '18732.00', '16377.75', '0.00', '2344.25',
        '0.00', '10.00', '0.00', '2', '1', '4', '0', null, null, '15', '18682', '18682',
        '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', null, null, null, null, null, '大梨',
        '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', null, '0', '1', null, null, null, null, null, null);
INSERT INTO `oms_order`
VALUES ('27', '1', null, '202002250100000001', '2020-02-25 15:59:20', 'test', '540.00', '540.00', '0.00', '0.00',
        '0.00', '0.00', '0.00', '0', '1', '0', '0', null, null, null, '0', '0', '无优惠,无优惠', null, null, null, null, null,
        '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', null, '0', '1', null, null, null, null, null,
        null);
INSERT INTO `oms_order`
VALUES ('28', '1', null, '202002250100000002', '2020-02-25 16:05:47', 'test', '540.00', '540.00', '0.00', '0.00',
        '0.00', '0.00', '0.00', '0', '1', '0', '0', null, null, null, '0', '0', '无优惠,无优惠', null, null, null, null, null,
        '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', null, '0', '1', null, null, null, null, null,
        null);
INSERT INTO `oms_order`
VALUES ('29', '1', null, '202002250100000003', '2020-02-25 16:07:58', 'test', '540.00', '540.00', '0.00', '0.00',
        '0.00', '0.00', '0.00', '0', '1', '0', '0', null, null, null, '0', '0', '无优惠,无优惠', null, null, null, null, null,
        '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', null, '0', '0', null, null, null, null, null,
        null);
INSERT INTO `oms_order`
VALUES ('30', '1', null, '202002250100000004', '2020-02-25 16:50:13', 'test', '240.00', '240.00', '20.00', '0.00',
        '0.00', '0.00', '10.00', '0', '1', '2', '0', '顺丰快递', '12333333', null, '0', '0', '无优惠', null, null, null, null,
        null, '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', null, '0', '0', null, '2020-02-25 16:53:29',
        '2020-02-25 16:54:03', null, null, '2020-02-25 16:52:51');

-- ----------------------------
-- Table structure for oms_order_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`            bigint(20) DEFAULT NULL COMMENT '订单id',
    `order_sn`            varchar(64)    DEFAULT NULL COMMENT '订单编号',
    `product_id`          bigint(20) DEFAULT NULL,
    `product_pic`         varchar(500)   DEFAULT NULL,
    `product_name`        varchar(200)   DEFAULT NULL,
    `product_brand`       varchar(200)   DEFAULT NULL,
    `product_sn`          varchar(64)    DEFAULT NULL,
    `product_price`       decimal(10, 2) DEFAULT NULL COMMENT '销售价格',
    `product_quantity`    int(11) DEFAULT NULL COMMENT '购买数量',
    `product_sku_id`      bigint(20) DEFAULT NULL COMMENT '商品sku编号',
    `product_sku_code`    varchar(50)    DEFAULT NULL COMMENT '商品sku条码',
    `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
    `promotion_name`      varchar(200)   DEFAULT NULL COMMENT '商品促销名称',
    `promotion_amount`    decimal(10, 2) DEFAULT NULL COMMENT '商品促销分解金额',
    `coupon_amount`       decimal(10, 2) DEFAULT NULL COMMENT '优惠券优惠分解金额',
    `integration_amount`  decimal(10, 2) DEFAULT NULL COMMENT '积分优惠分解金额',
    `real_amount`         decimal(10, 2) DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
    `gift_integration`    int(11) DEFAULT '0',
    `gift_growth`         int(11) DEFAULT '0',
    `product_attr`        varchar(500)   DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='订单中所包含的商品';

-- ----------------------------
-- Records of oms_order_item
-- ----------------------------
INSERT INTO `oms_order_item`
VALUES ('21', '12', '201809150101000001', '26',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '6946605', '3788.00', '1', '90', '201806070026001', '19', '单品促销', '200.00', '2.02', '0.00', '3585.98',
        '3788', '3788', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('22', '12', '201809150101000001', '27',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米', '7437788',
        '2699.00', '3', '98', '201808270027001', '19', '打折优惠：满3件，打7.50折', '674.75', '1.44', '0.00', '2022.81', '2699',
        '2699', '[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('23', '12', '201809150101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '649.00', '1', '102', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '57.60', '0.35', '0.00',
        '591.05', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('24', '12', '201809150101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '699.00', '1', '103', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '62.40', '0.37', '0.00',
        '636.23', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('25', '12', '201809150101000001', '29',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg',
        'Apple iPhone 8 Plus', '苹果', '7437799', '5499.00', '1', '106', '201808270029001', '19', '无优惠', '0.00', '2.94',
        '0.00', '5496.06', '5499', '5499', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('26', '13', '201809150102000002', '26',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '6946605', '3788.00', '1', '90', '201806070026001', '19', '单品促销', '200.00', '2.02', '0.00', '3585.98',
        '3788', '3788', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('27', '13', '201809150102000002', '27',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米', '7437788',
        '2699.00', '3', '98', '201808270027001', '19', '打折优惠：满3件，打7.50折', '674.75', '1.44', '0.00', '2022.81', '2699',
        '2699', '[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('28', '13', '201809150102000002', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '649.00', '1', '102', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '57.60', '0.35', '0.00',
        '591.05', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('29', '13', '201809150102000002', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '699.00', '1', '103', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '62.40', '0.37', '0.00',
        '636.23', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('30', '13', '201809150102000002', '29',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg',
        'Apple iPhone 8 Plus', '苹果', '7437799', '5499.00', '1', '106', '201808270029001', '19', '无优惠', '0.00', '2.94',
        '0.00', '5496.06', '5499', '5499', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('31', '14', '201809130101000001', '26',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '6946605', '3788.00', '1', '90', '201806070026001', '19', '单品促销', '200.00', '2.02', '0.00', '3585.98',
        '3788', '3788', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('32', '14', '201809130101000001', '27',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米', '7437788',
        '2699.00', '3', '98', '201808270027001', '19', '打折优惠：满3件，打7.50折', '674.75', '1.44', '0.00', '2022.81', '2699',
        '2699', '[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('33', '14', '201809130101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '649.00', '1', '102', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '57.60', '0.35', '0.00',
        '591.05', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('34', '14', '201809130101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '699.00', '1', '103', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '62.40', '0.37', '0.00',
        '636.23', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('35', '14', '201809130101000001', '29',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg',
        'Apple iPhone 8 Plus', '苹果', '7437799', '5499.00', '1', '106', '201808270029001', '19', '无优惠', '0.00', '2.94',
        '0.00', '5496.06', '5499', '5499', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('36', '15', '201809130101000001', '26',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '6946605', '3788.00', '1', '90', '201806070026001', '19', '单品促销', '200.00', '2.02', '0.00', '3585.98',
        '3788', '3788', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('37', '15', '201809130101000001', '27',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米', '7437788',
        '2699.00', '3', '98', '201808270027001', '19', '打折优惠：满3件，打7.50折', '674.75', '1.44', '0.00', '2022.81', '2699',
        '2699', '[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('38', '15', '201809130101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '649.00', '1', '102', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '57.60', '0.35', '0.00',
        '591.05', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('39', '15', '201809130101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '699.00', '1', '103', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '62.40', '0.37', '0.00',
        '636.23', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('40', '15', '201809130101000001', '29',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg',
        'Apple iPhone 8 Plus', '苹果', '7437799', '5499.00', '1', '106', '201808270029001', '19', '无优惠', '0.00', '2.94',
        '0.00', '5496.06', '5499', '5499', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('41', '16', '201809140101000001', '26',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '6946605', '3788.00', '1', '90', '201806070026001', '19', '单品促销', '200.00', '2.02', '0.00', '3585.98',
        '3788', '3788', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('42', '16', '201809140101000001', '27',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米', '7437788',
        '2699.00', '3', '98', '201808270027001', '19', '打折优惠：满3件，打7.50折', '674.75', '1.44', '0.00', '2022.81', '2699',
        '2699', '[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('43', '16', '201809140101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '649.00', '1', '102', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '57.60', '0.35', '0.00',
        '591.05', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]');
INSERT INTO `oms_order_item`
VALUES ('44', '16', '201809140101000001', '28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '7437789', '699.00', '1', '103', '201808270028001', '19', '满减优惠：满1000.00元，减120.00元', '62.40', '0.37', '0.00',
        '636.23', '649', '649', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('45', '16', '201809140101000001', '29',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg',
        'Apple iPhone 8 Plus', '苹果', '7437799', '5499.00', '1', '106', '201808270029001', '19', '无优惠', '0.00', '2.94',
        '0.00', '5496.06', '5499', '5499', '[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]');
INSERT INTO `oms_order_item`
VALUES ('46', '27', '202002250100000001', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '100.00', '3', '163',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '100.00', '0', '0', null);
INSERT INTO `oms_order_item`
VALUES ('47', '27', '202002250100000001', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '120.00', '2', '164',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '120.00', '0', '0', null);
INSERT INTO `oms_order_item`
VALUES ('48', '28', '202002250100000002', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '100.00', '3', '163',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '100.00', '0', '0', null);
INSERT INTO `oms_order_item`
VALUES ('49', '28', '202002250100000002', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '120.00', '2', '164',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '120.00', '0', '0', null);
INSERT INTO `oms_order_item`
VALUES ('50', '29', '202002250100000003', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '100.00', '3', '163',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '100.00', '0', '0',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"秋季\"}]');
INSERT INTO `oms_order_item`
VALUES ('51', '29', '202002250100000003', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '120.00', '2', '164',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '120.00', '0', '0',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"夏季\"}]');
INSERT INTO `oms_order_item`
VALUES ('52', '30', '202002250100000004', '36',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg',
        '耐克NIKE 男子 气垫 休闲鞋 AIR MAX 90 ESSENTIAL 运动鞋 AJ1285-101白色41码', 'NIKE', '6799345', '120.00', '2', '164',
        '202002210036001', '29', '无优惠', '0.00', '0.00', '0.00', '120.00', '0', '0',
        '[{\"key\":\"颜色\",\"value\":\"红色\"},{\"key\":\"尺寸\",\"value\":\"38\"},{\"key\":\"风格\",\"value\":\"夏季\"}]');

-- ----------------------------
-- Table structure for oms_order_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_operate_history`;
CREATE TABLE `oms_order_operate_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`     bigint(20) DEFAULT NULL COMMENT '订单id',
    `operate_man`  varchar(100) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
    `create_time`  datetime     DEFAULT NULL COMMENT '操作时间',
    `order_status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    `note`         varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录';

-- ----------------------------
-- Records of oms_order_operate_history
-- ----------------------------
INSERT INTO `oms_order_operate_history`
VALUES ('5', '12', '后台管理员', '2018-10-12 14:01:29', '2', '完成发货');
INSERT INTO `oms_order_operate_history`
VALUES ('6', '13', '后台管理员', '2018-10-12 14:01:29', '2', '完成发货');
INSERT INTO `oms_order_operate_history`
VALUES ('7', '12', '后台管理员', '2018-10-12 14:13:10', '4', '订单关闭:买家退货');
INSERT INTO `oms_order_operate_history`
VALUES ('8', '13', '后台管理员', '2018-10-12 14:13:10', '4', '订单关闭:买家退货');
INSERT INTO `oms_order_operate_history`
VALUES ('9', '22', '后台管理员', '2018-10-15 16:31:48', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('10', '22', '后台管理员', '2018-10-15 16:35:08', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('11', '22', '后台管理员', '2018-10-15 16:35:59', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('12', '17', '后台管理员', '2018-10-15 16:43:40', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('13', '25', '后台管理员', '2018-10-15 16:52:14', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('14', '26', '后台管理员', '2018-10-15 16:52:14', '4', '订单关闭:xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('15', '23', '后台管理员', '2018-10-16 14:41:28', '2', '完成发货');
INSERT INTO `oms_order_operate_history`
VALUES ('16', '13', '后台管理员', '2018-10-16 14:42:17', '2', '完成发货');
INSERT INTO `oms_order_operate_history`
VALUES ('17', '18', '后台管理员', '2018-10-16 14:42:17', '2', '完成发货');
INSERT INTO `oms_order_operate_history`
VALUES ('18', '26', '后台管理员', '2018-10-30 14:37:44', '4', '订单关闭:关闭订单');
INSERT INTO `oms_order_operate_history`
VALUES ('19', '25', '后台管理员', '2018-10-30 15:07:01', '0', '修改收货人信息');
INSERT INTO `oms_order_operate_history`
VALUES ('20', '25', '后台管理员', '2018-10-30 15:08:13', '0', '修改费用信息');
INSERT INTO `oms_order_operate_history`
VALUES ('21', '25', '后台管理员', '2018-10-30 15:08:31', '0', '修改备注信息：xxx');
INSERT INTO `oms_order_operate_history`
VALUES ('22', '25', '后台管理员', '2018-10-30 15:08:39', '4', '订单关闭:2222');
INSERT INTO `oms_order_operate_history`
VALUES ('23', '12', '后台管理员', '2019-11-09 16:50:28', '4', '修改备注信息：111');
INSERT INTO `oms_order_operate_history`
VALUES ('24', '30', '后台管理员', '2020-02-25 16:52:37', '0', '修改费用信息');
INSERT INTO `oms_order_operate_history`
VALUES ('25', '30', '后台管理员', '2020-02-25 16:52:51', '0', '修改费用信息');
INSERT INTO `oms_order_operate_history`
VALUES ('26', '30', '后台管理员', '2020-02-25 16:54:03', '2', '完成发货');

-- ----------------------------
-- Table structure for oms_order_return_apply
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_return_apply`;
CREATE TABLE `oms_order_return_apply`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`           bigint(20) DEFAULT NULL COMMENT '订单id',
    `company_address_id` bigint(20) DEFAULT NULL COMMENT '收货地址表id',
    `product_id`         bigint(20) DEFAULT NULL COMMENT '退货商品id',
    `order_sn`           varchar(64)    DEFAULT NULL COMMENT '订单编号',
    `create_time`        datetime       DEFAULT NULL COMMENT '申请时间',
    `member_username`    varchar(64)    DEFAULT NULL COMMENT '会员用户名',
    `return_amount`      decimal(10, 2) DEFAULT NULL COMMENT '退款金额',
    `return_name`        varchar(100)   DEFAULT NULL COMMENT '退货人姓名',
    `return_phone`       varchar(100)   DEFAULT NULL COMMENT '退货人电话',
    `status`             int(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
    `handle_time`        datetime       DEFAULT NULL COMMENT '处理时间',
    `product_pic`        varchar(500)   DEFAULT NULL COMMENT '商品图片',
    `product_name`       varchar(200)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`      varchar(200)   DEFAULT NULL COMMENT '商品品牌',
    `product_attr`       varchar(500)   DEFAULT NULL COMMENT '商品销售属性：颜色：红色；尺码：xl;',
    `product_count`      int(11) DEFAULT NULL COMMENT '退货数量',
    `product_price`      decimal(10, 2) DEFAULT NULL COMMENT '商品单价',
    `product_real_price` decimal(10, 2) DEFAULT NULL COMMENT '商品实际支付单价',
    `reason`             varchar(200)   DEFAULT NULL COMMENT '原因',
    `description`        varchar(500)   DEFAULT NULL COMMENT '描述',
    `proof_pics`         varchar(1000)  DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
    `handle_note`        varchar(500)   DEFAULT NULL COMMENT '处理备注',
    `handle_man`         varchar(100)   DEFAULT NULL COMMENT '处理人员',
    `receive_man`        varchar(100)   DEFAULT NULL COMMENT '收货人',
    `receive_time`       datetime       DEFAULT NULL COMMENT '收货时间',
    `receive_note`       varchar(500)   DEFAULT NULL COMMENT '收货备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='订单退货申请';

-- ----------------------------
-- Records of oms_order_return_apply
-- ----------------------------
INSERT INTO `oms_order_return_apply`
VALUES ('3', '12', null, '26', '201809150101000001', '2018-10-17 14:34:57', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',
        '华为 HUAWEI P20', '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg',
        null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('4', '12', '2', '27', '201809150101000001', '2018-10-17 14:40:21', 'test', '3585.98', '大梨', '18000000000', '1',
        '2018-10-18 13:54:10', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8',
        '小米', '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', '已经处理了', 'admin', null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('5', '12', '3', '28', '201809150101000001', '2018-10-17 14:44:18', 'test', '3585.98', '大梨', '18000000000', '2',
        '2018-10-18 13:55:28',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', '已经处理了', 'admin', 'admin', '2018-10-18 13:55:58',
        '已经处理了');
INSERT INTO `oms_order_return_apply`
VALUES ('8', '13', null, '28', '201809150102000002', '2018-10-17 14:44:18', 'test', null, '大梨', '18000000000', '3',
        '2018-10-18 13:57:12',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', '理由不够充分', 'admin', null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('9', '14', '2', '26', '201809130101000001', '2018-10-17 14:34:57', 'test', '3500.00', '大梨', '18000000000', '2',
        '2018-10-24 15:44:56',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', '呵呵', 'admin', 'admin',
        '2018-10-24 15:46:35', '收货了');
INSERT INTO `oms_order_return_apply`
VALUES ('10', '14', null, '27', '201809130101000001', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '3',
        '2018-10-24 15:46:57', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8',
        '小米', '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', '就是不退', 'admin', null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('11', '14', '2', '28', '201809130101000001', '2018-10-17 14:44:18', 'test', '591.05', '大梨', '18000000000', '1',
        '2018-10-24 17:09:04',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', '可以退款', 'admin', null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('12', '15', '3', '26', '201809130102000002', '2018-10-17 14:34:57', 'test', '3500.00', '大梨', '18000000000', '2',
        '2018-10-24 17:22:54',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg', '华为 HUAWEI P20',
        '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', '退货了', 'admin', 'admin',
        '2018-10-24 17:23:06', '收货了');
INSERT INTO `oms_order_return_apply`
VALUES ('13', '15', null, '27', '201809130102000002', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '3',
        '2018-10-24 17:23:30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8',
        '小米', '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', '无法退货', 'admin', null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('15', '16', null, '26', '201809140101000001', '2018-10-17 14:34:57', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',
        '华为 HUAWEI P20', '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', null, null, null, null,
        null);
INSERT INTO `oms_order_return_apply`
VALUES ('16', '16', null, '27', '201809140101000001', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米',
        '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('17', '16', null, '28', '201809140101000001', '2018-10-17 14:44:18', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('18', '17', null, '26', '201809150101000003', '2018-10-17 14:34:57', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',
        '华为 HUAWEI P20', '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', null, null, null, null,
        null);
INSERT INTO `oms_order_return_apply`
VALUES ('19', '17', null, '27', '201809150101000003', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米',
        '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('20', '17', null, '28', '201809150101000003', '2018-10-17 14:44:18', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('21', '18', null, '26', '201809150102000004', '2018-10-17 14:34:57', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',
        '华为 HUAWEI P20', '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', null, null, null, null,
        null);
INSERT INTO `oms_order_return_apply`
VALUES ('22', '18', null, '27', '201809150102000004', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米',
        '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('23', '18', null, '28', '201809150102000004', '2018-10-17 14:44:18', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('24', '19', null, '26', '201809130101000003', '2018-10-17 14:34:57', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',
        '华为 HUAWEI P20', '华为', '颜色：金色;内存：16G', '1', '3788.00', '3585.98', '质量问题', '老是卡', '', null, null, null, null,
        null);
INSERT INTO `oms_order_return_apply`
VALUES ('25', '19', null, '27', '201809130101000003', '2018-10-17 14:40:21', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '小米8', '小米',
        '颜色：黑色;内存：32G', '1', '2699.00', '2022.81', '质量问题', '不够高端', '', null, null, null, null, null);
INSERT INTO `oms_order_return_apply`
VALUES ('26', '19', null, '28', '201809130101000003', '2018-10-17 14:44:18', 'test', null, '大梨', '18000000000', '0',
        null, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg', '红米5A', '小米',
        '颜色：金色;内存：16G', '1', '649.00', '591.05', '质量问题', '颜色太土', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for oms_order_return_reason
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_return_reason`;
CREATE TABLE `oms_order_return_reason`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL COMMENT '退货类型',
    `sort`        int(11) DEFAULT NULL,
    `status`      int(1) DEFAULT NULL COMMENT '状态：0->不启用；1->启用',
    `create_time` datetime     DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='退货原因表';

-- ----------------------------
-- Records of oms_order_return_reason
-- ----------------------------
INSERT INTO `oms_order_return_reason`
VALUES ('1', '质量问题', '1', '1', '2018-10-17 10:00:45');
INSERT INTO `oms_order_return_reason`
VALUES ('2', '尺码太大', '1', '1', '2018-10-17 10:01:03');
INSERT INTO `oms_order_return_reason`
VALUES ('3', '颜色不喜欢', '1', '1', '2018-10-17 10:01:13');
INSERT INTO `oms_order_return_reason`
VALUES ('4', '7天无理由退货', '1', '1', '2018-10-17 10:01:47');
INSERT INTO `oms_order_return_reason`
VALUES ('5', '价格问题', '1', '0', '2018-10-17 10:01:57');
INSERT INTO `oms_order_return_reason`
VALUES ('12', '发票问题', '0', '1', '2018-10-19 16:28:36');
INSERT INTO `oms_order_return_reason`
VALUES ('13', '其他问题', '0', '1', '2018-10-19 16:28:51');
INSERT INTO `oms_order_return_reason`
VALUES ('14', '物流问题', '0', '1', '2018-10-19 16:29:01');
INSERT INTO `oms_order_return_reason`
VALUES ('15', '售后问题', '0', '1', '2018-10-19 16:29:11');

-- ----------------------------
-- Table structure for oms_order_setting
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_setting`;
CREATE TABLE `oms_order_setting`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT,
    `flash_order_overtime`  int(11) DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分)',
    `normal_order_overtime` int(11) DEFAULT NULL COMMENT '正常订单超时时间(分)',
    `confirm_overtime`      int(11) DEFAULT NULL COMMENT '发货后自动确认收货时间（天）',
    `finish_overtime`       int(11) DEFAULT NULL COMMENT '自动完成交易时间，不能申请售后（天）',
    `comment_overtime`      int(11) DEFAULT NULL COMMENT '订单完成后自动好评时间（天）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单设置表';

-- ----------------------------
-- Records of oms_order_setting
-- ----------------------------
INSERT INTO `oms_order_setting`
VALUES ('1', '60', '120', '15', '7', '7');

