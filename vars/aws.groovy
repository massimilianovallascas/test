def getCredentials() {
    stage('Get AWS Credentials') {
        agent {
            docker {
                image 'python:latest'
                reuseNode true
            }
        }
        steps {
            sh 'python --version'
        }
    }
}
