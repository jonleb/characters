# Amazon Elastic Constainer Service (ECS)
## Introduction
The goal is to dedploy a Container image of the project on ECS.

## Requirements
* aws account 
* awscli
* a docker image published on dockerhub

## Steps
* Create a repository on EC2: https://docs.aws.amazon.com/AmazonECR/latest/userguide/repository-create.html
* Install awscli: `brew install awscli`
* Run configuration: `aws configure`
* get login info (N. Virginia): `aws ecr get-login --region us-east-1`
* login with the result of the previous command
* built new image: `docker build -t jonleb/dicetower:0.3-SNAPSHOT .`
* https://docs.aws.amazon.com/AmazonECR/latest/userguide/docker-push-ecr-image.html
* push docker image to EC2: docker push <USER_ACCOUNT>.dkr.ecr.us-east-1.amazonaws.com/dictower

