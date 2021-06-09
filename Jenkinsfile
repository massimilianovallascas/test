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
        string(name: 'TARGET_ENVIRONMENT', defaultValue: 'development', description: '')
    }

    stages {
        stage("Check rights") {
            steps {
                script {
                    if (params.TARGET_ENVIRONMENT != 'development') {
                        input message: "You are executing this scripts agains the ${params.TARGET_ENVIRONMENT} environment. Should we continue?"
                    }
                }
                echo "Deployment triggered by ${triggeredBy}"
            }
        }
        stage("Getting dynamic stages") {
            steps {
                echo "getting info"
                dynamicStages(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
            }
        }
    } 
}

