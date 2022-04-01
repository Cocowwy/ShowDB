# ShowDB
:zap: 数据库文档工具，你可以看到所有的表结构，表的详细信息，以及数据库的监控信息，配置信息等 :zap:  

## 如何使用？？  
如果你的项目是以SpringBoot启动，如果你的数据源（兼容多数据源，或者数据源来自不通数据库**目前仅支持MySQL,其他数据库的适配会慢慢迭代**），那么 **ShowDB** 就能根据你预开启的功能，来对数据源进行监控以及可视化

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

:zap: 这不是一个数据库的可视化工具！！仅仅仅仅只是一个**数据库文档工具，以及一些扩展增强！**


