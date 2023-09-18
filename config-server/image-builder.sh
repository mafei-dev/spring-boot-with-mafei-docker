echo "cleaning the config-server..."
./mvnw clean
echo "packaging the config-server.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-config-server:1.0.0
echo "creating image is completed."
