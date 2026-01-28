rm -rf question-bank-service.tar
docker rmi -f question-bank-service:1.0
docker build -f Dockerfile -t question-bank-service:1.0 .
docker save -o question-bank-service.tar question-bank-service:1.0
chmod 755 question-bank-service.tar
