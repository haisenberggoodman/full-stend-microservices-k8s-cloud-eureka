## Full guide: 
https://tproger.ru/articles/podnimaem-full-spring-stend-mikroservisov-iz-monorepozitoriya-v-kubernetes/

Digital marketplace


## Install infrastructure

```
# Set up 1 node k8s

> minikube start --memory=6144

# Go to environment folder

> cd k8s

# install evnironment settings

> kubectl apply -f cluster-namespace.yaml

# install components

> kubectl apply -f kafka/zookeeper-cluster.yaml
> kubectl apply -f kafka/kafka-broker.yaml

For debug use:
> kubectl port-forward kafka-0 9092:9092 -n library-platform

# install services
> kubectl apply -f services/config-service.yaml
> kubectl apply -f services/discovery-service.yaml
> kubectl apply -f services/gateway-service.yaml
> kubectl apply -f services/order-service.yaml
> kubectl apply -f services/payment-service.yaml
> kubectl apply -f services/product-service.yaml
> kubectl apply -f services/profile-service.yaml
```

## Links for materials:
- https://tproger.ru/articles/podnimaem-stend-spring-mikroservisov-v-kubernetes

