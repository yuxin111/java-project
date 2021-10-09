
## 目的
完成一个简单、高效的自用springboot模板\
该项目前端在[这里](https://github.com/yuxin111/vue-project)

## 启动
直接导入maven依赖启动即可

## 注意
启动前要修改mode为dev，以及根据自身情况数据库地址、用户和密码，图片存放位置等
```
# demonstrate、dev
mode: demonstrate   // 演示/开发模式

spring:
  application:
    name: my-java-project
  servlet:
    multipart:
      max-file-size: 2MB
  thymeleaf:
    cache: false
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:59171/test?serverTimezone=GMT%2B8&useSSL=true
    username: root
    password: root

server:
  port: 8888
  servlet:
    context-path: '/api'
  tomcat:
    uri-encoding: UTF-8

mybatis:
  mapper-locations: classpath:mapper/*.xml

es:
  address: 127.0.0.1:9200

img:
  location: C:/Source/UploadImg // 图片存放地址
  extensions:   // 支持存放图片类型
    - .jpg
    - .png

whitelist: 119.29.197.72    // 白名单，只有在mode == demonstrate时才生效
```
