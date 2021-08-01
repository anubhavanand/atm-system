cd account-service
mvn clean install
cd ../atm-service
mvn clean install
cd ../service-registry
mvn clean install
cd ..
docker-compose up -d
