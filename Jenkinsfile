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
    def terraformFolders = readOrderFromFile()
    echo "[INFO] Config file $params.TERRAFORM_DEPLOYMENT_ORDER_FILE contains " + terraformFolders.size() + " steps"
    terraformFolders.each { terraformFolder -> 
        stageName = "Running Terraform module for $terraformFolder"
        script {
            stage(stageName) {
                echo "$terraformFolder"
            }
        }
    }
}