#!/bin/bash

del_stopped(){
  local name=$1
  local state=$(docker inspect --format "{{.State.Running}}" $name 2>/dev/null)

  if [[ "$state" == "true" ]]; then
    docker stop $name
    docker rm $name
  fi
}

## Skysail Server
del_stopped skysail-server
#docker run --name akka-cassandra -v /tmp/docker/cassandra:/var/lib/cassandra -p 7000:7000 -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 -d cassandra:latest
docker run --name skysail-server -t --rm -p 9102:9102 evandor/skysail-server:latest
