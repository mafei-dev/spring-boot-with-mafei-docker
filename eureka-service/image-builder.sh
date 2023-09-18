echo "cleaning the eureka-service..."
./mvnw clean
echo "packaging the eureka-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-eureka-service:1.0.0
echo "creating image is completed."
