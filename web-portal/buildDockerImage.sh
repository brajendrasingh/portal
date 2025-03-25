rm -rf web-portal-ui.tar
docker rmi -f web-portal-ui:1.0
docker build -f Dockerfile -t web-portal-ui:1.0 .
docker save -o web-portal-ui.tar web-portal-ui:1.0
chmod 755 web-portal-ui.tar
