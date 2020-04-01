# 自动图床
本项目使用 upyun 又拍云 云对象存储作为文件存储地址。
技术栈使用 SpringBoot + Redis ,
将 upyun又拍云 的登陆的参数提取出来，可以通过设置 SpringBoot 的 
application.properties 参数的方式进行简单抽象，实现了 token 方法链
，本示例需要传入redis相关参数（token的部分）

## api
1.POST       /api/files 上传文件  
2.GET        /api/files /{fileName} 查找单个文件的地址  
3.GET        /api/files  查询所有文件的文件名信息  
4.DELETE     /api/files/{fileName}  根据文件名删除文件  

## 参数（又拍云）
upyun.bucket;服务名  
upyun.url;地址  
upyun.user_name;操作员  
upyun.password;密码  
upyun.secret;密钥  
upyun.path = "/file/";默认的文件存储路径  
upyun.expire_seconds = 600;默认的防盗链过期时间  

演示：
```properties
server.port=8080
spring.redis.host=host
spring.redis.password=password
spring.redis.database=0
spring.redis.port=6379
spring.redis.jedis.pool.max-wait=-1
spring.redis.timeout=1000
# 上面仅为 application.properties 的其他参数示例
upyun.bucket_name =bucket 
# 服务名
upyun.url = url
#测试地址
upyun.user_name =admin
# 操作员
upyun.password =password
#密码
upyun.secret=secret
#密钥
upyun.path =/file/
#默认文件上传路径
upyun.expire_seconds =600
#默认防盗链过期时间
```
## 项目地址
github:
gitee:



