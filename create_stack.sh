kubectl delete configmap opa-policy
kubectl delete -f deployment/
./gradlew clean bootJar
docker build -t opa-demo .
kubectl create configmap opa-policy --from-file=opa-policy
kubectl apply -f deployment/

