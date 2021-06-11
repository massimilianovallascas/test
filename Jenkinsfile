// @Library('test')_

library identifier: 'test@master',
    retriever: modernSCM([
      $class: 'GitSCMSource',
      remote: 'https://github.com/massimilianovallascas/test.git'
])

pipeline {
    agent any
    
    parameters {
        string(name: 'TERRAFORM_DEPLOYMENT_ORDER_FILE', defaultValue: '.terraform_deployment_order', description: '')
        // string(name: 'TARGET_ENVIRONMENT', defaultValue: 'development', description: '')
        string(name: 'accessKey', defaultValue: '', description: ' The AWS Access Key to use.')
        password(name: 'secretKey', defaultValue: '', description: ' The AWS Secret Key to use.')
        string(name: 'sessionToken', defaultValue: '', description: ' The AWS Session Token to use.')
        string(name: 'version', defaultValue: '', description: 'e.g. 1.3.8 (required for production)')
        string(name: 'Access key', defaultValue: '', description: ' The AWS Access Key to use.')
    }

    stages {
        stage("Validate parameters rights") {
            steps {
                script {
                    // check.commitHasTag("master")
                    // check.versionExists("master")
                    // check.deployContinue()
                    
                }
                echo "${triggeredBy()}"
            }
        }
        stage("Setup terraform") {
            steps {
                script {
                    terraform.download("1.0.0")
                    terraform.init()
                }
            }
        }
        stage("Getting dynamic stages") {
            steps {
                dynamicStages(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
            }
        }
    } 
}

