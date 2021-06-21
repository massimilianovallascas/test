import massi.terraform.GlobalVars

def download(String version) {
    sh """
        if [ ! -f "/tmp/terraform_${version}.zip" ]; then curl https://releases.hashicorp.com/terraform/${version}/terraform_${version}_linux_amd64.zip -o /tmp/terraform_${version}.zip; fi
        unzip -o /tmp/terraform_${version}.zip -d .
        chmod 0755 ./terraform
    """
}

def folderHasTerraformFile(filePath) {
    def fe = fileExists filePath
    println fe
    return fe
}

def stages() {
    if (folderHasTerraformFile('*.tf') == true) {
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
