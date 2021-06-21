def getCredentials() {
        // stage('Get AWS Credentials') {
        //     // agent {
        //     //     docker {
        //     //         image 'python:latest'
        //     //         reuseNode true
        //     //     }
        //     // }
        //     steps {
        //         script {
        //             sh 'python --version'

        //         }
        //     }
        // }
        docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw" -p 3306:3306') { c ->
            /* Wait until mysql service is up */
            sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
            /* Run some tests which require MySQL */
            sh 'make check'
        }
}
