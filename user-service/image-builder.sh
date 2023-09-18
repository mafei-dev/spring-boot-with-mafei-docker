echo "cleaning the user-service..."
./mvnw clean
echo "packaging the user-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-user-service:1.0.0
echo "creating image is completed."
