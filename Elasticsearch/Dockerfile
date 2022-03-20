FROM java:8
ENV TIME_ZONE Asia/Shanghai
ENV  LANG C.UTF-8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

RUN mkdir -p /usr/local/jar

WORKDIR  /usr/local/jar
#将当前kmp-indicator.jar 复制到容器根目录下
ADD ./target/demo-0.0.1-SNAPSHOT.jar  /usr/local/jar
#将依赖包 复制到容器根目录/libs下,kmp-indicator.jar已不再需要添加其它jar包
#ADD libs /libs
#暴露容器端口为9202 Docker镜像告知Docker宿主机应用监听了9202端口
EXPOSE 1234
#容器启动时执行的命令
CMD ["java", "-jar", "-Xmx1024m","-Xms1024m","./app.jar" , "--spring.profiles.active=dev"]