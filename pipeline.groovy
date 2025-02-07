pipeline {
    agent any 
    stages {
        stage ('pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/mayurmwagh/CDEC-B21-Backend.git'
            }
        }
        stage ('build'){
            steps {
                sh 'mvn clean package'
            }
        }
        stage ('deploy'){
            steps {
                sh '''
                    docker build -t mayurwagh/CDEC-B21-Backend:latest .
                    docker push mayurwagh/CDEC-B21-Backend:latest
                    docker rmi mayurwagh/CDEC-B21-Backend:latest
                    kubectl apply -f ./yaml/
                '''
            }
        }
    }
}