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
In the Dockerfile there are the info needed to package the executable .jar file into the Docker container.

Note that the -i ("- interactive") parameter in run it's needed it keeps STDIN open even if not attached. 
The -v datavolume:/database maps a docker volume named datavolume to the /database directory within the container, and it is used to get persistent storage.
```sh
docker build -t davide/urlapi:1.0 .

docker run -i -v datavolume:/database davide/urlapi:1.0
```

### How to
When you run the docker image a cli will pop up.
![Menu](images/menu.jpg)

You can press:
	- 1 to insert a link and make it short
	- 2 to get the original link from a short one
	- 3 to exit
	- 4 to print all entries in database
	- 5 to print only the most clicked short link
