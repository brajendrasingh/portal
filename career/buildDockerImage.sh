rm -rf career-service.tar
docker rmi -f career-service:1.0
docker build -f Dockerfile -t career-service:1.0 .
docker save -o career-service.tar career-service:1.0
chmod 755 career-service.tar
