rm -rf admin-ui.tar
docker rmi -f admin-ui:1.0
docker build -f Dockerfile -t admin-ui:1.0 .
docker save -o admin-ui.tar admin-ui:1.0
chmod 755 admin-ui.tar
