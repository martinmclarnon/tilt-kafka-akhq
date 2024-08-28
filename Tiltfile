 # Tiltfile

# Function to build and deploy: zookeeper service
def zookeeper_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/demo-zookeeper.yaml')
    # Assign port
    k8s_resource('demo-zookeeper',
                 port_forwards=2181,
                 labels="message-broker")

# Function to build and deploy: kafka service
def kafka_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/demo-kafka.yaml')
    # Assign port
    k8s_resource('demo-kafka',
                 port_forwards=9092,
                 resource_deps=['demo-zookeeper'],
                 labels="message-broker")

# Function to build and deploy: akhq service
def akhq_service():
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/demo-akhq.yaml')
    # Assign port
    k8s_resource('demo-akhq',
                 port_forwards=8080,
                 resource_deps=['demo-kafka'],
                 labels="message-broker")


# Function to build and deploy: producer service
def producer_service():
    # Build from Dockerfile
    docker_build('producer', context='producer', dockerfile='./producer/Dockerfile')
    # Specify the Kubernetes manifest for the deployment
    k8s_yaml('k8s/demo-producer.yaml')
    # Assign port
    k8s_resource('demo-producer',
                 port_forwards='8081:8080',
                 resource_deps=['demo-kafka'],
                 trigger_mode=TRIGGER_MODE_MANUAL,
                 labels="service")

# Function to build and deploy: consumer service
def consumer_service():
     # Build from Dockerfile
     docker_build('consumer', context='consumer', dockerfile='./consumer/Dockerfile')
     # Specify the Kubernetes manifest for the deployment
     k8s_yaml('k8s/demo-consumer.yaml')
     # Assign port
     k8s_resource('demo-consumer',
                  port_forwards='8082:8080',
                  resource_deps=['demo-kafka'],
                  trigger_mode=TRIGGER_MODE_MANUAL,
                  labels="service")

zookeeper_service()
kafka_service()
akhq_service()
producer_service()
consumer_service()



