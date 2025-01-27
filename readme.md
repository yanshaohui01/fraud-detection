# 实时欺诈检测系统
## 1. 需求分析
本服务能够满足如下需求：
> - 能够实时分析金融交易以检测潜在欺诈行为，并进行拦截、记录和告警（邮件告警）。对于识别的欺诈交易转人工处理。
> - 基于规则的检测能力，规则定义灵活可扩展满足操作员自定义规则（按照约定）的需求，保存后实时生效，应用于业务检测；
> - 对检测到的欺诈交易，进行日志记录和邮件通知。

## 2. 架构 及 性能
> - 编程框架选用SpringBoot（2.2.5.RELEASE）+ MybatisPlus 跨模块通信采用HTTP 支持RPC扩展。
> - 存储层采用 MySQL + Redis + Guava Cache 三级存储结构，按数据热度分层查询，可以有效提升接口并发；
> - 网络IO采用 HTTP和 RPC协议（后续扩展点）, 随着业务的发展和变更，支持微服务架构扩展。
> - 阿里云镜像+阿里云ACK容器化部署，Deployment、Service 和 Horizontal Pod Autoscaler（HPA））确保服务具有高可用性，及横向扩容能力。
> - 系统采用同步和异步线程池ThreadPoolTaskExecutor（@EnableAsync）相结合的方式处理任务。对于一些对实时性要求较高的任务，如实时交易检测，使用同步线程池进行处理，确保检测结果的及时反馈。
> - 基于规则的业务检测逻辑定义，采用Sping原生规则语言SpEL 作为规则定义语言。SpEL 具有强大的表达式解析能力和动态求值功能，支持关系运算（amount > 50000；country == 'North Korea'；account.contains('6200444')），支持逻辑运算（and、or关系）能够方便地定义各种复杂的欺诈检测规则对规则联合校验。
> - 阿里云 MQ 实现业务解耦，方便后续业务模块扩展。

## 3. 测试 
#### 规则定义基于Spring的SePL语法，字段说明如下：

| key | 运算符号 | 作用 | 案例 | 备注 |
| :---: | --- | --- | --- | --- |
| amount | >、= 、 <  | 规则成立禁止交易，禁止 金额 大于，等于 或小于 的交易 | amount>50000  amount=50000 amount<50000等| 目前 >、= 或 < 只能配置一种，后续可扩展使用 |
| account | .contains | 规则成立禁止交易,禁止黑名单账户交易,支持模糊匹配 | account.contains('622440014523') | 写法为 key.contains('value') |
| timestamp | 逻辑关系支持 AND/OR | 规则成立禁止交易，禁止制定时间段的交易 | Time < "2020-12-01 00:00:00" OR Time > "2025-1-27 00:00:00" | 用 AND / OR 连接 |
| country | ==  | 规则成立禁止交易，禁止指定地区的交易 | country == 'North Korea' | 字符串用单引号 |
| transaction_type | == | 规则成立禁止交易，禁止某种交易类型 | transaction_type == 'cross-border trade' | 字符串用单引号 |

目前只支持这么多指端，可以根据需要进行定义扩展。后续可以扩展成数据字典，对业务人员更加友好。


#### 测试接口文档，
[点击查看](http://116.62.57.218:8088/swagger-ui.html) 

> 规则接口

> 交易接口

> 人工审核接口

> 报表接口

## 4. 部署
git
github
阿里云镜像服务器
阿里云ACK

JDK8
数据库初始化


### 5. 扩展点






服务部署和测试说明的ReadMe 文档；
服务部署
依赖 JDK8, MySQL、Redis、Kafka 组件，
部署在阿里云ACK上
Docker容器管理 + 阿里云容器镜像
Git + GitHub（private）代码管理；

初始化 表结构；
配置JDK8依赖环境
修改代码配置：
数据库 + Kafka + Redis
Maven3.6 打包环境
Docker 打包指令：

Swagger 接口文档：
新增规则 rule
测试交易
生成日志





