// @Library('test')_

library identifier: 'test@master',
    retriever: modernSCM([
      $class: 'GitSCMSource',
      remote: 'https://github.com/massimilianovallascas/test.git'
])

pipeline {
    agent any

    options {
        timestamps()
    }

    parameters {
        string(name: 'TERRAFORM_DEPLOYMENT_CONFIG_FILE', defaultValue: '.terraform_deployment_config.yml', description: '')
        string(name: 'VERSION', defaultValue: '', description: 'e.g. 1.3.8 (required for production)')
        string(name: 'HSBC_PASS', defaultValue: '', description: 'Your personal password')
        string(name: 'CR', defaultValue: '', description: 'Change request number (required for production)')
    }

    stages {
        stage("Perform checks") {
            steps {
                script {
                    if (params.version != "") {
                        // checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: env.GIT_URL, credentialsId: credential]], branches: [[name: params.version]]], poll: false
                    } else {
                        // check.commitHasTag("master")
                    }
                    //check.versionExists("master", params.version)
                    //check.deployContinue()
                }
                echo "${triggeredBy()}"
            }
        }
        
        stage("Setup") {
            steps {
                script {
                    terraform.download("1.0.0")
                }

            }
        }
        stage("Getting dynamic stages") {
        //     // stages {
        //     //     stage('a') {
        //     //         steps {
        //     //             echo "aa"
        //     //         }
        //     //     }
        //     //     stage('b') {
        //     //         steps {
        //     //             echo "bb"
        //     //         }
        //     //     }
        //     // }
            steps {
                script{
                    aws.getCredentials(params.TERRAFORM_DEPLOYMENT_CONFIG_FILE)
                    dynamicStages.fromYmlFile(params.TERRAFORM_DEPLOYMENT_CONFIG_FILE, terraform.&stage)           
                }     
            }
        }

        //}
    } 
    // post {
    //     regression {

    //     }

    //     fixed {
            
    //     }
    // }
}