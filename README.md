# URLShortner API
This API is designed to:

  - Short URL cration
  - Forwarding of short URL to original ones
  - Persistent storage in database
  - Can be run in Docker
  - Gathering statistics

### Docker
This API is very easy to install and deploy in a Docker container.

In the Dockerfile there are the info needed to package the executable .jar file into the Docker container.

Note the -i ("- interactive") parameter in run it's needed it keeps STDIN open even if not attached.

```sh
git clone https://github.com/lapentad/URLShortner.git
cd UrlShortner
docker build -t davide/urlapi:1.0 .
docker run -i -t davide/urlapi:1.0
```