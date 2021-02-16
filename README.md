# Edens Tower API
Run everything as admin in console.

# Command to run MariaDB docker
```docker run --name edens-tower-api-db -e MYSQL_ROOT_PASSWORD=ml272718 -d mariadb:latest```

# Run with network agaisnt earlier docker `
```docker run -it --network edens-tower --rm mariadb mysql -edens-tower-api-db -root -p```
# Commands for myslq-standalone
1. ```docker pull mariadb```
2. ```docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=ml272718 -e MYSQL_DATABASE=edens_tower_api -e MYSQL_USER=edadmin -e MYSQL_PASSWORD=ml272718 -d mariadb:latest```

# Commands for edens-tower-API
1. ```docker pull jtmlmass/edens-tower-api:1.0```
2. ```docker tag edens-tower-api:1.0 jtmlmass/edens-tower-api:1.0```
3. ```>docker run -p 8086:8086 --name edens-tower-api --link mysql-standalone -d jtmlmass/edens-tower-api:1.0```