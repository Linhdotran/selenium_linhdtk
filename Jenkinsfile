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
    }
}
