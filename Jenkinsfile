pipeline {
    agent any
    
    parameters {
        string(name: 'TERRAFORM_DEPLOYMENT_ORDER_FILE', defaultValue: '.terraform_deployment_order', description: '')
    }

    stages {
        stage("Get info"){
            steps {
                echo "getting info"
                runDynamic()
            }
        }
    } 
}
