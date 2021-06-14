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
        string(name: 'version', defaultValue: '', description: 'e.g. 1.3.8 (required for production)')
    }

    stages {
        stage("Validate parameters rights") {
            steps {
                // script {
                //     check.commitHasTag("master")
                     check.versionExists("master", param.version)
                //     check.deployContinue()
                // }
                echo "${triggeredBy()}"
            }
        }
        stage("Setup terraform") {
            steps {
                script {
                    terraform.download("1.0.0")
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

