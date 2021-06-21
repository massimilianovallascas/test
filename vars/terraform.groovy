import massi.terraform.GlobalVars

def download(String version) {
    sh """
        if [ ! -f "/tmp/terraform_${version}.zip" ]; then curl https://releases.hashicorp.com/terraform/${version}/terraform_${version}_linux_amd64.zip -o /tmp/terraform_${version}.zip; fi
        unzip -o /tmp/terraform_${version}.zip -d .
        chmod 0755 ./terraform
    """
}

def stage(stageName) {
    // def filePattern = 'main.tf'
    def filePattern = '*.tfx'
    // aws.getCredentials()
    stage(stageName) {
        def fe = fileExists stageName
        if (fe) {
            dir(stageName) {
                // def fpe = fileExists filePattern
                script {
                    fpe = "find . -name '${filePattern}' | wc -l | xargs"
                }
                if (fpe > 0) {
                    script {
                        sh "pwd"
                        milestone()
                        init()
                        milestone()
                        plan()
                        milestone()
                        apply()
                        milestone()
                        clean()
                        milestone()
                    }
                } else {
                    echo error("File not found (${filePattern}), execution interrupted.")
                }
            }
        } else {
            echo error("File not found (${stageName}), execution interrupted.")
        }
    }
}

def init() {
    sh """
        ../terraform init -no-color
    """
}

def plan(int parallelism = GlobalVars.parallelism) {
    sh """
        ../terraform plan -no-color --parallelism ${parallelism} --out ./${GlobalVars.buildPlan}
    """
}

def apply(int parallelism = GlobalVars.parallelism) {
    sh """
        ../terraform apply -no-color --parallelism ${parallelism} ./${GlobalVars.buildPlan}
    """
}

def clean() {
     sh """
        rm -rf ./${GlobalVars.buildPlan}
    """
}
