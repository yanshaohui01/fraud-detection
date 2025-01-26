### table structure
| 表名 | 别名 | 作用 | 备注 |  |
| --- | --- | --- | --- | --- |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |
|  |  |  |  |  |


user  系统用户表

opertion_login  操作记录表

rule  判定规则表

transactions 交易表

fraud_transaction  异常交易表 
 
audit_info  交易审核表

account  信用评分表

message  通知消息表

report_daily  日报表
 
report_weekly  周报表

请求进来，判定是否正常，正常则放行，记录日志入ELK, 有欺诈风险则入库，触发人工审核，人工审核结果单独记录。确实是风险记录则流转到另外流程，如果是误判则返回正常交易。

判定规则，利用多线程 fork-join 框架并发执行；
rule 规则载入redis中；

```
/*
数据库表结构设计：实时金融欺诈检测系统的附加功能

1. 用户表 (users)
    - id: 主键 (int, 自增)
    - username: 用户名 (varchar)
    - password: 密码 (varchar)
    - email: 邮箱 (varchar)
    - phone: 电话 (varchar)
    - status: 状态(tinyint)
    - created_at: 创建时间 (datetime)
    - updated_at: 更新时间 (datetime)

2. 交易表 (transactions)
    - id: 主键 (int, 自增)
    - user_id: 外键，关联到用户表 (int)
    - amount: 金额 (decimal)
    - transaction_type: 交易类型 (varchar)
    - timestamp: 交易时间 (datetime)
    - status: 状态 (varchar)

3. 异常交易表 (fraud_transaction)
    - id: 主键 (int, 自增)
    - transaction_id: 外键，关联到交易表 (int)
    - alert_type: 警告类型 (varchar)
    - alert_description: 警告描述 (text)
    - detected_at: 检测时间 (datetime)
*/


1. 检测规则表 (rule)
    - id: 主键 (int, 自增)
    - rule_name: 规则名称 (varchar)
    - rule_description: 规则描述 (text)
    - created_at: 创建时间 (datetime)
    - updated_at: 更新时间 (datetime)

2. 异常交易人工审核表 (audit_info)
    - id: 主键 (int, 自增)
    - transaction_id: 外键，关联到交易表 (int)
    - auditor: 审核员 (varchar)
    - audit_status: 审核状态 (varchar)
    - audit_comments: 审核备注 (text)
    - audit_time: 审核时间 (datetime)

3. 异常交易消息通知表 (message)
    - id: 主键 (int, 自增)
    - alert_id: 外键，关联到异常交易表 (int)
    - recipient: 接收人 (varchar)
    - message_content: 消息内容 (text)
    - sent_time: 发送时间 (datetime)
    - status: 发送状态 (varchar)

4. 日报统计表 (report_daily)
    - id: 主键 (int, 自增)
    - report_date: 报表日期 (date)
    - total_transactions: 总交易数 (int)
    - total_alerts: 异常警告数 (int)
    - resolved_alerts: 已解决警告数 (int)
    - unresolved_alerts: 未解决警告数 (int)
    - created_at: 创建时间 (datetime)
*/

-- MySQL 创建表语句

-- 用户表
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
	status tinyint(1),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 交易表
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    timestamp DATETIME NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (rule_id) REFERENCES rule(id)
);

-- 异常交易表
CREATE TABLE fraud_transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT NOT NULL,
    alert_type VARCHAR(50) NOT NULL,
    alert_description TEXT,
    detected_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

-- 审核员信息表
CREATE TABLE auditor(
    username VARCHAR(255) NOT NULL,
    level INT NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
	status tinyint(1),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)


-- 检测规则表
CREATE TABLE rule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL,
    rule_description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 异常交易人工审核表
CREATE TABLE audit_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT NOT NULL,
    auditor VARCHAR(255) NOT NULL,
    audit_status VARCHAR(50) NOT NULL,
    audit_comments TEXT,
    audit_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id),
	FOREIGN KEY (auditor_id) REFERENCES auditor(id)
);

-- 异常交易消息通知表
CREATE TABLE message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alert_id INT NOT NULL,
    recipient VARCHAR(255) NOT NULL,
    message_content TEXT NOT NULL,
    sent_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    FOREIGN KEY (alert_id) REFERENCES fraud_alerts(id)
);

-- 日报统计表
CREATE TABLE report_daily (
    id INT AUTO_INCREMENT PRIMARY KEY,
    report_date DATE NOT NULL,
    total_transactions INT NOT NULL,
    total_alerts INT NOT NULL,
    resolved_alerts INT NOT NULL,
    unresolved_alerts INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```