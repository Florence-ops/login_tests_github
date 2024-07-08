pipeline {
    agent any
    parameters {
        string(defaultValue: "https://github.com/Florence-ops/tests_github.git", description: 'GitHub Repository URL', name: 'GITHUB_URL')
    }
    tools {
        maven 'Maven_3.9.8' // Ensure this matches your Maven installation name in Jenkins
        jdk 'JDK_11' // Ensure this matches your JDK installation name in Jenkins
    }
    stages {
        stage('Checkout Git repository') {
            steps {
                git branch: 'main', url: "${params.GITHUB_URL}"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                sh 'ls -R target' // Verify the directory structure
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}


