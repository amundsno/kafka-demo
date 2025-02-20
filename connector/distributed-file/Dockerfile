FROM apache/kafka:latest

# Replace default connector properties
COPY ./properties/connect-distributed.properties /opt/kafka/config/

WORKDIR /opt/kafka/
CMD ["bin/connect-distributed.sh","config/connect-distributed.properties"]