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
         stage('Publish report to MS team'){
             steps {
                office365ConnectorSend webhookUrl: 'https://cmcglobalcompany.webhook.office.com/webhookb2/933a24d5-19dd-461e-8c0c-e2845506945f@f89c1178-4c5d-43b5-9f3b-d21c3bec61b5/IncomingWebhook/02ada63de41c43339e804c0bc4d79158/cbb8cbce-b5c2-453d-9a74-1ed3c256c32e',
                message: 'See Report here [Report](http://192.168.66.116:3001/job/CICDTestAPI/Newman_20Report/)',
                status: 'Success',
                color: "${currentBuild.currentResult} == 'SUCCESS' ? '#0099ff' : '#ff9900'",
                factDefinitions:[
                  
                        [ name: "Pipeline Duration", template: "Maven Serenity #${currentBuild.number}"]
                    ]
            }
        }
        
}
}
