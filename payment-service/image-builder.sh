echo "cleaning the payment-service..."
echo "version before."
./mvnw -version
export JAVA_HOME="/c/Program Files/Java/jdk-17"
echo "version after."
./mvnw -version
./mvnw clean
echo "packaging the payment-service.."
./mvnw package -DskipTests
echo "creating image...."
docker build . --tag mafeidev/sbm-payment-service:1.0.0
echo "creating image is completed."
