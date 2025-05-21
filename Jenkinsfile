pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Jenkins > Credentials'dan alınmalı
        DOCKER_IMAGE = 'emirhancebiroglu/devops-hw4:latest'
    }

    triggers {
        githubPush() // triggerd push and merge
    }

    stages {
        stage('Clone repository') {
            steps {
                git url: 'https://github.com/emirhancebiroglu/devops-hw4.git', branch: 'master'
            }
        }

        stage('Build JAR with Gradle') {
            steps {
                bat 'gradlew.bat clean build'
            }
        }

        stage('Build Docker image') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin'
            }
        }

        stage('Push Docker image') {
            steps {
                bat 'docker push %DOCKER_IMAGE%'
            }
        }

        stage('Apply Kubernetes Deployment') {
            steps {
                bat 'kubectl apply -f deployment.yaml'
            }
        }

        stage('Apply Kubernetes Service') {
            steps {
                bat 'kubectl apply -f service.yaml'
            }
        }
    }
}
