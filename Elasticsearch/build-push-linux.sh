mirror="10.211.55.5/test-demo"
tag="1.0.1"


echo 开始构建镜像...

docker build  -f ./Dockerfile -t $mirror:$tag .
docker login 10.211.55.5 --password="yt070266" --username="root"

echo 推送中...
docker push $mirror:$tag
echo "完成打包：$mirror:$tag"