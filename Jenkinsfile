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
        File file = new File(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
        // data = File(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
        data = file.readLines()
        // def datas = readYaml(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
    }
    return data
}

void runDynamic() {
    def s = readOrderFromFile()

    s.each { module ->  
        script {
            stage(module) {
                
                    echo "in"
                
            }
        }
    }
}