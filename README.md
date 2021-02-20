# Edens Tower API
Run everything as admin in console.

# Command to run MariaDB docker
```docker run --name edens-tower-api-db -e MYSQL_ROOT_PASSWORD=ml272718 -d mariadb:latest```

# Run with network agaisnt earlier docker `
```docker run -it --network edens-tower --rm mariadb mysql -edens-tower-api-db -root -p```
# Commands for myslq-standalone
1. ```docker pull mariadb```
2. ```docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=ml272718 -e MYSQL_DATABASE=edens_tower_api -e MYSQL_USER=edadmin -e MYSQL_PASSWORD=ml272718 -d mariadb:latest```

# Commands for edens-tower-api
0. ```docker build -t jtmlmass/edens-tower-api:tag .```
1. ```docker push jtmlmass/edens-tower-api:tag```
2. ```docker pull jtmlmass/edens-tower-api:tag```
3. ```>docker run -p 8086:8086 --name edens-tower-api --link mysql-standalone -d jtmlmass/edens-tower-api:tag```

# For new version of edens-tower-api
1. ```docker tag edens-tower-api:1.0 jtmlmass/edens-tower-api:1.0```

# Docker sheet Cheat reference
- [Docker Cheat Sheet](https://www.docker.com/sites/default/files/d8/2019-09/docker-cheat-sheet.pdf)
