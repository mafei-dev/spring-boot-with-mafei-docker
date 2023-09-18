echo "cleaning the order-service..."
echo "version before."
./mvnw -version
export JAVA_HOME="/c/Program Files/Java/jdk1.8.0_202"
echo "version after."
./mvnw -version
./mvnw clean
echo "packaging the order-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-order-service:1.0.0
echo "creating image is completed."
