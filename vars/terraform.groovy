import massi.terraform.GlobalVars

def download(String version) {
    sh """
        if [ ! -f "/tmp/terraform_${version}.zip" ]; then curl https://releases.hashicorp.com/terraform/${version}/terraform_${version}_linux_amd64.zip -o /tmp/terraform_${version}.zip; fi
        unzip -o /tmp/terraform_${version}.zip -d .
        chmod 0755 ./terraform
    """
}

def stage = { String stageName ->
    def f = '*.tf'
    // aws.getCredentials()
    stage(stageName) {
        if (utils.folderHasFile(f)) {
            script {
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
            echo error("File not found (${f}), execution interrupted.")
        }
    }
}

def init() {
    sh """
        ./terraform init -no-color
    """
}

def plan(int parallelism = GlobalVars.parallelism) {
    sh """
        ./terraform plan -no-color --parallelism ${parallelism} --out ./${GlobalVars.buildPlan}
    """
}

def apply(int parallelism = GlobalVars.parallelism) {
    sh """
        ./terraform apply -no-color --parallelism ${parallelism} ./${GlobalVars.buildPlan}
    """
}

def clean() {
     sh """
        rm -rf ./${GlobalVars.buildPlan}
    """
}
