pipeline {
    agent any
    environment {
        REPO = "${HOME}/jwt"
        PID_PATH = "${REPO}/pid.nohup"
        POSTMAN_PROJECT = "${REPO}/project"
        DATE_TIME = sh(returnStdout: true, script: 'date +%Y-%m-%d-%H-%M-%S').trim()
        DATE = sh(returnStdout: true, script: 'date +%Y-%m-%d').trim()
        REPORT_URL = "/var/www/html/test-reports"
        NEWMAN_FOLDER_PATH = "${REPO}/newman"
        TEST_FILE_PATH = "${POSTMAN_PROJECT}/${TEST_FILE}.json"
        SUCCESS_FOLDER = "success"
        ERROR_FOLDER = "error"
    }
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
              stage('Running Newman collections') {

            steps {

                // additional file need additional job. this is how pipeline for
                // quite difficult to capture newman exit code by just base since "$?" did not work
                script {

                    def newmanOutPath = "${NEWMAN_FOLDER_PATH}/${params.TEST_FILE}/${DATE}/${params.TEST_FILE}_${DATE_TIME}.html"

                    def rc = sh(script: """
                        echo "Begin running newman/postman collection at path ${TEST_FILE_PATH} ..."
                        
                        mkdir -p ${NEWMAN_FOLDER_PATH}/${params.TEST_FILE}/${DATE}
                        echo 'Done generated folders ...'
                        
                        echo "Temporarily generated to ${newmanOutPath}"
                        newman run ${TEST_FILE_PATH} --reporters cli,htmlextra --reporter-htmlextra-export ${newmanOutPath}
                        sudo chmod 777 ${newmanOutPath}
                    """, returnStatus: true) as Integer
                    echo "Got exit code of test file ${rc}.."

                    // sync file to /var/www/html/test_reports
                    def copiedFolder = ''
                    if (rc == 0) {
                        copiedFolder = SUCCESS_FOLDER
                        sh """
                            mkdir -p ${REPORT_URL}/${params.TEST_FILE}/${SUCCESS_FOLDER}/${DATE}
                            sudo chmod 777 ${REPORT_URL}/${params.TEST_FILE}/${SUCCESS_FOLDER}/${DATE}
                        """
                    } else {
                        copiedFolder = ERROR_FOLDER
                        sh """
                            mkdir -p ${REPORT_URL}/${params.TEST_FILE}/${ERROR_FOLDER}/${DATE}
                            sudo chmod 777 ${REPORT_URL}/${params.TEST_FILE}/${ERROR_FOLDER}/${DATE}
                        """
                    }
                    def targetPath = "${REPORT_URL}/${params.TEST_FILE}/${copiedFolder}/${DATE}"
                    echo "Copying ${newmanOutPath} to ${targetPath}"
                    def cpRc = sh(script: "sudo cp -n ${newmanOutPath} ${targetPath}", returnStatus: true) as Integer
                    echo "Copy status ${cpRc} ..." // check if remove files are needed?

                    // on second thought, maybe using try-catch is cleaner ^_^
                    if (rc != 0) {
                        // actively throwing errors
                        echo "Emitting error because of previous (newman) exit code ${rc} ..."
                        // currentBuild.result = 'FAILURE'
                        sh "exit ${rc}"
                    }
                }
        stage('Publish report to MS team'){
             steps {
                office365ConnectorSend webhookUrl: 'https://cmcglobalcompany.webhook.office.com/webhookb2/933a24d5-19dd-461e-8c0c-e2845506945f@f89c1178-4c5d-43b5-9f3b-d21c3bec61b5/IncomingWebhook/02ada63de41c43339e804c0bc4d79158/cbb8cbce-b5c2-453d-9a74-1ed3c256c32e',
                message: 'See Newman Report here [Report](http://http://192.168.66.116:3001/job/CICDTestAPI/392/execution/node/3/ws/newman/)',
                status: 'Success',
                color: "${currentBuild.currentResult} == 'SUCCESS' ? '#0099ff' : '#ff9900'",
                  factDefinitions:[
                        [ name: "Commit Message", template: "${GIT_COMMIT_MSG}"],
                        [ name: "Pipeline Duration", template: "Maven Serenity #${currentBuild.number}"]
                    ]
            }
    }
}
}
