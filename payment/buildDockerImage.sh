rm -rf payment-service.tar
docker rmi -f payment-service:1.0
docker build -f Dockerfile -t payment-service:1.0 .
docker save -o payment-service.tar payment-service:1.0
chmod 755 payment-service.tar
