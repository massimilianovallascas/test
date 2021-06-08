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

def readOrderFromFile() {
    def data = []
    if (fileExists(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)) {
        data = readFile(params.TERRAFORM_DEPLOYMENT_ORDER_FILE).readLines()
        // def data = readYaml(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
    }
    return data
}

void runDynamic() {
    def stages = readOrderFromFile()
    echo "[INFO] Config file $params.TERRAFORM_DEPLOYMENT_ORDER_FILE contains " + stages.size() + "steps"
    stages.each { stageName ->  
        script {
            stage(stageName) {
                echo "$stageName"
            }
        }
    }
}