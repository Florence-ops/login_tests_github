pipeline {
    agent any
    environment {
        JAVA_HOME = "${WORKSPACE}/jdk-11"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Install Dependencies') {
            steps {
                sh '''
                # Ensure wget or curl is installed
                if ! command -v curl &> /dev/null; then
                    apt-get update && apt-get install -y curl
                fi

                # Download and extract OpenJDK 11
                curl -L -o OpenJDK11.tar.gz https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.11+9/OpenJDK11U-jdk_x64_linux_hotspot_11.0.11_9.tar.gz
                mkdir -p jdk-11
                tar -xzf OpenJDK11.tar.gz -C jdk-11 --strip-components=1
                '''
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Florence-ops/github_login_tests.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add deployment steps here
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
