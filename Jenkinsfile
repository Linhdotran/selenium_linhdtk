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
        stage('Publish report to MS team'){
             steps {
                office365ConnectorSend webhookUrl: 'https://cmcglobalcompany.webhook.office.com/webhookb2/933a24d5-19dd-461e-8c0c-e2845506945f@f89c1178-4c5d-43b5-9f3b-d21c3bec61b5/IncomingWebhook/02ada63de41c43339e804c0bc4d79158/cbb8cbce-b5c2-453d-9a74-1ed3c256c32e',
                message: 'See Newman Report here [Report](http://http://192.168.66.116:3001/job/CICDTestAPI/392/execution/node/3/ws/newman/)',
                status: 'Success',
                color: "${currentBuild.currentResult} == 'SUCCESS' ? '#0099ff' : '#ff9900'"
            }
    }
}
}
