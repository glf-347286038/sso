# 基于java:8
FROM java:8
# 应用构建成功后的jar文件被复制到镜像内,名字改为sso.jar
COPY sso.jar /sso.jar
# 时区
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 启动项目
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=product","sso.jar"]