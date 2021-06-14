import massi.terraform

def download(String version) {
    sh """
        echo "Download terraform"
        curl https://releases.hashicorp.com/terraform/${version}/terraform_${version}_linux_amd64.zip -o terraform.zip
        unzip -o terraform.zip -d .
        chmod 0755 ./terraform
    """
}

def init() {
    sh """
        ./terraform init -no-color
    """
}

def plan(int parallelism = GlobalVars.parallelism) {
    sh """
        ./terraform plan -no-color --parallelism ${parallelism} --out ./
    """ + GlobalVars.buildPlan
}

def apply(int parallelism = GlobalVars.parallelism) {
    sh """
        ./terraform apply -no-color --parallelism ${parallelism} ./
    """ + GlobalVars.buildPlan
}

def clean() {
     sh """
        rm -rm ./
    """ + GlobalVars.buildPlan
}