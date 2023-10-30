# Deploy ambiente di sviluppo

## 0. build e push immmagine
docker image build -t registry-implemedev.almaviva.it/impleme/anagrafe-tributi[:TAG]
docker push registry-implemedev.almaviva.it/impleme/anagrafe-tributi[:TAG]

## 1. deploy config map
kubectl --insecure-skip-tls-verify  create configmap tributi-configmap --from-file=application.yml  -n impleme-bolite

## 2. deploy file deployment
kubectl --insecure-skip-tls-verify  create -f tributi-deployment.yaml

## 3. deploy file service
kubectl --insecure-skip-tls-verify  create -f tributi-service.yaml
