First, update your existing list of packages:

```sh 
sudo apt update
```

Next, install a few prerequisite packages which let apt use packages over HTTPS:

```sh 
sudo apt install apt-transport-https ca-certificates curl software-properties-common
```

Then add the GPG key for the official Docker repository to your system:

```sh 
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```

Add the Docker repository to APT sources:

```sh 
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
```

This will also update our package database with the Docker packages from the newly added repo.

Make sure you are about to install from the Docker repo instead of the default Ubuntu repo:

```sh 
apt-cache policy docker-ce
```

Finally, install Docker:

```sh 
sudo apt install docker-ce
```

Docker should now be installed, the daemon started, and the process enabled to start on boot. Check that it’s running:

```sh 
sudo systemctl status docker
```

### Config The Remote access - Docker

Navigate to /lib/systemd/system in your terminal and open docker.service file

```sh 
sudo nano /lib/systemd/system/docker.service
```

Find the line which starts with ExecStart and adds -H=tcp://0.0.0.0:2375 to make it look like

```sh 
ExecStart=/usr/bin/dockerd -H=fd:// -H=tcp://0.0.0.0:2375
```

Reload the docker daemon

```sh 
systemctl daemon-reload
```

Restart the container

```sh 
sudo service docker restart
```

Test if it is working by using this command, if everything is fine below command should return a JSON

```sh 
curl http://localhost:2375/images/json
```
