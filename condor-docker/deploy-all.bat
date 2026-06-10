
docker-compose up -d mysql
docker-compose up -d kafka

call mvn -f ../pom.xml clean install

docker-compose build condor--transactions-manager
docker-compose build condor--customers-manager
docker-compose build condor--transactions-engine

docker-compose up -d condor--transactions-manager
docker-compose up -d condor--customers-manager
docker-compose up -d condor--transactions-engine

docker ps
