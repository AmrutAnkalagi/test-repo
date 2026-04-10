def call () {
    pipeline {
    agent any
    options {
        timeout (time:1, unit:'HOURS')
    }
    triggers {
        pollSCM ('* * * * *')
    }
    stages {
        stage ('SCM') {
            steps {
                git url: 'https://github.com/nopSolutions/nopCommerce.git',
                    branch: 'develop'
            }    
        }
        stage ('Build and publish') {
            steps {
                sh 'dotnet build -c Release src/Presentation/Nop.Web/Nop.Web.csproj'
                sh 'mkdir published && dotnet publish -c Release -o published/ src/Presentation/Nop.Web/Nop.Web.csproj'
            }
        }
    }
}
}