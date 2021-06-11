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

def plan(int parallelism = 256, logFile = "terraform_plan.log") {
    sh """
        ./terraform plan -no-color --paralllelism ${parallelism}
    """
}

def build(int parallelism = 256) {
    sh """
        ./terraform build -no-color --paralllelism ${parallelism}
    """
}