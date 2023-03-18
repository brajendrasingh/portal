rm -rf enrollment-service.tar
docker rmi -f enrollment-service:1.0
docker build -f Dockerfile -t enrollment-service:1.0 .
docker save -o career-service.tar enrollment-service:1.0
chmod 755 enrollment-service.tar
