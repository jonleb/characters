# Dice Tower
Dice Tower for multiple game

##  Docker
* delete a docker image: `docker rm dicetower`
* build a new image: `docker build . -t dicetower:v1`
* run a new docker image: `docker run --name=dicetower -p 8080:8080 dicetower:v1`
* stop a docker container: `docker stop dicetower`
* start dicetower: `docker start dicetower`

## Dockerhub
* built an image: à `docker build -t jonleb/dicetower:tag .` ex: docker build jonleb/dicetower:0.3-SNAPSHOT`
* login to docker: `docker login`
* push docker image to repository: `docker push jonleb/dicetower:tag` exe:i `docker push jonleb/dicetower:0.3-SNAPSHOT`

## EC2
* Create a repository on EC2: https://docs.aws.amazon.com/AmazonECR/latest/userguide/repository-create.html
* Install awscli: `brew install awscli`
* Run configuration: `aws configure`
* get login info: `aws ecr get-login --region us-east-1`
* login with the result of the previous command
* built new image: `docker build -t jonleb/dicetower:0.3-SNAPSHOT .`
* push docker image to EC2: docker push <USER_ACCOUNT>.dkr.ecr.us-east-1.amazonaws.com/dictower

