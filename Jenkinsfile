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


    if (fileExists(params.TERRAFORM_DEPLOYMENT_ORDER_FILE)) {
        def data = readFile(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
        def datas = readYaml(file: params.TERRAFORM_DEPLOYMENT_ORDER_FILE)
        
        return data
    }
}

def runDynamic() {
    data = readOrderFromFile()
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