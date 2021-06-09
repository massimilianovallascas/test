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
    }

    stages {
        stage("Get info"){
            steps {
                echo "getting info"
                dynamicStages(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
            }
        }
    } 
}

