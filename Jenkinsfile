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
                sh 'docker image build -t firstbuild/demo .'
            }
        }
        stage ('Containerization') {
            steps {
                sh 'docker run -it -d --name hello -p 9008:8080 firstbuild/demo'
            }
        }
        stage ('Login to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DEFAULT_DOCKER_HUB', passwordVariable: 'DOCKERHUB-PASSWORD', usernameVariable: 'DOCKERHUB-USERNAME')]) {
    // some block
                        sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
                    }
                }

            }
        }
        stage ('Push To DockerHub') {
            steps {
                sh 'docker push firstbuild/demo'
            }
        }
    }
}