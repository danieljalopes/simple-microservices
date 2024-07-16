### Minikube
#### Start Minikube
```bash
minikube start
```
#### Open dashboard
```bash
minikube dashboard
```

#### Open connection to Postgres NOdeport
```bash
minikube dashboard
```
#### Volumes in minikube
minikube provides a folder that can be used for volume creation `/data/', just create sub-folders in it
```bash
minikube ssh
su mkdir /data/<my folder>
```
### Kubectl
#### Apply everything
```bash
kubectl apply -f .\
```
#### Apply a new manifest
```bash
kubectl apply -f .\<file>.yaml
```
#### Replace a manifest
```bash
kubectl apply -f .\<file>.yaml
```
### How to


#### Connect to Postgres database from outside Minikube
1. Open a new terminal
2. Execute `minikube service postgres-np --url`. 
    - this will give an address
    - never close this window
3. use the given address for your database app

#### Connect to Consumer microservice from outside Minikube
1. Open a new terminal
2. Execute `minikube service consumer-np --url`. 
    - this will give an address
    - never close this window
3. use the given address for your database app