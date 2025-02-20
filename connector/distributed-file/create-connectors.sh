# Change to script directory to use relative paths
cd $(dirname $0)

# With replicas, ports are distributed automatically by Docker
container_port=$(docker ps --format '{{.Names}}{{.Ports}}' | grep connect-distributed | head -n 1 | cut -d : -f 2 | cut -d - -f 1)

# Check if the connector is ready
curl http://localhost:${container_port}/connectors | jq

# Add source connector
curl -d "@./properties/connect-file-source.json" --header "Content-Type: application/json" http://localhost:${container_port}/connectors | jq

# Add sink connector
curl -d "@./properties/connect-file-sink.json" --header "Content-Type: application/json" http://localhost:${container_port}/connectors | jq