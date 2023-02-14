pipeline {
    agent any

    stages {
        stage('Clean newman report') {
            steps {
                sh 'rm -r newman'
            }
        }
    
        stage('check newman') {
            steps {
                sh 'newman -v'
            }
        }
        
        
        stage('run report') {
            steps {
                sh 'newman run TestCICD.postman_collection.json --insecure --reporters cli,htmlextra'
        }
        
        }
    }
}
