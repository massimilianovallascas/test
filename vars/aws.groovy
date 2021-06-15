def getCredentials() {
    stage('Build') {
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
