# 📖ShowDB
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![JDK](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/java/technologies/downloads)
[![Stars](https://img.shields.io/github/stars/Cocowwy/ShowDB.svg?style=social)](https://github.com/Cocowwy/ShowDB)


## 🐰简介
:zap: 拆箱即用的数据库文档工具，自动将SpringBoot项目中的所有数据源文档可视化，同时监控数据库相关的信息，如主从状态，配置等  
:zap: 你可以将其直接引入你的SpringBoot工程，来直接展示当前项目的数据源，也可以参考 [showdb-test](https://github.com/Cocowwy/ShowDB/tree/v1-dev/showdb-test) 工程，对所有需要展示的数据源进行统一管理，形成一个公司内部的统一的文档

## 🍺为啥开发这个？
- 在使用 **screw** 的时候，需要配置数据源的连接信息，然后才会生成一个基于当前数据源的表结构文档🤪

- 但是这个文档它是不可变的，且每次数据库修改了表结构，这个文档就得重新生成，何况，如果是多数据源，难道就需要生成多个文档嘛💐，那么这就**太麻烦了**，在实际开发过程中，迭代快的情况下往往就需要经常重新生成文档，这对于懒人来说是件很麻烦的事情

- 那么咱就大漏特漏😵，现如今微服务基本都会连接上数据源🍎，所以如果能够对**SpringBoot项目中已经存在的数据源/多数据源，自动生成文档，那么会更迎合Javaer/Springer的使用习惯，就像swagger一样，拆箱即用，且支持用户的一些个性化的自定义配置🎠，同时也可以对数据源进行一些信息的监控，如比较关心的主从状态，客户端连接，一些数据库的配置信息等等，这些也都能集成进来，使其更符合ShowDB的名字** 🐥

## ✋Features
- 1 **拆箱即用**，引入pom文件，即可使用
- 2 **UI界面丰富**
- 3 **文档可视化**，能对数据库表结构一览无余，支持模糊搜索
- 4 **支持多数据源**，自动对环境中存在的数据源进行监控与文档化
- 5 支持**数据库表结构SQL迁移，数据库表结构文档下载**
- 6 支持数据库**主从信息实时监控，数据库配置信息查询，数据库连接信息展示**
- 7 支持表**详细信息查询（如数据量大小，索引大小，存储引擎等），表的建表SQL，Java实体类代码生成**等

## 🧰如何使用？？
- 在SpringBoot项目的pom.xml文件中引入依赖

**稳定版☕** [地址戳这儿](https://search.maven.org/artifact/cn.cocowwy/showdb-spring-boot-starter)
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
**如果要食用最新的快照版本（还在测试使用的功能），使用如下依赖🍔**  
**注意，快照版的使用，需要带上下面的参数，因为默认是不会走 snapshots 的**
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
<repositories>
    <repository>
        <id>showdb-snapshots</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
``` 
当前快照版较稳定版新增功能：
- 自定义缓存清理时长
- 部分JS代码由CDN迁移至内部代码，以提供稳定的前端脚本
- 修复了一些已知问题
- 新增事务查询

**yml配置🔧**
```yml
showdb:
  enable: true
  endpoint: '*'  
```

- 项目中已经配置好单数据源（多数据源看下面的例子）
- 浏览器中访问该路径：```/db```，即可自动生成当前项目里的所有数据源文档页面

**如果是多数据源如何接入？**
- 如何配置呢，可以参考 ```showdb-test``` 这个moudle，或者参考下面这块代码（这是本人的例子，多数据源的注入并不一定这么写，环境里只要存在多数据源，就会自动给你配置好）

**yml**
```yml
spring:
  application:
    name: ShowDB-TEST
  datasource:
    cms:
      type: com.alibaba.druid.pool.DruidDataSource
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://IP:3306/cms?&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai
      username: 
      password: 
    oms:
      type: com.alibaba.druid.pool.DruidDataSource
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://IP:3306/oms?&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai
      username: 
      password: 
    pms:
      type: com.alibaba.druid.pool.DruidDataSource
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://IP:3306/pms?&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai
      username: 
      password: 
``` 

**数据源注入**
```java
    /**
 * 数据源1配置
 */
@Bean(name = "cms", destroyMethod = "close", initMethod = "init")
@ConfigurationProperties(prefix = "spring.datasource.cms")
@Primary
public DruidDataSource createDataSource1() {
        return DruidDataSourceBuilder.create().build();
        }

/**
 * 数据源2配置
 */
@Bean(name = "oms", destroyMethod = "close", initMethod = "init")
@ConfigurationProperties(prefix = "spring.datasource.oms")
public DruidDataSource createDataSource2() {
        return DruidDataSourceBuilder.create().build();
        }

/**
 * 数据源3配置
 */
@Bean(name = "pms", destroyMethod = "close", initMethod = "init")
@ConfigurationProperties(prefix = "spring.datasource.pms")
public DruidDataSource createDataSource3() {
        return DruidDataSourceBuilder.create().build();
        }
```
![73849dd9319e33d555677d1acf6745f](https://user-images.githubusercontent.com/63331147/161917411-9808b386-590d-409a-b2dd-196f27f40ff6.jpg)
- **上图的Demo中，该项目存在三个数据源，同时其中一个数据源开启了主从，ShowDB对当前项目中所有的数据源进行了统一的文档管理，以及数据源信息监控**
- **ps：如果需要一份多业务库的综合的文档，你可以单独启动一个SpringBoot项目，然后将所有的业务库都注入进这个服务，这样能当做一个唯一的入口来做统一的业务数据库文档**

## 🏷️版本选择
|  环境   | 版本号  |
|  ----  | ----  |
| JDK | 1.8+ |
| SpringBoot | 2.3.9.RELEASE+ |


## 🔧**endpoint可选值如下：**
|  endpoint   | 说明  |
|  ----  | ----  |
| * | 所有 |
| monitor-master-slave  | 主从信息监控 |
| monitor-ip-connection  | IP连接监控 |

## 📄**当前支持数据源如下：**
|  数据源   | 是否支持  |
|  ----  | ----  |
| MySQL | 支持 |

## 📦**包模块说明：**
|  包名   | 说明  |
|  ----  | ----  |
| showdb-core | SQL核心执行逻辑，内部缓存等 |
| showdb-spring-boot-autoconfigure | ShowDB的自动配置 |
| showdb-spring-boot-starter | 引入starter |
| showdb-test | 测试模块，内含多数据源demo，测试sql等，quickstart 戳这儿 |
| showdb-web | web模块，视图和业务处理 |

## 待开发的功能..
- 增加对于数据库更多比较需要关心的参数进行可视化
- 增加对于MySQL数据库长事务信息查询
- 敏感触点做预警通知
- 适配多种不同类型的数据库
- UI界面的更加美观（这也是最难的）

## 🍕END
欢迎提 issue 和 pr ~

## 🥖**分支说明：**
|  包名   | 说明  |
|  ----  | ----  |
| master | 当前稳定版分支 |
| RELEASE-X | X为稳定版的版本号 |
| v1-dev | v1版本开发分支，接受PR |

