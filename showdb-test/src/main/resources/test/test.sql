-- 测试DEMO
CREATE
DATABASE test_order;
CREATE
DATABASE test_goods;

-- 创建管理员表
DROP TABLE IF EXISTS `shop_admin`;
CREATE TABLE IF NOT EXISTS `shop_admin`
(
    `adminid`
    int
    unsigned
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键ID',
    `adminuser`
    varchar
(
    32
) NOT NULL DEFAULT '' COMMENT '管理员账号',
    `adminpass` CHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '管理员密码',
    `adminemail` varchar
(
    50
) NOT NULL DEFAULT '' COMMENT '管理员电子邮箱',
    `logintime` int unsigned NOT NULL DEFAULT '0' COMMENT '登陆时间',
    `loginip` BIGINT NOT NULL DEFAULT '0' COMMENT '登陆IP',
    `createtime` int unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY
(
    `adminid`
),
    UNIQUE shop_admin_adminuser_adminpass
(
    `adminuser`,
    `adminpass`
),
    UNIQUE shop_admin_adminuser_adminemail
(
    `adminuser`,
    `adminemail`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '管理员表';

insert into `shop_admin`(adminuser, adminpass, adminemail, createtime)
values ('admin', md5(123456), 'maoriaty@sina.com', unix_timestamp())

-- 会员表
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE IF NOT EXISTS `shop_user`
(
    `userid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键id',
    `openid`
    char
(
    32
) NOT NULL DEFAULT '0' COMMENT 'OPENID',
    `username` VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '用户名',
    `userpass` VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '用户密码',
    `useremail` VARCHAR
(
    100
) NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY
(
    `userid`
),
    UNIQUE shop_user_username_userpass
(
    `username`,
    `userpass`
),
    UNIQUE shop_user_useremail_userpass
(
    `useremail`,
    `userpass`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '会员表';

-- 会员详细信息
DROP TABLE IF EXISTS `shop_profile`;
CREATE TABLE IF NOT EXISTS `shop_profile`
(
    `id`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键id',
    `truename`
    VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '真实姓名',
    `age` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '年龄',
    `sex` ENUM
(
    '0',
    '1',
    '2'
) NOT NULL DEFAULT '0' COMMENT '性别',
    `birthday` date NOT NULL DEFAULT '1992-12-05' COMMENT '生日',
    `nickname` VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '昵称',
    `company` VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '公司',
    `userid` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户id',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY
(
    `id`
),
    UNIQUE shop_profile_userid
(
    `userid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '会员详细信息表';

-- 无限级商品分类表
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE IF NOT EXISTS `shop_category`
(
    `cateid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键id',
    `title`
    VARCHAR
(
    32
) NOT NULL DEFAULT '' COMMENT '标题',
    `parentid` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '父级id',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY
(
    `cateid`
),
    KEY shop_category_parentid
(
    `parentid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '无限级商品分类表';

-- 商品表
DROP TABLE IF EXISTS `shop_product`;
CREATE TABLE IF NOT EXISTS `shop_product`
(
    `productid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '商品id',
    `cateid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '商品分类id',
    `title`
    VARCHAR
(
    200
) NOT NULL DEFAULT '' COMMENT '商品标题',
    `description` TEXT COMMENT '商品描述',
    `num` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '库存',
    `price` DECIMAL
(
    10,
    2
) NOT NULL DEFAULT '0.00' COMMENT '价格',
    `cover` VARCHAR
(
    200
) NOT NULL DEFAULT '' COMMENT '封面图',
    `pics` TEXT COMMENT '图片集合',
    `issale` ENUM
(
    '0',
    '1'
) NOT NULL DEFAULT '0' COMMENT '是否促销',
    `saleprice` DECIMAL
(
    10,
    2
) NOT NULL DEFAULT '0.00' COMMENT '促销价格',
    `ishot` ENUM
(
    '0',
    '1'
) NOT NULL DEFAULT '0' COMMENT '是否热卖',
    `ison` ENUM
(
    '0',
    '1'
) NOT NULL DEFAULT '0' COMMENT '是否上架',
    `istui` ENUM
(
    '0',
    '1'
) NOT NULL DEFAULT '0' COMMENT '是否推荐',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY
(
    `productid`
),
    KEY shop_product_cateid
(
    `cateid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品表';

-- 购物车表
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE IF NOT EXISTS `shop_cart`
(
    `cartid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY
    COMMENT
    '购物车id',
    `productid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '商品id',
    `userid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '用户id',
    `productnum`
    INT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '加入购物车的商品数量',
    `price`
    DECIMAL
(
    10,
    2
) NOT NULL DEFAULT '0.00' COMMENT '加入购物车时的商品价格',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    KEY shop_cart_userid
(
    `userid`
),
    KEY shop_cart_productid
(
    `productid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '购物车表'

-- 订单表
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE IF NOT EXISTS `shop_order`
(
    `orderid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY
    COMMENT
    '订单id',
    `userid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '下单人id',
    `amount`
    DECIMAL
(
    10,
    2
) NOT NULL DEFAULT '0.00' COMMENT '订单总价',
    `status` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '订单状态',
    `addressid` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '收货地址',
    `expressid` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '快递id',
    `expressno` VARCHAR
(
    50
) NOT NULL DEFAULT '' COMMENT '快递单号',
    `tradeno` VARCHAR
(
    100
) NOT NULL DEFAULT '' COMMENT '支付交易号',
    `tradeext` TEXT COMMENT '支付信息',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    KEY shop_order_userid
(
    `userid`
),
    KEY shop_order_addressid
(
    `addressid`
),
    KEY shop_order_expressid
(
    `expressid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单表';

-- 订单详情表
DROP TABLE IF EXISTS `shop_order_detail`;
CREATE TABLE IF NOT EXISTS `shop_order_detail`
(
    `detailid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY
    COMMENT
    '订单详情id',
    `orderid`
    BIGINT
    UNSIGNED
    NOT
    NULL
    DEFAULT
    '0'
    COMMENT
    '订单id',
    `productid`
    BIGINT
    UNSIGNED
    NULL
    DEFAULT
    '0'
    COMMENT
    '商品id',
    `price`
    DECIMAL
(
    10,
    2
) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `productnum` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品数量',
    `createtime` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建时间',
    KEY shop_order_detail_orderid
(
    `orderid`
),
    KEY shop_order_detail_productid
(
    `productid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单详情表';

-- 收货地址表
DROP TABLE IF EXISTS `shop_address`;
CREATE TABLE IF NOT EXISTS `shop_address`
(
    `addressid`
    INT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY
    COMMENT
    '地址id',
    `firstname`
    VARCHAR
(
    30
) NOT NULL DEFAULT '' COMMENT '姓',
    `lastname` VARCHAR
(
    30
) NOT NULL DEFAULT '' COMMENT '名',
    `company` VARCHAR
(
    100
) NOT NULL DEFAULT '' COMMENT '公司',
    `address` TEXT COMMENT '详细地址',
    `postcode` CHAR
(
    6
) NOT NULL DEFAULT '' COMMENT '邮编',
    `email` VARCHAR
(
    100
) NOT NULL DEFAULT '' COMMENT '邮箱',
    `telephone` VARCHAR
(
    20
) NOT NULL DEFAULT '' COMMENT '电话',
    `userid` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户id',
    `createtime` INT NOT NULL DEFAULT '0' COMMENT '创建时间',
    KEY shop_address_userid
(
    `userid`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '收货地址表';