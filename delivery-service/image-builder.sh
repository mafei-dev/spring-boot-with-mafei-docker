echo "cleaning the delivery-service..."
echo "version before."
./mvnw -version
export JAVA_HOME="/c/Program Files/Java/jdk-17"
echo "version after."
./mvnw -version
./mvnw clean
echo "packaging the delivery-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-delivery-service:1.0.0
echo "creating image is completed."
