pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE = 'emirhancebiroglu/devops-hw4:latest'
        REGISTRY_URL = 'https://registry-1.docker.io/v2/'
    }

    triggers {
        githubPush()
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
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials',
                                usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
                }
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
