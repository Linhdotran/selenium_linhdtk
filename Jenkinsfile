pipeline {
    agent any
    environment {
        DATE_TIME = sh(returnStdout: true, script: 'date +%Y-%m-%d-%H-%M-%S').trim()
        DATE = sh(returnStdout: true, script: 'date +%Y-%m-%d').trim()
        REPORT_URL = "/var/jenkins_home/workspace/CICDTestAPI/html/test-reports"
    }
    stages {
        stage('Clean newman report') {
            steps {
                sh 'rm -r newman'
            }
        }
        
        stage('run report') {
            steps {
                sh 'newman run TestCICD.postman_collection.json --insecure --reporters cli,htmlextra --reporter-export ./newman/report.html'
                echo "DATETIME is ${DATE_TIME}"
        }
 
    }
        
        stage('Generate Report'){
            steps {
                archive (includes: 'pkg/*.gem')
                publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: false,
                reportDir: '/var/jenkins_home/workspace//CICDTestAPI/newman/',
                reportFiles: 'report.html',
                reportName: 'Newman Report',
                reportTitles: '',
                useWrapperFileDirectly: true])
            }
        }
}
}
