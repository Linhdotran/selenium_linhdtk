pipeline {
    agent any
    environment {
        REPO = "${HOME}/workspace/CICDTestAPI"
        PID_PATH = "${REPO}/pid.nohup"
        POSTMAN_PROJECT = "${REPO}/project"
        DATE_TIME = sh(returnStdout: true, script: 'date +%Y-%m-%d-%H-%M-%S').trim()
        DATE = sh(returnStdout: true, script: 'date +%Y-%m-%d').trim()
        REPORT_URL = "/var/jenkins_home/workspace/CICDTestAPI/html/test-reports"
        NEWMAN_FOLDER_PATH = "${REPO}/newman"
        TEST_FILE_PATH = "TestCICD.postman_collection.json"
        SUCCESS_FOLDER = "success"
        ERROR_FOLDER = "error"
    }
    stages {
        stage('Clean newman report') {
            steps {
                sh 'rm -r newman'
            }
        }
        
        stage('run report') {
            steps {
                sh 'newman run TestCICD.postman_collection.json --insecure --reporters cli,htmlextra'
                echo "DATETIME is ${DATE_TIME}"
        }
 
    }
}
}
