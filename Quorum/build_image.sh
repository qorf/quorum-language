docker buildx create --name quorum_builder --use
docker buildx build --no-cache -f Dockerfile.quorum.build -t andreasstefik/quorum:9.5.1 . --platform linux/amd64,linux/arm64 --push
