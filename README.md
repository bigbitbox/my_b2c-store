### 项目一：B2C电商购物平台

+ **主要技术栈** SpringCloud、SpringBoot、Redis、ElasticSearch、MySql

+ **项目描述** 本次项目用于实现顾客商城购物功能，顾客可以对对商品进行分类选择和搜索，商城包括顾客的购物车、收藏、订单功能。商城管理员可以登录商城后台进行对商品、账号、订单进行统一的管理。

+ **项目角色**：此项目中负责Java后端开发，提供后端接口及对应文档供前台使用。

+ **项目总结** 本项目后端使用**SpringCloud**开发，使用**Mysql**作为数据库。各个微服务使用Feign调用功能，编辑了**清晰的接口文档**为前端提供API接口，注册中心使用Nacos对微服务统一配置管理。采用 **Redis** 实现了 **高频信息缓存** ，加快了 **请求响应速度** ，降低了 **50%** 以上的数据库压力。使用 **Elasticsearch** 实现 **商品内容搜索** ，相比 Mysql搜索效率有较大的提升。利用 **RabbitMQ** 的队列机制进行 **异步请求** ，缓解了 **高并发** 情况下的 **系统资源短缺** 问题。使用springAOP进行**登录拦截**，为配合 **集群化部署** ，前后端均以 **Docker** 作为容器，可在任一陌生机器 **迅速启动应用**，弹性部署。