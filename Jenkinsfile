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
                office365ConnectorSend webhookUrl: 'https://cmcglobalcompany.webhook.office.com/webhookb2/d71b4385-2978-475a-849d-1f7fb4786638@f89c1178-4c5d-43b5-9f3b-d21c3bec61b5/IncomingWebhook/df5cace06cbe44b8a2db7619802ffe8b/0ef5503d-db25-40ca-bf9d-3c80b57ebfac',
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
