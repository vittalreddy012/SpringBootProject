pipeline {
  agent any

  environment {
    AWS_REGION = 'ap-southeast-2'
    ECR_REPO   = '869020980224.dkr.ecr.ap-southeast-2.amazonaws.com/springboot-app'
    IMAGE_TAG  = "${env.BUILD_NUMBER}"
  }

  stages {

    stage('Build JAR') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t springboot-app:${IMAGE_TAG} .'
      }
    }

    stage('Login to ECR') {
      steps {
        sh '''
          aws ecr get-login-password --region $AWS_REGION \
          | docker login --username AWS --password-stdin $ECR_REPO
        '''
      }
    }

    stage('Tag & Push to ECR') {
      steps {
        sh '''
          docker tag springboot-app:${IMAGE_TAG} $ECR_REPO:${IMAGE_TAG}
          docker tag springboot-app:${IMAGE_TAG} $ECR_REPO:latest
          docker push $ECR_REPO:${IMAGE_TAG}
          docker push $ECR_REPO:latest
        '''
      }
    }
  }
}
