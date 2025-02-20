FROM apache/kafka:latest

# Replace default connector properties
COPY ./properties/connect-file-source.properties /opt/kafka/config/
COPY ./properties/connect-file-sink.properties /opt/kafka/config/
COPY ./properties/connect-standalone.properties /opt/kafka/config/

WORKDIR /opt/kafka/
CMD ["bin/connect-standalone.sh",\
"config/connect-standalone.properties",\
"config/connect-file-source.properties",\
"config/connect-file-sink.properties"]