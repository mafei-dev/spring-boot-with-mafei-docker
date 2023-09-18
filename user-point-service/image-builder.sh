echo "cleaning the user-point-service..."
./mvnw clean
echo "packaging the user-point-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-user-point-service:1.0.0
echo "creating image is completed."
