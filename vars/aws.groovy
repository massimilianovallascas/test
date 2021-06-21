def getCredentials() {
        stage('Get AWS Credentials') {
            // agent {
            //     docker {
            //         image 'python:latest'
            //         reuseNode true
            //     }
            // }
            steps {
                script {
                    sh 'python --version'

                }
            }
        }
}
