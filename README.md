# URLShortner API
This API is designed to:

  - Short URL creation
  - Forwarding of short URL to original ones
  - Persistent storage in database
  - Can be run in Docker
  - Gathering statistics

## Get Started
### Docker
This API is very easy to install and deploy in a Docker container.

Clone the repository and change directory.

```sh
git clone https://github.com/lapentad/URLShortner.git

cd UrlShortner
```
Then you need to build .jar with Maven

```sh
./mvnw package
```

In the Dockerfile there are the info needed to package the executable .jar file into the Docker container.


The -v datavolume:/database maps a docker volume named datavolume to the /database directory within the container, and it is used to get persistent storage.

```sh
docker build -t lapenta/urlapi .

docker run -p 80:80 -v datavolume:/database lapenta/urlapi
```

Run addhosts.bat OR "127.0.0.1 www.short.com" to hosts file.

Go towww.short.com to see endpoint