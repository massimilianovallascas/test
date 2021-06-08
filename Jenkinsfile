def readOrderFromFile() {
    def data = readFile(file: param.TERRAFORM_DEPLOYMENT_ORDER_FILE)
    return data
}

def runDynamic(args) {
    data = readOrderFromFile
    println(data)
    
    // parallel args.items.collectEntries { 
    //     name -> [ "${name}": { 
    //         node('nodeLabel') {
    //             stage("${name}") {
    //                 stage("${name}-a") {
    //                     sh "env | grep -i NODE_NAME"
    //                 }
    //                 stage("${name}-b") {
    //                     sh "env | grep -i NODE_NAME"
    //                 }
    //             }
    //             }
    //         }
    //     ]
    // }
}

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

