# 📖ShowDB
:zap: 拆箱即用的数据库文档工具，自动将SpringBoot项目中的所有数据源文档可视化，同时监控数据库相关的信息，如主从状态，配置等 :zap:  

## ✋Features
- 1 拆箱即用，引入pom文件，即可使用
- 2 UI界面丰富
- 3 文档可视化，能对数据库表结构一览无余，支持模糊搜索
- 4 支持多数据源，自动对环境中存在的数据源进行监控与文档化
- 5 支持数据库表结构SQL迁移，数据库表结构文档下载
- 6 支持数据库主从信息实时监控，数据库配置信息查询，数据库连接信息展示
- 7 支持表详细信息查询，建表SQL查询，Java实体类代码生成等


## 🧰如何使用？？  
- 在SpringBoot项目的pom.xml文件中引入依赖  
**最新快照版本**
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
- **稳定版（稳定版还未上，目前可🍔食用快照版）**
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>showdb-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 项目中已经配置好数据源/多数据源
- 浏览器中访问该路径：```/db```，即可自动生成当前项目里的所有数据源文档页面

![73849dd9319e33d555677d1acf6745f](https://user-images.githubusercontent.com/63331147/161917411-9808b386-590d-409a-b2dd-196f27f40ff6.jpg)
**上图的Demo中，该项目存在三个数据源，同时其中一个数据源开启了主从，ShowDB对当前项目中所有的数据源进行了统一的文档管理，以及数据源信息监控**


## 🏷️版本选择
|  环境   | 版本号  |
|  ----  | ----  |
| JDK | 1.8+ |
| SpringBoot | 2.3.9.RELEASE+ |

## 🔧**yml配置**
```yml
showdb:
  enable: true
  endpoint: monitor-master-slave,monitor-ip-connection,'*'  
```
  
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
增加对于MySQL数据库 长事务信息查询
敏感信息做预警通知

