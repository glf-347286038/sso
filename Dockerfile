# 基于java镜像创建新镜像
FROM java:8
# 作者
MAINTAINER golf
# 挂载
VOLUME /home/app
# 应用构建成功后的jar文件被复制到镜像内,名字也改为sso.jar
ADD sso-0.0.1-SNAPSHOT.jar sso.jar
# 运行jar包
RUN bash -c 'touch /sso.jar'
# 执行命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=product","/sso.jar"]
