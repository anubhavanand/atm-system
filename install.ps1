cd account-service
mvn clean -DskipTests install
cd ../atm-service
mvn clean -DskipTests install
cd ../service-registry
mvn clean -DskipTests install
cd ..
docker-compose up -d
