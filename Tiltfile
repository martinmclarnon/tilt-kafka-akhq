# Tiltfile

# def pvc_service():
#     k8s_yaml('k8s/zookeeper-data-pvc.yaml')
#     k8s_yaml('k8s/zookeeper-data-pv.yaml')
#     k8s_yaml('k8s/zookeeper-log-pvc.yaml')
#     k8s_yaml('k8s/zookeeper-log-pv.yaml')
#     k8s_yaml('k8s/kafka-pvc.yaml')
#     k8s_yaml('k8s/kafka-pv.yaml')
#     k8s_yaml('k8s/kafka-network.yaml')

# pvc_service()

# Function to build and deploy: zookeeper service
def zookeeper_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/zookeeper-service.yaml')
    k8s_yaml('k8s/zookeeper-deployment.yaml')
    # Assign port
    k8s_resource('zookeeper-deployment', port_forwards=2181)

zookeeper_service()

# Function to build and deploy: kafka service
def kafka_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/kafka-service.yaml')
    k8s_yaml('k8s/kafka-deployment.yaml')
    # Assign port
    k8s_resource('kafka-deployment', port_forwards=9092)

kafka_service()

# Function to build and deploy: akhq service
def akhq_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/akhq-service.yaml')
    k8s_yaml('k8s/akhq-deployment.yaml')
    # Assign port
    k8s_resource('akhq-deployment', port_forwards=8080)

akhq_service()

# Function to build and deploy: producer service
def producer_service():
    # Build from Dockerfile
    docker_build('producer', context='producer', dockerfile='./producer/Dockerfile')
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/producer-service.yaml')
    k8s_yaml('k8s/producer-deployment.yaml')
    # Assign port
    k8s_resource('producer-deployment', port_forwards='8081:8080')

producer_service()

# Function to build and deploy: consumer service
def consumer_service():
     # Build from Dockerfile
     docker_build('consumer', context='consumer', dockerfile='./consumer/Dockerfile')
     # Specify the Kubernetes manifest for the deployment
     k8s_yaml('k8s/consumer-service.yaml')
     k8s_yaml('k8s/consumer-deployment.yaml')
     # Assign port
     k8s_resource('consumer-deployment', port_forwards=8082)

consumer_service()