mirror="10.211.55.10/test-demo"
tag="1.0.1"


echo 开始构建镜像...

docker build  -f ./Dockerfile -t $mirror:$tag .
docker login 10.211.55.10 --password="yangte" --username="root"

echo 推送中...
docker push $mirror:$tag
echo "完成打包：$mirror:$tag"