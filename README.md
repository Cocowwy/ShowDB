# 📖ShowDB   
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![JDK](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/java/technologies/downloads)
[![Stars](https://img.shields.io/github/stars/Cocowwy/ShowDB.svg?style=social)](https://github.com/Cocowwy/ShowDB)

## ✋Features
- 1 **拆箱即用**，引入pom文件，即可拆箱即用
- 2 **UI界面丰富**
- 3 **文档可视化**，能对数据库表结构一览无余，支持模糊搜索
- 4 **支持多数据源**，自动对环境中存在的数据源进行监控与文档化
- 5 支持**数据库表结构SQL迁移，数据库表结构文档下载**
- 6 支持表**详细信息查询（如数据量大小，索引大小，存储引擎等），表的建表SQL，Java实体类代码生成**等
- 7 支持监控数据源IP连接数，事务明细，数据库配置信息查询等查询
- 8 **支持 MyBatis 代码的生成（可定制化）**

## 🐰简介
:zap: 拆箱即用的数据库文档工具，自动将SpringBoot项目中的所有数据源文档可视化，同时监控数据库相关的信息，如主从状态，配置等  
:zap: 你可以将其直接引入你的SpringBoot工程，来直接展示当前项目的数据源，也可以参考 [showdb-test](https://github.com/Cocowwy/ShowDB/tree/v1-dev/showdb-test) 工程，对所有需要展示的数据源进行统一管理，形成一个公司内部的统一的文档  

## 🍺为啥开发这个？ 
- 在使用 **screw** 的时候，需要配置数据源的连接信息，然后才会生成一个基于当前数据源的表结构文档🤪     

- 但是这个文档它是不可变的，且每次数据库修改了表结构，这个文档就得重新生成，何况，如果是多数据源，难道就需要生成多个文档嘛💐，那么这就**太麻烦了**，在实际开发过程中，迭代快的情况下往往就需要经常重新生成文档，这对于懒人来说是件很麻烦的事情    

- 那么咱就大漏特漏😵，现如今微服务基本都会连接上数据源🍎，所以如果能够对**SpringBoot项目中已经存在的数据源/多数据源，自动生成文档，那么会更迎合Javaer/Springer的使用习惯，就像swagger一样，拆箱即用，且支持用户的一些个性化的自定义配置🎠，同时也可以对数据源进行一些信息的监控，如比较关心的主从状态，客户端连接，一些数据库的配置信息等等，这些也都能集成进来，使其更符合ShowDB的名字** 🐥    

## 🧰如何使用？？  
- 在SpringBoot项目的pom.xml文件中引入依赖  

**稳定版☕** [地址戳这儿](https://search.maven.org/artifact/cn.cocowwy/showdb-spring-boot-starter)
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
```
**如果要食用最新的快照版本（还在测试使用的功能），使用如下依赖🍔**  
**注意，快照版的使用，需要带上下面的参数，因为默认是不会走 snapshots 的**
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.1.0-SNAPSHOT</version>
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

 **yml配置🔧**
```yml
showdb:
  enable: true
  refresh: 500
  customize:
    top-alert: 数据源文档工具 V1.0.1
    creator: Cocowwy
    img: https://avatars.githubusercontent.com/u/63331147?s=400&u=d12524532cc111c9ec069f1aa754e702289a587c&v=4
    email: 514658459@qq.com
    desc: 数据源可视化工具ShowDB，自动监控SpringBoot项目已存在数据源，自动生成数据库文档，MyBatis代码，及数据源监控信息等
  plugin:
    generate: true
```

- 项目中已经配置好单数据源（多数据源看下面的例子）
- 浏览器中访问该路径：```/db```，即可自动生成当前项目里的所有数据源文档页面
- 如果你的项目里面设置：```server.servlet.context-path```的配置为```xxx```，那么showdb的访问页面为：```/xxx/db```  
- ShowDB的内部接口前缀为：```/showdb```，**所以如果项目对请求路径做了限制，需要手动放开面带有```db```和```showdb```的接口的限制**


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
![image](https://user-images.githubusercontent.com/63331147/197142803-5ee74156-5a60-4c4d-8930-425e64ed9d1f.png)
![image](https://user-images.githubusercontent.com/63331147/197143324-47baaf83-f4a5-4517-8053-933a74f08846.png)
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
| monitor-performance  | 性能监控，事务，锁 |


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

## TODO 待开发的功能..
- 增加对于数据库更多比较需要关心的参数进行可视化
- 敏感触点做预警通知  
- 适配多种不同类型的数据库
- 提供更多监控性的指标
- UI界面的更加美观（这也是最难的） 
- 生成MyBtis相关文件

## 微信公众号～  
<img src="https://user-images.githubusercontent.com/63331147/192079044-208ee2f1-381d-49b3-b60e-451350197f95.png" width="200" height="200" alt="公众号"/><br/>

## END🍕
如果你有更好的意见，欢迎关注我公众号给我留言~
如果你有更好的想法或者想添加，修复bug，欢迎PR~

