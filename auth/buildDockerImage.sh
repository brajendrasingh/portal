rm -rf auth-service.tar
docker rmi -f auth-service:1.0
docker build -f Dockerfile -t auth-service:1.0 .
docker save -o auth-service.tar auth-service:1.0
chmod 755 auth-service.tar
