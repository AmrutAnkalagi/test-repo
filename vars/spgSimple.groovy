def call() {
    pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }
    tools {
        maven 'MAVEN_4.0.0'
    }
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage ('scm') {
            steps {
                git url: 'https://github.com/spring-projects/spring-petclinic.git',
                    branch: 'main'
            }
        }    
            stage('build') {
                steps {
                    sh 'mvn clean package'
                }
            }
        }
    }
}