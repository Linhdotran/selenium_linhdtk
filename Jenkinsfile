pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
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
