pipeline {
    agent any
    tools {
        maven 'MAVEN_3.9.14'
    }
    options {
        timeout (time: 1, unit: 'HOURS')
    }
    triggers {
        pollSCM ('* * * * *')
    }
    stages {
        stage ('Git Checkout') {
            steps {
                git url: 'https://github.com/AmrutAnkalagi/test-repo.git',
                    branch: 'main'
            }
        }
        stage ('Docker Build') {
            steps {
                sh 'docker image build -t amrutankalagi19/ci_push_repo .'
            }
        }
        stage ('Containerization') {
            steps {
                sh 'docker run -it -d -P amrutankalagi19/ci_push_repo'
            }
        }
        stage ('Login to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
                    }
                }
              

            }
        }
        stage ('Push To DockerHub') {
            steps {
                sh 'docker push amrutankalagi19/ci_push_repo:latest'
            }
        }
    }
}