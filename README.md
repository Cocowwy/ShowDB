# 📖ShowDB
:zap: 数据库文档工具，你可以看到所有的表结构，表的详细信息，以及数据库的监控信息，配置信息等 :zap:  

## 如何使用？？  
如果你的项目是以SpringBoot启动，如果你的环境已经存在数据源（或多数据源，或者数据源来自不通数据库**目前仅支持MySQL,其他数据库的适配会慢慢迭代**），那么 **ShowDB** 自动的根据开启的功能来对数据源进行监控以及可视化

- 启动你的springboot项目
- 引入ShowDB的starter，并且在配置文件中自定义配置
- 浏览器中访问该接口：```/db```  
- 即可自动生成当前项目里的数据源的文档可视化页面
![2eabd8d5b822d7ab97e2375f7f1cc37](https://user-images.githubusercontent.com/63331147/161767659-388c0cf6-f309-40ce-aad9-74fff61ccc04.jpg)


## 版本选择
|  环境   | 版本号  |
|  ----  | ----  |
| JDK | 1.8+ |
| SpringBoot | 2.0+ |

**maven**
```xml
还在开发中...
```

**yml配置**
```yml
showdb:
  enable: true
  endpoint: structure,monitor-master-slave,monitor-table,'*'  
```
  
**endpoint可选值如下：**
|  endpoint   | 说明  |
|  ----  | ----  |
| * | 所有 |
| structure  | 表结构 |
| monitor-master-slave  | 主从监控 |
| monitor-table  | 表监控 |
| monitor-database  | 数据库监控 |  

**当前支持数据源如下：**
|  数据源   | 是否支持  |
|  ----  | ----  |
| MySQL | 支持 |

  
**包模块说明：**
|  包名   | 说明  |
|  ----  | ----  |
| showdb-core | 核心逻辑 |
| showdb-spring-boot-autoconfigure | 自动配置 |
| showdb-spring-boot-starter | 引入starter |
| showdb-test | 测试，内含多数据源demo，测试sql等 |
| showdb-web | web端 |

:zap: 这不是一个数据库的可视化工具！！仅仅仅仅只是一个**数据库文档工具，以及一些扩展增强！**


