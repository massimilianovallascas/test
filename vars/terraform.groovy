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

def plan(Int parallelism = 256, String logFile = terraform_plan.log) {
    sh """
        ./terraform plan -no-color --paralllelism ${parallelism} | tee ../${logfile}
    """
}

def build(Int parallelism = 256) {
    sh """
        ./terraform build -no-color --paralllelism ${parallelism}
    """
}