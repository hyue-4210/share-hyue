# 启动

mvn clean install
java -jar  target/sharera-1.0.jar

浏览器输入

http://localhost:8080/selectByAll

# 数据库文件
db/share.sql



# 加密

## 下载druid包 
http://central.maven.org/maven2/com/alibaba/druid/1.1.10/

## 执行命令

password是需要加密数据库的密码

```
java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools password
```

## 配置文文件

```
publicKey: 加密后的key

spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test
    username: root
    password: 加密后的密码
    filters: config
    druid:
      connection-properties: config.decrypt=true;config.decrypt.key=${publicKey}
      filter:
        config:
          enabled: true
```# share
# hyue-share
# hyue-share
