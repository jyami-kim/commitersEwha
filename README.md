```shell script
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=commiters -e MYSQL_USER=jyami -e MYSQL_PASSWORD=commiters --name commiters mysql
docker exec -i -t mysql_test bash
mysql -u root -p
```
