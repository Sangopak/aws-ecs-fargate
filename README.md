# AWS ECS Fargate

Application will upload file in S3 and should be able to run it from ECS using Fargate

## Getting Started


## Install

```
	mvn clean package
```

## Running Test

```
	TBD
```

## Running the application in local

```
	java -DawsAccessKey=<accessKey> -DawsSecretKey=<secretKey> -jar target/ecs-fargate-1.0-SNAPSHOT.jar

```
## Build docker image

```
	docker build -t ecs-fargate .
```

## Run docker image

```
	docker run -e JAVA_OPTS="-DawsAccessKey=<accessKey> -DawsSecretKey=<secretKey>" ecs-fargate
```		