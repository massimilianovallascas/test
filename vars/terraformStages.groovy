def readOrderFromFile(def fileName) {
    def data = []
    if (fileExists(fileName)) {
        data = readFile(params.TERRAFORM_DEPLOYMENT_ORDER_FILE).readLines()
        // def data = readYaml(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
    }

    return data
}

def call(def fileName) {
    def terraformFolders = readOrderFromFile(fileName)

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