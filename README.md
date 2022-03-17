# showdb-starter  开发中...
数据库文档工具，拆箱即用，springboot版

## 如何使用？？  
如果你的项目是以SpringBoot启动，如果你的项目存在数据源（多数据源，或者数据源来自不通数据库<目前仅支持MySQL>），那么ShowDB就能根据你预设开启的功能，来对当前数据库进行文档可视化  
你可以看所有的表结构，你可以看表的详细信息，以及数据库的监控信息，配置信息等，不需要使用额外的配置，只需要引入pom，即可 拆箱即用。
maven
```xml
还在开发中...
```

yml
```yml
showdb:
  enable: true
  endpoint: structure,monitor-master-slave,monitor-table,'*'
```

jar版代码 https://github.com/Cocowwy/ShowDB


目前只支持**MySQL**
:zap: 当然这不是一个数据库的（增刪改查）连接工具！！仅仅仅仅只是一个**数据库文档工具**

jar包启动版部分效果图如下：
<img width="1257" alt="1644808963259_779C9B9B-8133-4c6d-9063-465971D8AFC6" src="https://user-images.githubusercontent.com/63331147/153794961-9543a094-2873-4332-aabb-d3f1e65541ee.png">

<img width="1262" alt="image" src="https://user-images.githubusercontent.com/63331147/153794948-c1e5e95b-eb97-4b91-b10b-550b3657e474.png">

