FROM mariadb:10.6
COPY custom.cnf /etc/mysql/conf.d
COPY createDB.sql /docker-entrypoint-initdb.d/
