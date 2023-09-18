echo "cleaning the api-gateway..."
./mvnw clean
echo "packaging the api-gateway.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-api-gateway:1.0.0
echo "creating image is completed."
