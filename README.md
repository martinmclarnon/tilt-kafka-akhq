# Kafka demo with one producer and one consumer - hosted with local Kubernetes

## Prerequisites
### Kind - https://kind.sigs.k8s.io/
```shell
$ brew install kind
```

### Create Cluster
```shell
$ kind create cluster
```
### Delete Cluster
```shell
$ kind delete cluster
```

### Tilt - https://tilt.dev/
```shell
$ brew install tilt
```
From the root folder where the TiltFile reside
```shell
$ tilt up --stream
```

To take the cluster down
```shell
$ tilt down
```

### Confirm the pods status
```shell
$ kubectl get pods
```
### URLS

Tilt GUI
```shell
http://localhost:10350
```

AKHQ GUI
```shell
http://localhost:8080
```
Producer - Environment Variable test GET Endpoint
```shell
http://localhost:8081/orders/env
```

Producer - POST Endpoint Order Payload
```shell
{
  "orderID": "123-456-785",
  "dateOfCreation" : "2023-12-25T14:01:58.874+00:00",
  "content" : "This is an Order"		
}
```
Producer - POST Endpoint Order Endpoint
```shell
http://localhost:8081/orders/
```

*NOTE: docker-compose.yaml is for local testing and is not part of the K8S set up.*


