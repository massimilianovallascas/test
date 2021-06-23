def getCredentials(String fileName) {
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
        // docker.image('python:latest').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw" -p 3306:3306') { c ->
        //     /* Wait until mysql service is up */
        //     sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
        //     /* Run some tests which require MySQL */
        //     sh 'make check'
        // }
    def data = fs.readFromYmlFile(fileName)
    println(data.environment)
    def environmentId = data.environment."${env.BRANCH_NAME}"
    println(environmentId)
}

// while true; sleep 600 && osascript -e 'display alert "Time is up" message "Please start discusssing next story."'; done