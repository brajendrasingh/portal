rm -rf library-service.tar
docker rmi -f library-service:1.0
docker build -f Dockerfile -t library-service:1.0 .
docker save -o library-service.tar library-service:1.0
chmod 755 library-service.tar
