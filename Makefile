
build:
	docker build \
	  -f Dockerfile \
	  -t myjenkins .

start:
	docker run \
	  -p 8080:8080 -p 50000:50000 \
	  -v $(shell pwd)/data:/var/jenkins_home \
	  --env JAVA_OPTS=-Djenkins.install.runSetupWizard=false \
	  myjenkins

clean:
	rm -rf $(shell pwd)/data
